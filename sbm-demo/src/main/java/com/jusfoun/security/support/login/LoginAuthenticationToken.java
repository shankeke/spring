package com.jusfoun.security.support.login;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.jusfoun.security.ClientDetails;

/**
 * 描述 : 登录认证信息包装. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月6日 下午3:32:34
 */
public class LoginAuthenticationToken extends UsernamePasswordAuthenticationToken {
	private static final long serialVersionUID = 7444831017334725302L;
	private final ClientDetails clientDetails;

	public LoginAuthenticationToken(Object principal, Object credentials, ClientDetails clientDetails) {
		super(principal, credentials);
		this.clientDetails = clientDetails;
	}

	public LoginAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, ClientDetails clientDetails) {
		super(principal, credentials, authorities);
		this.clientDetails = clientDetails;
	}

	public ClientDetails getClientDetails() {
		return clientDetails;
	}

}
