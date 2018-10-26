package com.jusfoun.security.support.token.factory;

import org.springframework.security.core.userdetails.UserDetails;

import com.jusfoun.security.ClientDetails;
import com.jusfoun.security.exceptions.TokenException;
import com.jusfoun.security.exceptions.TokenInvalidException;
import com.jusfoun.security.support.token.AccessToken;
import com.jusfoun.security.support.token.RefreshToken;
import com.jusfoun.security.support.token.TokenType;
import com.jusfoun.security.support.token.parser.TokenParser;

/**
 * 说明： 简单令牌处理工厂 . <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月3日 上午12:11:07
 */
public class SimpleTokenFactory implements TokenFactory {

	/**
	 * 策略模式： 引入token解析器，具体使用哪个类型的解析器，使用时指定
	 */
	private final TokenParser tokenParser;

	public SimpleTokenFactory(TokenParser tokenParser) {
		this.tokenParser = tokenParser;
	}

	@Override
	public AccessToken createAccessToken(ClientDetails clientDetails, UserDetails userDetails) throws TokenException {
		return (AccessToken) tokenParser.createToken(clientDetails, userDetails, TokenType.ACCESS_TOKEN);
	}

	@Override
	public RefreshToken createRefreshToken(ClientDetails clientDetails, UserDetails userDetails) throws TokenException {
		return (RefreshToken) tokenParser.createToken(clientDetails, userDetails, TokenType.REFRESH_TOKEN);
	}

	@Override
	public AccessToken parseAccessToken(String token) throws TokenInvalidException {
		return (AccessToken) tokenParser.parseToken(token, TokenType.ACCESS_TOKEN);
	}

	@Override
	public RefreshToken parseRefreshToken(String token) throws TokenInvalidException {
		return (RefreshToken) tokenParser.parseToken(token, TokenType.REFRESH_TOKEN);
	}

}
