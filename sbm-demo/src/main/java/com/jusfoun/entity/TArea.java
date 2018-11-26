package com.jusfoun.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.jusfoun.common.base.id.Idable;
import com.jusfoun.common.base.page.IPageable;
import com.jusfoun.common.base.tree.Treeable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明：地区信息. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月24日 上午10:08:21
 */
@ApiModel
@Table(name = "t_area")
public class TArea extends IPageable implements Treeable<TArea>, Idable<Long> {
	private static final long serialVersionUID = -3980406855769490927L;

	/**
	 * 主键
	 */
	@ApiModelProperty("主键")
	@Id
	private Long id;

	/**
	 * 地区全称
	 */
	@ApiModelProperty("地区全称")
	@Column(name = "area_name")
	private String areaName;

	/**
	 * 父级地区主键
	 */
	@ApiModelProperty("父级地区主键")
	@Column(name = "parent_id")
	private Long parentId;

	/**
	 * 父级地区全称
	 */
	@ApiModelProperty("父级地区全称")
	@Column(name = "parent_name")
	private String parentName;

	/**
	 * 级别
	 */
	@ApiModelProperty("级别")
	private Byte level;

	/**
	 * 地区子集
	 */
	@ApiModelProperty("地区子集")
	private List<TArea> subList;

	/**
	 * 是否是叶子节点
	 */
	@ApiModelProperty("是否是叶子节点")
	@Transient
	private boolean leaf;

	@Override
	public String initOrderByClause() {
		return "id ASC";
	}

	@Override
	public String[] matchFeilds() {
		return new String[]{areaName};
	}

	@Override
	public boolean isLeaf() {
		return leaf;
	}

	@Override
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	@Override
	public List<TArea> getSubList() {
		return subList;
	}

	@Override
	public void setSubList(List<TArea> subList) {
		this.subList = subList;
	}

	/**
	 * 获取ID
	 *
	 * @return id - ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置ID
	 *
	 * @param id
	 *            ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取栏目名
	 *
	 * @return area_name - 栏目名
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * 设置栏目名
	 *
	 * @param areaName
	 *            栏目名
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName == null ? null : areaName.trim();
	}

	/**
	 * 获取父栏目
	 *
	 * @return parent_id - 父栏目
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 设置父栏目
	 *
	 * @param parentId
	 *            父栏目
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	/**
	 * 获取级别
	 *
	 * @return level - 级别
	 */
	public Byte getLevel() {
		return level;
	}

	/**
	 * 设置级别
	 *
	 * @param level
	 *            级别
	 */
	public void setLevel(Byte level) {
		this.level = level;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((areaName == null) ? 0 : areaName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (leaf ? 1231 : 1237);
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ((parentName == null) ? 0 : parentName.hashCode());
		result = prime * result + ((subList == null) ? 0 : subList.hashCode());
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
		TArea other = (TArea) obj;
		if (areaName == null) {
			if (other.areaName != null)
				return false;
		} else if (!areaName.equals(other.areaName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (leaf != other.leaf)
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (parentName == null) {
			if (other.parentName != null)
				return false;
		} else if (!parentName.equals(other.parentName))
			return false;
		if (subList == null) {
			if (other.subList != null)
				return false;
		} else if (!subList.equals(other.subList))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TArea [id=" + id + ", areaName=" + areaName + ", parentId=" + parentId + ", parentName=" + parentName + ", level=" + level + ", subList=" + subList + ", leaf="
				+ leaf + "]";
	}

}