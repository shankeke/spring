package com.jusfoun.security.support.token.parser;

import java.util.Base64;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.jusfoun.security.ClientDetails;
import com.jusfoun.security.exceptions.TokenCreateException;
import com.jusfoun.security.exceptions.TokenInvalidException;
import com.jusfoun.security.support.token.AccessToken;
import com.jusfoun.security.support.token.RefreshToken;
import com.jusfoun.security.support.token.Token;
import com.jusfoun.security.support.token.TokenType;
import com.jusfoun.security.support.token.verifier.TokenVerifier;

/**
 * 描述 :基于Base64编码的令牌解析器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月2日 下午11:11:14
 */
public class Base64TokenParser extends AbstractTokenParser {

	public Base64TokenParser(final TokenVerifier tokenVerifier) {
		super(tokenVerifier);
	}

	@Override
	public Token create(ClientDetails clientDetails, UserDetails userDetails, TokenType type) throws TokenCreateException {
		try {
			String clientId = clientDetails.getClientId();
			String subject = userDetails.getUsername();
			StringBuffer buff = new StringBuffer();
			buff.append(clientId).append(DEFAULT_DELIMITER).append(subject).append(DEFAULT_DELIMITER).append(UUID.randomUUID().toString());
			switch (type) {
				case REFRESH_TOKEN :
					return new RefreshToken(clientId, subject, Base64.getEncoder().encodeToString(buff.toString().getBytes()));
				default :
					return new AccessToken(clientId, subject, Base64.getEncoder().encodeToString(buff.toString().getBytes()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TokenCreateException("Token create error !", e);
		}
	}

	@Override
	public Token parse(String token, TokenType type) throws TokenInvalidException {
		Assert.hasText(token, "Parameter 'token' must be bot empty!");
		String decrypt = null;
		try {
			decrypt = new String(Base64.getDecoder().decode(token.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new TokenInvalidException(String.format("Invalid token '%s' !", token), e);
		}
		String[] split = decrypt.split(DEFAULT_DELIMITER);
		if (split == null || split.length != 3) {
			throw new TokenInvalidException(String.format("Invalid token '%s' !", token));
		}
		switch (type) {
			case REFRESH_TOKEN :
				return new RefreshToken(split[0], split[1], token);
			default :
				return new AccessToken(split[0], split[1], token);
		}
	}

}
