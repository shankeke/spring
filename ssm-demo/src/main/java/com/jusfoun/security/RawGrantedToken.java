package com.jusfoun.security;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jusfoun.entity.TokenUserDetails;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述 : 简单token对象. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月4日 下午8:38:24
 */
@ApiModel
public class RawGrantedToken implements Serializable {
	private static final long serialVersionUID = 1574825783668756453L;

	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	private Long userId;

	/**
	 * 用户名称
	 */
	@ApiModelProperty("用户名称")
	private String username;

	/**
	 * 真实姓名
	 */
	@ApiModelProperty("真实姓名")
	private String realname;

	/**
	 * 权限集合
	 */
	@ApiModelProperty("权限集合")
	private String[] authorities;

	/**
	 * 是否启用
	 */
	@ApiModelProperty("是否启用")
	private boolean enabled;

	/**
	 * 是否认证
	 */
	@ApiModelProperty("是否认证")
	private boolean authenticated;

	/**
	 * 是否锁定
	 */
	@ApiModelProperty("是否锁定")
	private boolean accountNonLocked;

	/**
	 * 账户是否过期
	 */
	@ApiModelProperty("账户是否过期")
	private boolean accountNonExpired;

	/**
	 * 密码是否过期
	 */
	@ApiModelProperty("密码是否过期")
	private boolean credentialsNonExpired;

	/**
	 * 有效期
	 */
	@ApiModelProperty("有效期")
	private Integer expires_in;
	/**
	 * 认证token
	 */
	@ApiModelProperty("认证token")
	private String access_token;

	/**
	 * 刷新token
	 */
	@ApiModelProperty("刷新token")
	private String refresh_token;

	/**
	 * 失效时间
	 */
	@ApiModelProperty("失效时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date expiresTime;

	/**
	 * 失效时间
	 */
	@ApiModelProperty("失效时间")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date refreshTokenExpTime;

	public RawGrantedToken() {
	}

	public RawGrantedToken(Long userId, String username, String realname, String[] authorities, boolean enabled, boolean authenticated, boolean accountNonLocked,
			boolean accountNonExpired, boolean credentialsNonExpired, Integer expires_in, String access_token, String refresh_token, Date expiresTime, Date refreshTokenExpTime) {
		super();
		this.userId = userId;
		this.username = username;
		this.realname = realname;
		this.authorities = authorities;
		this.enabled = enabled;
		this.authenticated = authenticated;
		this.accountNonLocked = accountNonLocked;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.expires_in = expires_in;
		this.access_token = access_token;
		this.refresh_token = refresh_token;
		this.expiresTime = expiresTime;
		this.refreshTokenExpTime = refreshTokenExpTime;
	}

	public RawGrantedToken(TokenUserDetails userDetails) {
		this(userDetails.getUserId(), userDetails.getUsername(), userDetails.getRealname(), userDetails.grantedAuthorities(), userDetails.isEnabled(),
				userDetails.isAuthenticated(), userDetails.isAccountNonLocked(), userDetails.isAccountNonExpired(), userDetails.isCredentialsNonExpired(),
				userDetails.getExpiresIn(), userDetails.getAccessToken(), userDetails.getRefreshToken(), userDetails.getAccessTokenExpTime(), userDetails.getRefreshTokenExpTime());
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String[] getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public Integer getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public Date getExpiresTime() {
		return expiresTime;
	}

	public void setExpiresTime(Date expiresTime) {
		this.expiresTime = expiresTime;
	}

	public Date getRefreshTokenExpTime() {
		return refreshTokenExpTime;
	}

	public void setRefreshTokenExpTime(Date refreshTokenExpTime) {
		this.refreshTokenExpTime = refreshTokenExpTime;
	}

}
