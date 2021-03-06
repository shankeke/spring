package com.jusfoun.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * 说明：客户端信息. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月1日 上午9:12:10
 */
public interface ClientDetails {

	/**
	 * 说明：获取client_id. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月1日 上午9:12:25
	 * @return client_id
	 */
	String getClientId();

	/**
	 * 说明： 获取client_secret. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月1日 上午9:12:39
	 * @return client_secret
	 */
	String getClientSecret();

	/**
	 * 说明：获取resource_ids. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月1日 上午9:13:01
	 * @return resource_ids
	 */
	String[] getResourceIds();

	/**
	 * 说明： 获取scopes. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月1日 上午9:13:32
	 * @return scopes
	 */
	String[] getScopes();

	/**
	 * 说明： 获取grant_types. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月1日 上午9:13:47
	 * @return 获取grant_types
	 */
	String[] getGrantTypes();

	/**
	 * 说明： access_token有效期. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月1日 上午9:14:07
	 * @return access_token有效期
	 */
	Integer getAccessTokenValidity();

	/**
	 * 说明： refresh_token有效期. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月1日 上午9:14:31
	 * @return refresh_token有效期
	 */
	Integer getRefreshTokenValidity();

	/**
	 * 说明： 获取客户端拥有的权限列表. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月1日 上午9:15:19
	 * @return 客户端权限列表
	 */
	Collection<? extends GrantedAuthority> getAuthorities();

	/**
	 * 说明：求权限交集. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月1日 下午1:09:29
	 * @param authorities
	 *            用户权限集合
	 * @return 返回当前登录能赋予的权限集合
	 */
	Collection<? extends GrantedAuthority> retainAuthorities(Collection<? extends GrantedAuthority> grantedAuthorities);

}
