package com.jusfoun.security.support.login;

import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import com.jusfoun.common.annotation.Logable;
import com.jusfoun.entity.TokenUserDetails;
import com.jusfoun.security.ClientDetails;
import com.jusfoun.security.ClientDetailsService;
import com.jusfoun.security.exceptions.ClientBadSecretException;
import com.jusfoun.security.exceptions.NoGrantedAnyAuthorityException;

/**
 * 描述 : 认证提供器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午2:48:46
 */
public class LoginAuthenticationProvider implements AuthenticationProvider {
	private final ClientDetailsService clientDetailsService;
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;

	public LoginAuthenticationProvider(final UserDetailsService userDetailsService,
			final PasswordEncoder passwordEncoder, final ClientDetailsService clientDetailsService) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
		this.clientDetailsService = clientDetailsService;
	}

	@Logable(fullPath = "系统管理/用户管理/用户登录", desc = "用户登录")
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Assert.notNull(authentication, "No authentication data provided");
		LoginAuthenticationToken auth = (LoginAuthenticationToken) authentication;
		// 客户端认证
		ClientDetails clientDetails = auth.getClientDetails();
		String clientId = clientDetails.getClientId();
		String clientSecret = clientDetails.getClientSecret();

		// 查询客户端信息
		ClientDetails loadClientDetails = clientDetailsService.loadClientByClientId(clientId);
		// 校验客户端密码
		if (!passwordEncoder.matches(clientSecret, loadClientDetails.getClientSecret())) {
			throw new ClientBadSecretException("Authentication Failed. ClientId or ClientSecret not valid.");
		}

		// 用户认证
		String username = (String) auth.getPrincipal();
		String password = (String) auth.getCredentials();
		// 加载用户信息
		TokenUserDetails userDetails = (TokenUserDetails) userDetailsService.loadUserByUsername(username);
		// 用户信息认证
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
		}

		// 对客户端的权限和用户的权限求交集就是该用户在该客户端拥有的权限集合
		Collection<? extends GrantedAuthority> authorities = loadClientDetails
				.retainAuthorities(userDetails.getAuthorities());
		if (authorities == null || authorities.isEmpty()) {
			throw new NoGrantedAnyAuthorityException("User has no authority assigned");
		}
		// 把权限集合给用户当前的会话
		userDetails.setAuthorities(authorities);

		// 认证通过
		return new LoginAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities(),
				loadClientDetails);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (LoginAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
