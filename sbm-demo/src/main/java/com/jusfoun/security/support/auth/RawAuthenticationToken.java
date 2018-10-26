package com.jusfoun.security.support.auth;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jusfoun.security.support.token.AccessToken;

/**
 * 说明：客户端令牌包装类. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午3:05:19
 */
public class RawAuthenticationToken extends AbstractAuthenticationToken {
	private static final long serialVersionUID = 2877954820905567501L;

	private AccessToken accessToken;
	private UserDetails userDetails;

	public RawAuthenticationToken(AccessToken unsafeToken) {
		super(null);
		this.accessToken = unsafeToken;
		this.setAuthenticated(false);
	}

	public RawAuthenticationToken(UserDetails userDetails, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.eraseCredentials();
		this.userDetails = userDetails;
		super.setAuthenticated(true);
	}

	@Override
	public void setAuthenticated(boolean authenticated) {
		if (authenticated) {
			throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		}
		super.setAuthenticated(false);
	}

	@Override
	public Object getCredentials() {
		return this.accessToken;
	}

	@Override
	public Object getPrincipal() {
		return this.userDetails;
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		this.accessToken = null;
	}
}
