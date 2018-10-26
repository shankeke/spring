package com.jusfoun.security.exceptions;

/**
 * 说明： client密码错误. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月14日 下午1:45:42
 */
public class ClientBadSecretException extends ClientException {
	private static final long serialVersionUID = -2711776825981689462L;

	public ClientBadSecretException(String msg) {
		super(msg);
	}

	public ClientBadSecretException(String msg, Throwable t) {
		super(msg, t);
	}

	public ClientBadSecretException(String msg, String clientId) {
		super(msg, clientId);
	}

	public ClientBadSecretException(String msg, String clientId, Throwable t) {
		super(msg, clientId, t);
	}
}
