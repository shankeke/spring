package com.jusfoun.common.base.extend.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jusfoun.common.base.id.Idable;
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
public abstract class BaseEntity<T> extends IPageable implements Idable<Long> {
	private static final long serialVersionUID = 1957941391153967331L;

	@ApiModelProperty("主键")
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Override
	public Long getId() {
		return id;
	}

	@Override
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((creatorId == null) ? 0 : creatorId.hashCode());
		result = prime * result + ((creatorName == null) ? 0 : creatorName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((updateDate == null) ? 0 : updateDate.hashCode());
		result = prime * result + ((updaterId == null) ? 0 : updaterId.hashCode());
		result = prime * result + ((updaterName == null) ? 0 : updaterName.hashCode());
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
		BaseEntity<?> other = (BaseEntity<?>) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (creatorId == null) {
			if (other.creatorId != null)
				return false;
		} else if (!creatorId.equals(other.creatorId))
			return false;
		if (creatorName == null) {
			if (other.creatorName != null)
				return false;
		} else if (!creatorName.equals(other.creatorName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (updateDate == null) {
			if (other.updateDate != null)
				return false;
		} else if (!updateDate.equals(other.updateDate))
			return false;
		if (updaterId == null) {
			if (other.updaterId != null)
				return false;
		} else if (!updaterId.equals(other.updaterId))
			return false;
		if (updaterName == null) {
			if (other.updaterName != null)
				return false;
		} else if (!updaterName.equals(other.updaterName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseEntity [id=" + id + ", status=" + status + ", createDate=" + createDate + ", creatorId=" + creatorId + ", creatorName=" + creatorName + ", updateDate="
				+ updateDate + ", updaterId=" + updaterId + ", updaterName=" + updaterName + ", remark=" + remark + "]";
	}

}
