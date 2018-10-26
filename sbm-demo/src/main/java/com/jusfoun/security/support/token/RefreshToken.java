package com.jusfoun.security.support.token;

/**
 * 说明： 刷新令牌. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月22日 下午5:27:26
 */
public class RefreshToken extends AccessToken {
	private static final long serialVersionUID = -1097637388411959968L;

	public RefreshToken(Token token) {
		super(token.getClientId(), token.getSubject(), token.getToken());
	}

	public RefreshToken(String clientId, String subject, String token) {
		super(clientId, subject, token);
	}

}
