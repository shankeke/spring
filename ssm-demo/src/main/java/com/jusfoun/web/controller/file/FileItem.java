package com.jusfoun.web.controller.file;

import java.io.Serializable;

/**
 * 描述:上传文件包装类. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月11日 下午4:21:16
 */
public class FileItem implements Serializable {
	private static final long serialVersionUID = -3547441100756863690L;

	/**
	 * 文件类型
	 */
	private String contentType;

	/**
	 * 文件原名
	 */
	private String originalFilename;

	/**
	 * 文件新名称
	 */
	private String fileName;

	/**
	 * 文件路径
	 */
	private String filePath;

	public FileItem() {
	}

	public FileItem(String contentType, String originalFilename, String fileName, String filePath) {
		this.contentType = contentType;
		this.originalFilename = originalFilename;
		this.fileName = fileName;
		this.filePath = filePath;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
