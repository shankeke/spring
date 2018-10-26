package com.jusfoun.security.support.token.parser;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.jusfoun.common.utils.encrypt.DES;
import com.jusfoun.security.ClientDetails;
import com.jusfoun.security.exceptions.TokenCreateException;
import com.jusfoun.security.exceptions.TokenInvalidException;
import com.jusfoun.security.support.token.AccessToken;
import com.jusfoun.security.support.token.RefreshToken;
import com.jusfoun.security.support.token.Token;
import com.jusfoun.security.support.token.TokenType;
import com.jusfoun.security.support.token.extract.adapter.TokenExtractAdapter;
import com.jusfoun.security.support.token.verifier.TokenVerifier;

/**
 * 说明：基于DES编码的令牌解析器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月2日 下午11:11:14
 */
public class DesTokenParser extends AbstractTokenParser {

	public DesTokenParser(final TokenExtractAdapter tokenExtractAdapter, final TokenVerifier tokenVerifier) {
		super(tokenExtractAdapter, tokenVerifier);
	}

	@Override
	public Token create(ClientDetails clientDetails, UserDetails userDetails, TokenType type) throws TokenCreateException {
		try {
			String clientId = clientDetails.getClientId();
			String subject = userDetails.getUsername();
			StringBuffer buff = new StringBuffer();
			buff.append(clientId).append(DEFAULT_DELIMITER).append(subject).append(DEFAULT_DELIMITER).append(UUID.randomUUID().toString());
			DES des = new DES();
			switch (type) {
				case REFRESH_TOKEN :
					return new RefreshToken(clientId, subject, des.encrypt(buff.toString()));
				default :
					return new AccessToken(clientId, subject, des.encrypt(buff.toString()));
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
			DES des = new DES();
			decrypt = des.decrypt(token);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TokenInvalidException("Invalid token: " + token, e);
		}
		String[] split = decrypt.split(DEFAULT_DELIMITER);
		if (split == null || split.length != 3) {
			throw new TokenInvalidException("Invalid token: " + token);
		}
		switch (type) {
			case REFRESH_TOKEN :
				return new RefreshToken(split[0], split[1], token);
			default :
				return new AccessToken(split[0], split[1], token);
		}
	}

}
