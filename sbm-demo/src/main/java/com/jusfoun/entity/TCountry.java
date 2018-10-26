package com.jusfoun.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jusfoun.common.base.id.Idable;
import com.jusfoun.common.base.page.IPageable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明：国家信息. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月24日 下午12:40:38
 */
@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "t_country")
public class TCountry extends IPageable implements Idable<Long>{
	private static final long serialVersionUID = -2326084818533423613L;

	/**
	 * 主键
	 */
	@ApiModelProperty("主键")
	@Id
	private Long id;

	/**
	 * 全称
	 */
	@ApiModelProperty("全称")
	@Column(name = "zh_name")
	private String zhName;

	/**
	 * 简称
	 */
	@ApiModelProperty("简称")
	@Column(name = "sh_name")
	private String shName;

	/**
	 * 英文名称
	 */
	@ApiModelProperty("英文名称")
	@Column(name = "en_name")
	private String enName;

	@Transient
	public String getAlpha() {
		if (StringUtils.isNotBlank(enName)) {
			return enName.substring(0, 1).toUpperCase();
		}
		return "其他";
	}

	@Override
	public String initOrderByClause() {
		return "en_name asc";
	}

	/**
	 * 获取主键
	 *
	 * @return id - 主键
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置主键
	 *
	 * @param id
	 *            主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取全称
	 *
	 * @return zh_name - 全称
	 */
	public String getZhName() {
		return zhName;
	}

	/**
	 * 设置全称
	 *
	 * @param zhName
	 *            全称
	 */
	public void setZhName(String zhName) {
		this.zhName = zhName == null ? null : zhName.trim();
	}

	/**
	 * 获取简称
	 *
	 * @return sh_name - 简称
	 */
	public String getShName() {
		return shName;
	}

	/**
	 * 设置简称
	 *
	 * @param shName
	 *            简称
	 */
	public void setShName(String shName) {
		this.shName = shName == null ? null : shName.trim();
	}

	/**
	 * 获取英文名称
	 *
	 * @return en_name - 英文名称
	 */
	public String getEnName() {
		return enName;
	}

	/**
	 * 设置英文名称
	 *
	 * @param enName
	 *            英文名称
	 */
	public void setEnName(String enName) {
		this.enName = enName == null ? null : enName.trim();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((enName == null) ? 0 : enName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((shName == null) ? 0 : shName.hashCode());
		result = prime * result + ((zhName == null) ? 0 : zhName.hashCode());
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
		TCountry other = (TCountry) obj;
		if (enName == null) {
			if (other.enName != null)
				return false;
		} else if (!enName.equals(other.enName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (shName == null) {
			if (other.shName != null)
				return false;
		} else if (!shName.equals(other.shName))
			return false;
		if (zhName == null) {
			if (other.zhName != null)
				return false;
		} else if (!zhName.equals(other.zhName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TCountry [id=" + id + ", zhName=" + zhName + ", shName=" + shName + ", enName=" + enName + "]";
	}
 
}