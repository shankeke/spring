package com.jusfoun.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jusfoun.common.mybatis.page.IPageable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述:国家信息. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月24日 下午12:40:38
 */
@ApiModel
@JsonIgnoreProperties(value = {"handler"})
@Table(name = "t_country")
public class TCountry extends IPageable {
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
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		TCountry other = (TCountry) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getZhName() == null ? other.getZhName() == null : this.getZhName().equals(other.getZhName()))
				&& (this.getShName() == null ? other.getShName() == null : this.getShName().equals(other.getShName()))
				&& (this.getEnName() == null ? other.getEnName() == null : this.getEnName().equals(other.getEnName()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getZhName() == null) ? 0 : getZhName().hashCode());
		result = prime * result + ((getShName() == null) ? 0 : getShName().hashCode());
		result = prime * result + ((getEnName() == null) ? 0 : getEnName().hashCode());
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", zhName=").append(zhName);
		sb.append(", shName=").append(shName);
		sb.append(", enName=").append(enName);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}