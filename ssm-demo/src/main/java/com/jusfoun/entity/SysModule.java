package com.jusfoun.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.jusfoun.common.base.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 描述 :系统权限模型. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年1月5日 上午9:09:48
 */
@ApiModel
@JsonIgnoreProperties(value = {"handler"})
@Table(name = "sys_module")
public class SysModule extends BaseEntity<SysModule> {
	private static final long serialVersionUID = -518969013141251551L;

	/**
	 * 权限名称
	 */
	@ApiModelProperty("权限名称")
	private String name;

	/**
	 * 父节点标识符
	 */
	@ApiModelProperty("父节点ID")
	@Column(name = "parent_id")
	private Long parentId;

	/**
	 * 类型
	 */
	@ApiModelProperty("类型")
	private Integer type;

	/**
	 * 客户端ID
	 */
	@ApiModelProperty("客户端ID")
	@Column(name = "client_id")
	@JsonIgnore
	private String clientId;

	/**
	 * 链接
	 */
	@ApiModelProperty("链接")
	private String url;

	/**
	 * 子节点
	 */
	@ApiModelProperty("子节点")
	@Transient
	@JsonInclude(Include.NON_NULL)
	private List<SysModule> sysModules;

	public SysModule() {
	}

	public SysModule(String name, String url, Integer type) {
		this.name = name;
		this.type = type;
		this.url = url;
	}

	/**
	 * 描述 : 添加一个子节点. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月12日 上午10:47:17
	 * @param sysModule
	 */
	public void add(SysModule sysModule) {
		if (sysModules == null)
			sysModules = new ArrayList<SysModule>();
		sysModules.add(sysModule);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<SysModule> getSysModules() {
		return sysModules;
	}

	public void setSysModules(List<SysModule> sysModules) {
		this.sysModules = sysModules;
	}

}