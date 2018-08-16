package com.jusfoun.security.support.token.parser;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;

import com.jusfoun.security.ClientDetails;
import com.jusfoun.security.config.JwtSettings;
import com.jusfoun.security.exceptions.TokenCreateException;
import com.jusfoun.security.exceptions.TokenExpiredException;
import com.jusfoun.security.exceptions.TokenInvalidException;
import com.jusfoun.security.support.token.AccessToken;
import com.jusfoun.security.support.token.RefreshToken;
import com.jusfoun.security.support.token.Token;
import com.jusfoun.security.support.token.TokenType;
import com.jusfoun.security.support.token.extract.adapter.TokenExtractAdapter;
import com.jusfoun.security.support.token.verifier.TokenVerifier;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * 描述 :基于jwt实现的令牌解析器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月6日 上午10:35:21
 */
public class JwtTokenParser extends AbstractTokenParser {
	private final JwtSettings jwtSettings;

	public JwtTokenParser(final TokenExtractAdapter tokenExtractAdapter, final TokenVerifier tokenVerifier, final JwtSettings jwtSettings) {
		super(tokenExtractAdapter, tokenVerifier);
		this.jwtSettings = jwtSettings;
	}

	@Override
	public Token create(ClientDetails clientDetails, UserDetails userDetails, TokenType type) throws TokenCreateException {
		String clientId = clientDetails.getClientId();
		if (StringUtils.isBlank(clientId)) {
			throw new IllegalArgumentException("Cannot create JWT Token without clientId");
		}
		String subject = userDetails.getUsername();
		if (StringUtils.isBlank(userDetails.getUsername())) {
			throw new IllegalArgumentException("Cannot create JWT Token without username");
		}
		/*
		 * Collection<? extends GrantedAuthority> authorities =
		 * userDetails.getAuthorities(); if (authorities == null ||
		 * authorities.isEmpty()) { throw new
		 * IllegalArgumentException("User doesn't have any privileges"); }
		 */

		switch (type) {
			case REFRESH_TOKEN :
				return createRefreshToken(clientId, subject, clientDetails.getRefreshTokenValidity());
			default :
				// return createAccessToken(clientId, subject,
				// authorities.stream().map(t ->
				// t.getAuthority()).collect(Collectors.toSet()),
				// clientDetails.getAccessTokenValidity());
				return createAccessToken(clientId, subject, Arrays.stream(clientDetails.getScopes()).collect(Collectors.toSet()), clientDetails.getAccessTokenValidity());
		}
	}

	private AccessToken createAccessToken(String clientId, String subject, Set<String> scopes, Integer accessTokenValidity) {
		// 设置token信息
		Claims claims = Jwts.claims();
		claims.put("scopes", scopes);

		// 签章
		LocalDateTime currentTime = LocalDateTime.now();
		String token = Jwts.builder()//
				.setId(UUID.randomUUID().toString())//
				.setClaims(claims)//
				.setSubject(subject)//
				.setIssuer(clientId)//
				.setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))//
				.setExpiration(Date.from(currentTime.plusSeconds(accessTokenValidity).atZone(ZoneId.systemDefault()).toInstant()))//
				.signWith(SignatureAlgorithm.HS512, jwtSettings.getTokenSigningKey())//
				.compact();

		return new AccessToken(clientId, subject, token);
	}

	private RefreshToken createRefreshToken(String clientId, String subject, Integer accessTokenValidity) {
		// 设置token信息
		Claims claims = Jwts.claims();
		claims.put("scopes", "refresh_token");

		// 签章
		LocalDateTime currentTime = LocalDateTime.now();
		String token = Jwts.builder()//
				.setId(UUID.randomUUID().toString())//
				.setClaims(claims)//
				.setSubject(subject)//
				.setIssuer(clientId)//
				.setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))//
				.setExpiration(Date.from(currentTime.plusMinutes(accessTokenValidity).atZone(ZoneId.systemDefault()).toInstant()))//
				.signWith(SignatureAlgorithm.HS512, jwtSettings.getTokenSigningKey())//
				.compact();

		return new RefreshToken(clientId, subject, token);
	}

	@Override
	public Token parse(String token, TokenType type) throws TokenInvalidException {
		Jws<Claims> claims = parseClaims(jwtSettings.getTokenSigningKey(), token);
		if (claims != null) {
			Claims body = claims.getBody();
			String clientId = body.getIssuer();
			String subject = body.getSubject();
			switch (type) {
				case REFRESH_TOKEN :
					return new RefreshToken(clientId, subject, token);
				default :
					return new AccessToken(clientId, subject, token);
			}
		}
		throw new TokenInvalidException(String.format("Invalid JWT token '%s'", token));
	}

	public Jws<Claims> parseClaims(String signingKey, String token) {
		try {
			return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
		} catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException e) {
			throw new BadCredentialsException(String.format("Invalid JWT token '%s'", token), e);
		} catch (ExpiredJwtException e) {
			throw new TokenExpiredException(String.format("Expired JWT token '%s'", token), e);
		}
	}

}
