package com.jusfoun.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jusfoun.common.base.Idable;
import com.jusfoun.common.base.Treeable;
import com.jusfoun.common.base.page.IPageable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述:地区信息. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月24日 上午10:08:21
 */
@ApiModel
@JsonIgnoreProperties(value = {"handler"})
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
	private Integer parentId;

	/**
	 * 简称
	 */
	@ApiModelProperty("简称")
	@Column(name = "short_name")
	private String shortName;

	/**
	 * 经度
	 */
	@ApiModelProperty("经度")
	private String lng;

	/**
	 * 纬度
	 */
	@ApiModelProperty("纬度")
	private String lat;

	/**
	 * 级别
	 */
	@ApiModelProperty("级别")
	private Byte level;

	/**
	 * 定位
	 */
	@ApiModelProperty("定位")
	private String position;

	/**
	 * 排序
	 */
	@ApiModelProperty("排序")
	private Byte sort;

	/**
	 * 上下级机构标识符
	 */
	@ApiModelProperty("上下级机构标识符")
	private String flag;

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
		return new String[]{areaName, shortName};
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
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * 设置父栏目
	 *
	 * @param parentId
	 *            父栏目
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取简称
	 *
	 * @return short_name - 简称
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * 设置简称
	 *
	 * @param shortName
	 *            简称
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName == null ? null : shortName.trim();
	}

	/**
	 * 获取经度
	 *
	 * @return lng - 经度
	 */
	public String getLng() {
		return lng;
	}

	/**
	 * 设置经度
	 *
	 * @param lng
	 *            经度
	 */
	public void setLng(String lng) {
		this.lng = lng == null ? null : lng.trim();
	}

	/**
	 * 获取纬度
	 *
	 * @return lat - 纬度
	 */
	public String getLat() {
		return lat;
	}

	/**
	 * 设置纬度
	 *
	 * @param lat
	 *            纬度
	 */
	public void setLat(String lat) {
		this.lat = lat == null ? null : lat.trim();
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

	/**
	 * 获取定位
	 *
	 * @return position - 定位
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * 设置定位
	 *
	 * @param position
	 *            定位
	 */
	public void setPosition(String position) {
		this.position = position == null ? null : position.trim();
	}

	/**
	 * 获取排序
	 *
	 * @return sort - 排序
	 */
	public Byte getSort() {
		return sort;
	}

	/**
	 * 设置排序
	 *
	 * @param sort
	 *            排序
	 */
	public void setSort(Byte sort) {
		this.sort = sort;
	}

	/**
	 * 获取上下级机构标识符
	 *
	 * @return flag - 上下级机构标识符
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * 设置上下级机构标识符
	 *
	 * @param flag
	 *            上下级机构标识符
	 */
	public void setFlag(String flag) {
		this.flag = flag == null ? null : flag.trim();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((areaName == null) ? 0 : areaName.hashCode());
		result = prime * result + ((flag == null) ? 0 : flag.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((lng == null) ? 0 : lng.hashCode());
		result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((shortName == null) ? 0 : shortName.hashCode());
		result = prime * result + ((sort == null) ? 0 : sort.hashCode());
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
		TArea other = (TArea) obj;
		if (areaName == null) {
			if (other.areaName != null)
				return false;
		} else if (!areaName.equals(other.areaName))
			return false;
		if (flag == null) {
			if (other.flag != null)
				return false;
		} else if (!flag.equals(other.flag))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (lng == null) {
			if (other.lng != null)
				return false;
		} else if (!lng.equals(other.lng))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (shortName == null) {
			if (other.shortName != null)
				return false;
		} else if (!shortName.equals(other.shortName))
			return false;
		if (sort == null) {
			if (other.sort != null)
				return false;
		} else if (!sort.equals(other.sort))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TArea [id=" + id + ", areaName=" + areaName + ", parentId=" + parentId + ", shortName=" + shortName + ", lng=" + lng + ", lat=" + lat + ", level=" + level
				+ ", position=" + position + ", sort=" + sort + ", flag=" + flag + "]";
	}

}