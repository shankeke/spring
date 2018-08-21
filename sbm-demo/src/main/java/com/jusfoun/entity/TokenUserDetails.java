package com.jusfoun.entity;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jusfoun.security.ClientDetails;
import com.jusfoun.security.RawGrantedAuthorityTypeHandler;
import com.jusfoun.security.support.token.Token;
import com.jusfoun.security.support.token.factory.TokenFactory;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import tk.mybatis.mapper.annotation.ColumnType;

/**
 * 描述 : 用户认证信息模型. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午3:11:05
 */
@ApiModel
@JsonIgnoreProperties(value = {"handler"})
@Table(name = "token_user_details")
public class TokenUserDetails implements UserDetails, CredentialsContainer {
	private static final long serialVersionUID = 1574825783668756453L;

	/**
	 * 主键
	 */
	@ApiModelProperty("ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 客户端ID
	 */
	@ApiModelProperty("客户端ID")
	@Column(name = "client_id")
	private String clientId;

	/**
	 * 用户名称
	 */
	@ApiModelProperty("用户名称")
	private String username;

	/**
	 * 用户密码
	 */
	@ApiModelProperty("用户密码")
	@Transient
	private String password;

	/**
	 * 真实姓名
	 */
	@ApiModelProperty("真实姓名")
	private String realname;

	/**
	 * 权限集合
	 */
	@ApiModelProperty("权限集合")
	@ColumnType(column = "authorities", jdbcType = JdbcType.BLOB, typeHandler = RawGrantedAuthorityTypeHandler.class)
	private Collection<? extends GrantedAuthority> authorities;

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
	@Column(name = "account_non_locked")
	private boolean accountNonLocked;

	/**
	 * 账户是否过期
	 */
	@ApiModelProperty("账户是否过期")
	@Column(name = "account_non_expired")
	private boolean accountNonExpired;

	/**
	 * 密码是否过期
	 */
	@ApiModelProperty("密码是否过期")
	@Column(name = "credentials_non_expired")
	private boolean credentialsNonExpired;

	/**
	 * 认证token
	 */
	@ApiModelProperty("认证token")
	@Column(name = "access_token")
	private String accessToken;

	/**
	 * 刷新token
	 */
	@ApiModelProperty("刷新token")
	@Column(name = "refresh_token")
	private String refreshToken;

	/**
	 * 有效期
	 */
	@ApiModelProperty("有效期")
	@Column(name = "expires_in")
	private Integer expiresIn;
	/**
	 * access_token失效时间
	 */
	@ApiModelProperty("access_token失效时间")
	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "access_token_exp_time")
	private Date accessTokenExpTime;

	/**
	 * refresh_token失效时间
	 */
	@ApiModelProperty("refresh_token失效时间")
	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "refresh_token_exp_time")
	private Date refreshTokenExpTime;

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "create_time")
	private Date createTime;

	public TokenUserDetails() {
	}

	public TokenUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public TokenUserDetails(String username, Collection<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.authorities = authorities;
	}

	public TokenUserDetails(Long userId, String clientId, String username, String realname, Boolean authenticated, Boolean enabled, Boolean accountNonLocked, Integer expiresIn,
			Date accessTokenExpTime, Date refreshTokenExpTime, String accessToken, String refreshToken, Date createTime, Collection<? extends GrantedAuthority> authorities) {
		this.userId = userId;
		this.clientId = clientId;
		this.username = username;
		this.realname = realname;
		this.authenticated = authenticated;
		this.enabled = enabled;
		this.accountNonLocked = accountNonLocked;
		this.expiresIn = expiresIn;
		this.accessTokenExpTime = accessTokenExpTime;
		this.refreshTokenExpTime = refreshTokenExpTime;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.createTime = createTime;
		this.authorities = authorities;
	}

	/**
	 * 描述 : 创建用户信息. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月2日 下午7:46:27
	 * @param sysUser
	 *            系统用户信息
	 * @return token用户信息
	 */
	public static TokenUserDetails build(SysUser sysUser) {
		Assert.notNull(sysUser, "Parameter 'sysUser' is null !");
		// TODO 这里现将role放入token中方便前端处理
		TokenUserDetails tokenUser = new TokenUserDetails(sysUser.getUsername(), sysUser.getPassword(),
				sysUser.getAuthorities().parallelStream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toSet()));
		tokenUser.setUserId(sysUser.getId());
		tokenUser.setRealname(sysUser.getRealName());
		tokenUser.setEnabled(true);
		tokenUser.setAuthenticated(true);
		tokenUser.setAccountNonLocked(true);
		tokenUser.setAccountNonExpired(true);
		tokenUser.setCredentialsNonExpired(true);
		return tokenUser;
	}

	/**
	 * 描述 : 创建TokenUser对象. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月2日 下午7:44:55
	 * @param sysUser
	 *            登录的系统用户信息
	 * @param tokenFactory
	 *            token生成器
	 * @param clientDetails
	 *            客户端信息
	 * @return token信息
	 */
	public static TokenUserDetails build(SysUser sysUser, TokenFactory tokenFactory, ClientDetails clientDetails) {
		Assert.notNull(sysUser, "Parameter 'sysUser' is null !");
		return token(build(sysUser), tokenFactory, clientDetails);
	}

	/**
	 * 描述 : 生成token信息. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月2日 下午7:43:59
	 * @param userDetails
	 *            需要生成token的对象
	 * @param tokenFactory
	 *            token生成器
	 * @param clientDetails
	 *            客户端信息
	 * @return
	 */
	public static TokenUserDetails token(TokenUserDetails userDetails, TokenFactory tokenFactory, ClientDetails clientDetails) {
		Assert.notNull(userDetails, "Parameter 'tokenUserDetails' is null !");
		// 生成新的accessToken和refreshToken
		userDetails.setClientId(clientDetails.getClientId());
		userDetails.setExpiresIn(clientDetails.getAccessTokenValidity());
		userDetails.setCreateTime(new Date());
		userDetails.setAccessTokenExpTime(calculateExpiryDate(clientDetails.getAccessTokenValidity()));
		userDetails.setRefreshTokenExpTime(calculateExpiryDate(clientDetails.getRefreshTokenValidity()));
		// 有工厂生成token信息
		Token accessToken = tokenFactory.createAccessToken(clientDetails, userDetails);
		Token refreshtoken = tokenFactory.createRefreshToken(clientDetails, userDetails);
		userDetails.setAccessToken(accessToken.getToken());
		userDetails.setRefreshToken(refreshtoken.getToken());
		return userDetails;
	}

	/**
	 * 描述 : access_token是否过期. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月2日 下午7:30:47
	 * @return true-过期，false-未过期
	 */
	public boolean hasAccessTokenExpired() {
		return new DateTime(getAccessTokenExpTime()).isBeforeNow();
	}

	/**
	 * 描述 : refresh_token是否过期. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月2日 下午7:29:44
	 * @return true-过期,false-未过期
	 */
	public boolean hasRefreshTokenExpired() {
		return new DateTime(getRefreshTokenExpTime()).isBeforeNow();
	}

	/**
	 * 描述 : 根据当前时间和有效期计算token过期时间. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月2日 下午7:28:16
	 * @param expiryTimeInSeconds
	 *            token有效期
	 * @return token过期时间
	 */
	private static Date calculateExpiryDate(Integer expiryTimeInSeconds) {
		return DateTime.now().plusSeconds(expiryTimeInSeconds).toDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getExpiresIn() {
		if (!hasAccessTokenExpired()) {
			Duration duration = new Duration(DateTime.now(), new DateTime(getAccessTokenExpTime()));
			return duration.toStandardSeconds().getSeconds();
		}
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getAccessTokenExpTime() {
		return accessTokenExpTime;
	}

	public void setAccessTokenExpTime(Date accessTokenExpTime) {
		this.accessTokenExpTime = accessTokenExpTime;
	}

	public Date getRefreshTokenExpTime() {
		return refreshTokenExpTime;
	}

	public void setRefreshTokenExpTime(Date refreshTokenExpTime) {
		this.refreshTokenExpTime = refreshTokenExpTime;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public void eraseCredentials() {
		password = null;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
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

	@Override
	public boolean isAccountNonLocked() {
		return accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public String[] grantedAuthorities() {
		if (authorities != null && !authorities.isEmpty()) {
			return authorities.parallelStream().map(t -> t.getAuthority()).toArray(String[]::new);
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accessToken == null) ? 0 : accessToken.hashCode());
		result = prime * result + ((accessTokenExpTime == null) ? 0 : accessTokenExpTime.hashCode());
		result = prime * result + (accountNonExpired ? 1231 : 1237);
		result = prime * result + (accountNonLocked ? 1231 : 1237);
		result = prime * result + (authenticated ? 1231 : 1237);
		result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
		result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + (credentialsNonExpired ? 1231 : 1237);
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((expiresIn == null) ? 0 : expiresIn.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((realname == null) ? 0 : realname.hashCode());
		result = prime * result + ((refreshToken == null) ? 0 : refreshToken.hashCode());
		result = prime * result + ((refreshTokenExpTime == null) ? 0 : refreshTokenExpTime.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokenUserDetails other = (TokenUserDetails) obj;
		if (accessToken == null) {
			if (other.accessToken != null)
				return false;
		} else if (!accessToken.equals(other.accessToken))
			return false;
		if (accessTokenExpTime == null) {
			if (other.accessTokenExpTime != null)
				return false;
		} else if (!accessTokenExpTime.equals(other.accessTokenExpTime))
			return false;
		if (accountNonExpired != other.accountNonExpired)
			return false;
		if (accountNonLocked != other.accountNonLocked)
			return false;
		if (authenticated != other.authenticated)
			return false;
		if (authorities == null) {
			if (other.authorities != null)
				return false;
		} else if (!authorities.equals(other.authorities))
			return false;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (credentialsNonExpired != other.credentialsNonExpired)
			return false;
		if (enabled != other.enabled)
			return false;
		if (expiresIn == null) {
			if (other.expiresIn != null)
				return false;
		} else if (!expiresIn.equals(other.expiresIn))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (realname == null) {
			if (other.realname != null)
				return false;
		} else if (!realname.equals(other.realname))
			return false;
		if (refreshToken == null) {
			if (other.refreshToken != null)
				return false;
		} else if (!refreshToken.equals(other.refreshToken))
			return false;
		if (refreshTokenExpTime == null) {
			if (other.refreshTokenExpTime != null)
				return false;
		} else if (!refreshTokenExpTime.equals(other.refreshTokenExpTime))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TokenUserDetails [id=" + id + ", userId=" + userId + ", clientId=" + clientId + ", username=" + username + ", password=" + password + ", realname=" + realname
				+ ", authorities=" + authorities + ", enabled=" + enabled + ", authenticated=" + authenticated + ", accountNonLocked=" + accountNonLocked + ", accountNonExpired="
				+ accountNonExpired + ", credentialsNonExpired=" + credentialsNonExpired + ", accessToken=" + accessToken + ", refreshToken=" + refreshToken + ", expiresIn="
				+ expiresIn + ", accessTokenExpTime=" + accessTokenExpTime + ", refreshTokenExpTime=" + refreshTokenExpTime + ", createTime=" + createTime + "]";
	}

}