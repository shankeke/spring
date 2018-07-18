package com.jusfoun.security.exceptions;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * 描述 :没有权限. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月14日 下午2:31:26
 */
public class UnAuthorizedException extends AuthenticationServiceException {
	private static final long serialVersionUID = 5869204524357172888L;

	private String unauthorizedUrl;

	public UnAuthorizedException(String msg) {
		super(msg);
	}

	public UnAuthorizedException(String msg, Throwable t) {
		super(msg, t);
	}

	public UnAuthorizedException(String msg, String unauthorizedUrl) {
		super(msg);
		this.unauthorizedUrl = unauthorizedUrl;
	}

	public UnAuthorizedException(String msg, String unauthorizedUrl, Throwable t) {
		super(msg, t);
		this.unauthorizedUrl = unauthorizedUrl;
	}

	public String getUnauthorizedUrl() {
		return unauthorizedUrl;
	}

	public void setUnauthorizedUrl(String unauthorizedUrl) {
		this.unauthorizedUrl = unauthorizedUrl;
	}
}
