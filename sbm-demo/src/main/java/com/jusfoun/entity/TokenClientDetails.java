package com.jusfoun.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.apache.ibatis.type.JdbcType;
import org.springframework.security.core.GrantedAuthority;

import com.jusfoun.common.mybatis.typehandler.varchartype.VarcharVsStringArrayTypeHandler;
import com.jusfoun.common.utils.ICollections;
import com.jusfoun.security.ClientDetails;
import com.jusfoun.security.RawGrantedAuthority;
import com.jusfoun.security.RawGrantedAuthorityTypeHandler;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import tk.mybatis.mapper.annotation.ColumnType;

/**
 * 描述 : 客户端信息. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月30日 下午4:15:34
 */
@ApiModel
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "clientDetails")
@XmlType(name = "TokenClientDetails", propOrder = { //
		"clientId", //
		"clientSecret", //
		"resourceIds", //
		"scopes", //
		"grantTypes", //
		"accessTokenValidity", //
		"refreshTokenValidity" //
})
@Table(name = "token_client_details")
public class TokenClientDetails implements ClientDetails, Serializable {

	private static final long serialVersionUID = 3773300574613219161L;

	/**
	 * 客户端ID
	 */
	@ApiModelProperty("客户端ID")
	@Id
	@Column(name = "client_id")
	private String clientId;

	/**
	 * 客户端密码
	 */
	@ApiModelProperty("客户端密码")
	@Column(name = "client_secret")
	private String clientSecret;

	/**
	 * 资源ID
	 */
	@ApiModelProperty("资源ID")
	@XmlElementWrapper(name = "resourceIds")
	@XmlElement(name = "resourceId")
	@ColumnType(column = "resource_ids", jdbcType = JdbcType.VARCHAR, typeHandler = VarcharVsStringArrayTypeHandler.class)
	private String[] resourceIds;

	/**
	 * 作用范围
	 */
	@ApiModelProperty("作用范围")
	@XmlElementWrapper(name = "scopes")
	@XmlElement(name = "scope")
	@ColumnType(column = "scopes", jdbcType = JdbcType.VARCHAR, typeHandler = VarcharVsStringArrayTypeHandler.class)
	private String[] scopes;

	/**
	 * 授权方式
	 */
	@ApiModelProperty("授权方式")
	@XmlElementWrapper(name = "grantTypes")
	@XmlElement(name = "grantType")
	@ColumnType(column = "grant_types", jdbcType = JdbcType.VARCHAR, typeHandler = VarcharVsStringArrayTypeHandler.class)
	private String[] grantTypes;

	/**
	 * access_token有效期
	 */
	@ApiModelProperty("access_token有效期")
	@Column(name = "access_token_validity")
	private Integer accessTokenValidity;

	/**
	 * refresh_token有效期
	 */
	@ApiModelProperty("refresh_token有效期")
	@Column(name = "refresh_token_validity")
	private Integer refreshTokenValidity;

	/**
	 * 权限集合
	 */
	@ApiModelProperty("权限集合")
	@XmlTransient
	@ColumnType(column = "authorities", jdbcType = JdbcType.BLOB, typeHandler = RawGrantedAuthorityTypeHandler.class)
	private Collection<? extends GrantedAuthority> authorities;

	public TokenClientDetails() {
	}

	public TokenClientDetails(String clientId, String clientSecret) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	public TokenClientDetails(String clientId, String clientSecret, String[] resourceIds, String[] scopes, String[] grantTypes, Integer accessTokenValidity,
			Integer refreshTokenValidity, Collection<? extends GrantedAuthority> authorities) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.resourceIds = resourceIds;
		this.scopes = scopes;
		this.grantTypes = grantTypes;
		this.accessTokenValidity = accessTokenValidity;
		this.refreshTokenValidity = refreshTokenValidity;
		this.authorities = authorities;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String[] getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String[] resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String[] getScopes() {
		return scopes;
	}

	public void setScopes(String[] scopes) {
		this.scopes = scopes;
	}

	public String[] getGrantTypes() {
		return grantTypes;
	}

	public void setGrantTypes(String[] grantTypes) {
		this.grantTypes = grantTypes;
	}

	public Integer getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public Integer getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public void setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	@XmlTransient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> retainAuthorities(Collection<? extends GrantedAuthority> authorities) {
		if (!ICollections.hasElements(authorities) || !ICollections.hasElements(this.authorities)) {
			return null;
		}
		Set<String> collect = this.authorities.parallelStream().map(t -> t.getAuthority()).collect(Collectors.toSet());
		Set<String> collect1 = authorities.parallelStream().map(t -> t.getAuthority()).collect(Collectors.toSet());
		collect.retainAll(collect1);
		if (ICollections.hasElements(collect)) {
			return collect.parallelStream().map(t -> new RawGrantedAuthority(t)).collect(Collectors.toSet());
		}
		return null;
	}

}