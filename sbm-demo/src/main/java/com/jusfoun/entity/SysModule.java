package com.jusfoun.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.jusfoun.common.base.extend.entity.BaseEntity;
import com.jusfoun.common.base.tree.Treeable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 描述 :系统权限模型. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年1月5日 上午9:09:48
 */
@ApiModel
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root")
@XmlType(name = "SysModule", propOrder = { //
		"name", //
		"url", //
		"type", //
		"clientId", //
		"subList" //
})
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "sys_module")
public class SysModule extends BaseEntity<SysModule> implements Treeable<SysModule> {
	private static final long serialVersionUID = -518969013141251551L;

	/**
	 * 权限名称
	 */
	@ApiModelProperty("权限名称")
	@XmlElement(name = "name")
	private String name;

	/**
	 * 父节点标识符
	 */
	@ApiModelProperty("父节点ID")
	@Column(name = "parent_id")
	@XmlTransient
	private Long parentId;

	/**
	 * 类型
	 */
	@ApiModelProperty("类型")
	@XmlElement(name = "type")
	private Integer type;

	/**
	 * 客户端ID
	 */
	@ApiModelProperty("客户端ID")
	@Column(name = "client_id")
	@JsonIgnore
	@XmlElement(name = "clientId")
	private String clientId;

	/**
	 * 链接
	 */
	@ApiModelProperty("链接")
	@XmlElement(name = "url")
	private String url;

	/**
	 * 子节点
	 */
	@ApiModelProperty("子节点")
	@Transient
	@JsonInclude(Include.NON_NULL)
	@XmlElementWrapper(name = "sysModules")
	@XmlElement(name = "sysModule")
	private List<SysModule> subList;

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
		if (subList == null)
			subList = new ArrayList<SysModule>();
		subList.add(sysModule);
	}

	/**
	 * 是否是叶子节点
	 */
	@ApiModelProperty("是否是叶子节点")
	@Transient
	@XmlTransient
	private boolean leaf;

	@Override
	public List<SysModule> getSubList() {
		return subList;
	}

	@Override
	public void setSubList(List<SysModule> subList) {
		this.subList = subList;
	}

	@Override
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	@Override
	public boolean isLeaf() {
		return leaf;
	}

	@Override
	public String[] matchFeilds() {
		return new String[]{name};
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
		result = prime * result + (leaf ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ((subList == null) ? 0 : subList.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysModule other = (SysModule) obj;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (leaf != other.leaf)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (subList == null) {
			if (other.subList != null)
				return false;
		} else if (!subList.equals(other.subList))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SysModule [name=" + name + ", parentId=" + parentId + ", type=" + type + ", clientId=" + clientId + ", url=" + url + ", subList=" + subList + ", leaf=" + leaf
				+ "]";
	}
}