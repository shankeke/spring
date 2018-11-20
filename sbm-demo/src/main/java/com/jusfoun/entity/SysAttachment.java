package com.jusfoun.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.jusfoun.common.base.extend.entity.BaseEntity;
import com.jusfoun.common.enums.ObjType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Table(name = "sys_attachment")
public class SysAttachment extends BaseEntity<SysAttachment> {
	private static final long serialVersionUID = -1227356680945885302L;

	public SysAttachment() {
	}

	public SysAttachment(String contentType, String originalName, String fileName, String filePath, Long objId, Byte objType) {
		this.contentType = contentType;
		this.originalName = originalName;
		this.fileName = fileName;
		this.filePath = filePath;
		this.objId = objId;
		this.objType = objType;
	}

	/**
	 * 文件类型
	 */
	@ApiModelProperty("文件类型")
	@Column(name = "content_type")
	private String contentType;

	/**
	 * 附件原名
	 */
	@ApiModelProperty("附件原名")
	@Column(name = "original_name")
	private String originalName;

	/**
	 * 附件名称
	 */
	@ApiModelProperty("附件名称")
	@Column(name = "file_name")
	private String fileName;

	/**
	 * 文件相对路径
	 */
	@ApiModelProperty("文件相对路径")
	@Column(name = "file_path")
	private String filePath;

	/**
	 * 绑定对象ID
	 */
	@ApiModelProperty("绑定对象ID")
	@Column(name = "obj_id")
	private Long objId;

	/**
	 * 绑定对象类型:1-政府端执法监管附件；
	 * 
	 * @see ObjType
	 */
	@ApiModelProperty("绑定对象类型")
	@Column(name = "obj_type")
	private Byte objType;

	/**
	 * 获取文件类型
	 *
	 * @return content_type - 文件类型
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * 设置文件类型
	 *
	 * @param contentType
	 *            文件类型
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType == null ? null : contentType.trim();
	}

	/**
	 * 获取文件原名称
	 *
	 * @return original_name - 文件原名称
	 */
	public String getOriginalName() {
		return originalName;
	}

	/**
	 * 设置文件原名称
	 *
	 * @param originalName
	 *            文件原名称
	 */
	public void setOriginalName(String originalName) {
		this.originalName = originalName == null ? null : originalName.trim();
	}

	/**
	 * 获取上传附件名称
	 *
	 * @return file_name - 上传附件名称
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 设置上传附件名称
	 *
	 * @param fileName
	 *            上传附件名称
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName == null ? null : fileName.trim();
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 获取对象ID
	 *
	 * @return obj_id - 对象ID
	 */
	public Long getObjId() {
		return objId;
	}

	/**
	 * 设置对象ID
	 *
	 * @param objId
	 *            对象ID
	 */
	public void setObjId(Long objId) {
		this.objId = objId;
	}

	/**
	 * 获取上传对象类型:1-政府端执法监管附件；
	 *
	 * @return obj_type - 上传对象类型:1-政府端执法监管附件；
	 */
	public Byte getObjType() {
		return objType;
	}

	/**
	 * 设置上传对象类型:1-政府端执法监管附件；
	 *
	 * @param objType
	 *            上传对象类型:1-政府端执法监管附件；
	 */
	public void setObjType(Byte objType) {
		this.objType = objType;
	}

	@Override
	public String initOrderByClause() {
		return "create_date asc";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((contentType == null) ? 0 : contentType.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((filePath == null) ? 0 : filePath.hashCode());
		result = prime * result + ((objId == null) ? 0 : objId.hashCode());
		result = prime * result + ((objType == null) ? 0 : objType.hashCode());
		result = prime * result + ((originalName == null) ? 0 : originalName.hashCode());
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
		SysAttachment other = (SysAttachment) obj;
		if (contentType == null) {
			if (other.contentType != null)
				return false;
		} else if (!contentType.equals(other.contentType))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (filePath == null) {
			if (other.filePath != null)
				return false;
		} else if (!filePath.equals(other.filePath))
			return false;
		if (objId == null) {
			if (other.objId != null)
				return false;
		} else if (!objId.equals(other.objId))
			return false;
		if (objType == null) {
			if (other.objType != null)
				return false;
		} else if (!objType.equals(other.objType))
			return false;
		if (originalName == null) {
			if (other.originalName != null)
				return false;
		} else if (!originalName.equals(other.originalName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SysAttachment [contentType=" + contentType + ", originalName=" + originalName + ", fileName=" + fileName + ", filePath=" + filePath + ", objId=" + objId
				+ ", objType=" + objType + ", id=" + id + ", createDate=" + createDate + ", creatorId=" + creatorId + ", creatorName=" + creatorName + ", updateDate=" + updateDate
				+ ", updaterId=" + updaterId + ", updaterName=" + updaterName + ", remark=" + remark + "]";
	}

}