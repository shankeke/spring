package com.jusfoun.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明：角色权限关联模型. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年1月5日 上午9:10:21
 */
@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "sys_role_module")
public class SysRoleModule implements Serializable {
	private static final long serialVersionUID = -8437861697252568978L;

	@ApiModelProperty("角色ID")
	@Column(name = "role_id")
	private Long roleId;

	@ApiModelProperty("模块ID")
	@Column(name = "module_id")
	private Long moduleId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((moduleId == null) ? 0 : moduleId.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
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
		SysRoleModule other = (SysRoleModule) obj;
		if (moduleId == null) {
			if (other.moduleId != null)
				return false;
		} else if (!moduleId.equals(other.moduleId))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SysRoleModule [roleId=" + roleId + ", moduleId=" + moduleId + "]";
	}

}