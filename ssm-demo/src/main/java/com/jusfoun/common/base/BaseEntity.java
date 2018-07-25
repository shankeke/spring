package com.jusfoun.common.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jusfoun.common.base.page.IPageable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述 : 基础MODEL类. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午10:35:16
 */
@ApiModel
@JsonIgnoreProperties(value = {"handler"})
public abstract class BaseEntity<T> extends IPageable implements Serializable {
	private static final long serialVersionUID = 1957941391153967331L;

	@ApiModelProperty("主键")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@ApiModelProperty("状态")
	@Column(name = "status")
	protected Integer status;

	@ApiModelProperty("创建日期")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "create_date")
	protected Date createDate;

	@ApiModelProperty("创建人ID")
	@Column(name = "creator_id")
	protected Long creatorId;

	@ApiModelProperty("创建人姓名")
	@Column(name = "creator_name")
	protected String creatorName;

	@ApiModelProperty("修改日期")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "update_date")
	protected Date updateDate;

	@ApiModelProperty("修改人ID")
	@Column(name = "updater_id")
	protected Long updaterId;

	@ApiModelProperty("修改人姓名")
	@Column(name = "updater_name")
	protected String updaterName;

	@ApiModelProperty("备注")
	@Column(name = "remark")
	protected String remark;

	/**
	 * 描述:判断ID是否为空. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月12日 下午2:39:50
	 * @return ID是否为空
	 */
	public boolean idIsNull() {
		return id == null;
	}

	/**
	 * 描述:判断ID是否不为空. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月12日 下午2:39:50
	 * @return ID是否不为空
	 */
	public boolean idIsNotNull() {
		return !idIsNull();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(Long updaterId) {
		this.updaterId = updaterId;
	}

	public String getUpdaterName() {
		return updaterName;
	}

	public void setUpdaterName(String updaterName) {
		this.updaterName = updaterName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String initOrderByClause() {
		return null;
	}

}
