package com.jusfoun.security.exceptions;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * @author yjw@jusfoun.com
 * @date 2017年11月14日 下午1:45:30
 */
public class AuthMethodNotSupportedException extends AuthenticationServiceException {
	private static final long serialVersionUID = 3705043083010304496L;

	public AuthMethodNotSupportedException(String msg) {
		super(msg);
	}
}
