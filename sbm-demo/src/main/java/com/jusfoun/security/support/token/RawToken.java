package com.jusfoun.security.support.token;

import java.io.Serializable;

import com.jusfoun.security.support.token.parser.TokenParser;

/**
 * 描述 : 简单令牌. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月22日 下午5:27:16
 */
public class RawToken implements Serializable {
	private static final long serialVersionUID = -3993939583758194560L;

	/**
	 * 令牌载体
	 */
	private final String token;

	public RawToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public Token parseToken(TokenParser parser, TokenType type) {
		return parser.parseToken(token, type);
	}

}
