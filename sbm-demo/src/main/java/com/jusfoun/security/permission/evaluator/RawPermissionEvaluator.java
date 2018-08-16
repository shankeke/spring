package com.jusfoun.security.permission.evaluator;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * 描述 : 自定义基于方法的权限验证逻辑之 hasPermission()实现逻辑. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月11日 上午10:24:27
 */
@Component
public class RawPermissionEvaluator implements PermissionEvaluator {

	public static final String DEFAULT_TARGET_DOMAIN_OBJECT = "user";

	/**
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.access.PermissionEvaluator#hasPermission(org.springframework.security.core.Authentication,
	 *      java.lang.Object, java.lang.Object)
	 */
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		if (DEFAULT_TARGET_DOMAIN_OBJECT.equals(targetDomainObject)) {
			return this.hasPermission(authentication, permission);
		}
		return false;
	}

	/**
	 * 总是认为有权限
	 *
	 * @see org.springframework.security.access.PermissionEvaluator#hasPermission(org.springframework.security.core.Authentication,
	 *      java.io.Serializable, java.lang.String, java.lang.Object)
	 */
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
		return true;
	}

	/**
	 * 描述 : 简单的字符串比较，相同则认为有权限. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月11日 上午10:21:47
	 * @param authentication
	 *            身份验证
	 * @param permission
	 *            权限信息
	 * @return 如果身份认证中存在权限信息则认为有该权限
	 */
	private boolean hasPermission(Authentication authentication, Object permission) {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority authority : authorities)
			if (authority.getAuthority().equals(permission))
				return true;
		return false;
	}

}
