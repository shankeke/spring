package com.jusfoun.security.support.token;

/**
 * 说明：访问令牌. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月2日 下午11:35:01
 */
public class AccessToken extends RawToken implements Token {
	private static final long serialVersionUID = -2501760170904806774L;

	/**
	 * 令牌所属客户端ID
	 */
	private final String clientId;

	/**
	 * 用户名
	 */
	private final String subject;

	public AccessToken(String clientId, String subject, String token) {
		super(token);
		this.clientId = clientId;
		this.subject = subject;
	}

	@Override
	public String getClientId() {
		return clientId;
	}

	@Override
	public String getSubject() {
		return subject;
	}

	@Override
	public String getToken() {
		return super.getToken();
	}

}
