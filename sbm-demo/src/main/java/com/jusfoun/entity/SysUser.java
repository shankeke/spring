package com.jusfoun.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.jusfoun.common.base.extend.entity.BaseEntity;
import com.jusfoun.common.utils.ICollections;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 描述 : 系统用户信息. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年1月5日 上午9:10:43
 */
@ApiModel
@JsonIgnoreProperties(value = {"handler"})
@Table(name = "sys_user")
public class SysUser extends BaseEntity<SysUser> {
	private static final long serialVersionUID = -1543606832463879178L;

	/**
	 * 登录名
	 */
	@ApiModelProperty("登录名")
	private String username;

	/**
	 * 真实姓名
	 */
	@ApiModelProperty("真实姓名")
	@Column(name = "real_name")
	private String realName;

	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
	@Column(name = "password")
	private String password;

	/**
	 * 所属机构标识符
	 */
	@ApiModelProperty("部门ID")
	@Column(name = "gov_id")
	private Long govId;

	/**
	 * 机构名称
	 */
	@ApiModelProperty("部门名称")
	@Transient
	private String govName;

	/**
	 * 性别
	 */
	@ApiModelProperty("性别")
	private Integer gender;

	/**
	 * 固话
	 */
	@ApiModelProperty("电话")
	private String tel;

	/**
	 * 手机号码
	 */
	@ApiModelProperty("手机")
	private String mobile;

	/**
	 * 电子邮箱
	 */
	@ApiModelProperty("邮箱")
	private String email;

	@ApiModelProperty("地址")
	private String address;

	@ApiModelProperty("是否是系统管理员：0-否，1-是")
	@JsonIgnore
	@Column(name = "is_admin")
	private Boolean isAdmin;

	/**
	 * 角色列表
	 */
	@ApiModelProperty("角色列表")
	@Transient
	@JsonInclude(Include.NON_NULL)
	private Set<SysRole> sysRoles;

	@ApiModelProperty("角色列表")
	@Transient
	@JsonInclude(Include.NON_NULL)
	private Set<String> roleNames;

	@ApiModelProperty("权限列表")
	@JsonIgnore
	@Transient
	private Set<GrantedAuthority> authorities = new HashSet<>();

	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public Set<GrantedAuthority> getAuthorities() {
		if (ICollections.hasElements(authorities)) {
			return authorities;
		}
		Set<GrantedAuthority> userAuthotities = new HashSet<GrantedAuthority>();
		sysRoles = getSysRoles();
		if (ICollections.hasElements(sysRoles)) {
			for (SysRole role : sysRoles) {
				if (ICollections.hasElements(role.getSysModules())) {
					userAuthotities.addAll(role.getSysModules().parallelStream().map(authority -> new SimpleGrantedAuthority(authority.getUrl())).collect(Collectors.toSet()));
				}
			}
		}
		return userAuthotities;
	}

	/**
	 * 描述 : 如果角色不为空则获取角色列表. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 下午2:49:40
	 * @return
	 */
	public Set<String> getRoleNames() {
		if (ICollections.hasElements(getSysRoles())) {
			return getSysRoles().parallelStream().map(t -> t.getName()).collect(Collectors.toSet());
		}
		return roleNames;
	}

	@Override
	public String initOrderByClause() {
		return "username ASC";
	}

	public void setRoleNames(Set<String> roleNames) {
		this.roleNames = roleNames;
	}

	public String getGovName() {
		return govName;
	}

	public void setGovName(String govName) {
		this.govName = govName;
	}

	public Set<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(Set<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取登录名
	 *
	 * @return USER_NAME - 登录名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置登录名
	 *
	 * @param userName
	 *            登录名
	 */
	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	/**
	 * 获取密码
	 *
	 * @return PASSWORD - 密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置密码
	 *
	 * @param password
	 *            密码
	 */
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	/**
	 * 获取所属机构标识符
	 *
	 * @return GOV_ID - 所属机构标识符
	 */
	public Long getGovId() {
		return govId;
	}

	/**
	 * 设置所属机构标识符
	 *
	 * @param govId
	 *            所属机构标识符
	 */
	public void setGovId(Long govId) {
		this.govId = govId;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isAdmin() {
		return isAdmin == null ? false : isAdmin;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

}