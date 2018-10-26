package com.jusfoun.security;

import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jusfoun.common.enums.UsingStatus;
import com.jusfoun.entity.SysUser;
import com.jusfoun.entity.TokenUserDetails;
import com.jusfoun.security.exceptions.NoGrantedAnyAuthorityException;
import com.jusfoun.service.SysModuleService;
import com.jusfoun.service.SysUserService;

/**
 * 说明： 实现用户信息加载方法. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月14日 下午1:43:11
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysModuleService sysModuleService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// 用户名为空
		if (StringUtils.isEmpty(username)) {
			throw new UsernameNotFoundException("Parameter 'username' cound not be null or empty !");
		}

		// 查询用户信息
		SysUser sysUser = sysUserService.selectByUsername(username);

		// 用户不存在
		if (sysUser == null) {
			throw new UsernameNotFoundException(String.format("User not found with username '%s' !", username));
		}

		// 账户失效
		if (UsingStatus.DISABLE.equalsTo(sysUser.getStatus())) {
			throw new DisabledException(String.format("Disabled account '%s' !", username));
		}

		// 账户锁定
		if (UsingStatus.NOT_ENABLED.equalsTo(sysUser.getStatus())) {
			throw new LockedException(String.format("Locked account '%s' !", username));
		}

		// 如果是管理员权限则需要用户拥有所有的权限，这里赋给该账户所有的权限
		if (sysUser.isAdmin()) {
			sysUser.setAuthorities(sysModuleService.selectAll().parallelStream().map(t -> new RawGrantedAuthority(t.getUrl())).collect(Collectors.toSet()));
		}

		// 用户没有赋权，用户需要有权限才能登陆服务
		if (sysUser.getAuthorities() == null || sysUser.getAuthorities().isEmpty()) {
			throw new NoGrantedAnyAuthorityException("User has no authority assigned !");
		}

		// 创建新的token信息
		return TokenUserDetails.build(sysUser);
	}
}
