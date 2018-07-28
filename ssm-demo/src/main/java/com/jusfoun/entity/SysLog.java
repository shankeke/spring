package com.jusfoun.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import com.jusfoun.common.base.entity.extend.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述 :系统操作日志信息. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年1月5日 上午9:09:21
 */
@ApiModel
@Table(name = "sys_log")
public class SysLog extends BaseEntity<SysLog> {
	private static final long serialVersionUID = -4647350019312928242L;

	/**
	 * 访问IP
	 */
	@ApiModelProperty("访问IP")
	@Column(name = "remote_host")
	private String remoteHost;

	/**
	 * 远程端口
	 */
	@ApiModelProperty("远程端口")
	@Column(name = "remote_port")
	private Integer remotePort;

	/**
	 * 请求路径
	 */
	@ApiModelProperty("请求路径")
	@Column(name = "request_url")
	private String requestUrl;

	/**
	 * 请求地址
	 */
	@ApiModelProperty("请求地址")
	@Column(name = "request_uri")
	private String requestUri;

	/**
	 * 部门ID
	 */
	@ApiModelProperty("部门ID")
	@Column(name = "gov_id")
	private Long govId;

	/**
	 * 部门名称
	 */
	@ApiModelProperty("部门名称")
	@Column(name = "gov_name")
	private String govName;

	/**
	 * 访问人员名称
	 */
	@ApiModelProperty("访问人员名称")
	private String username;
	/**
	 * 真实姓名
	 */
	@ApiModelProperty("真实姓名")
	@Column(name = "real_name")
	private String realName;
	/**
	 * 地区名称
	 */
	@ApiModelProperty("地区名称")
	@Column(name = "area_name")
	private String areaName;

	/**
	 * 访问路径
	 */
	@ApiModelProperty("访问路径")
	@Column(name = "module_path")
	private String modulePath;

	/**
	 * 模块名称
	 */
	@ApiModelProperty("模块名称")
	@Column(name = "module_name")
	private String moduleName;

	/**
	 * 访问结果（0-失败，1-成功）
	 */
	@ApiModelProperty("访问结果（0-失败，1-成功）")
	@Column(name = "result_type")
	private Boolean resultType;

	@Override
	public String initOrderByClause() {
		return "create_date DESC";
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * 获取访问IP
	 *
	 * @return remote_host - 访问IP
	 */
	public String getRemoteHost() {
		return remoteHost;
	}

	/**
	 * 设置访问IP
	 *
	 * @param remoteHost
	 *            访问IP
	 */
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost == null ? null : remoteHost.trim();
	}

	/**
	 * 获取远程端口
	 *
	 * @return remote_port - 远程端口
	 */
	public Integer getRemotePort() {
		return remotePort;
	}

	/**
	 * 设置远程端口
	 *
	 * @param remotePort
	 *            远程端口
	 */
	public void setRemotePort(Integer remotePort) {
		this.remotePort = remotePort;
	}

	/**
	 * 获取请求路径
	 *
	 * @return request_url - 请求路径
	 */
	public String getRequestUrl() {
		return requestUrl;
	}

	/**
	 * 设置请求路径
	 *
	 * @param requestUrl
	 *            请求路径
	 */
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl == null ? null : requestUrl.trim();
	}

	/**
	 * 获取请求地址
	 *
	 * @return request_uri - 请求地址
	 */
	public String getRequestUri() {
		return requestUri;
	}

	/**
	 * 设置请求地址
	 *
	 * @param requestUri
	 *            请求地址
	 */
	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri == null ? null : requestUri.trim();
	}

	/**
	 * 获取部门ID
	 *
	 * @return gov_id - 部门ID
	 */
	public Long getGovId() {
		return govId;
	}

	/**
	 * 设置部门ID
	 *
	 * @param govId
	 *            部门ID
	 */
	public void setGovId(Long govId) {
		this.govId = govId;
	}

	/**
	 * 获取部门名称
	 *
	 * @return gov_name - 部门名称
	 */
	public String getGovName() {
		return govName;
	}

	/**
	 * 设置部门名称
	 *
	 * @param govName
	 *            部门名称
	 */
	public void setGovName(String govName) {
		this.govName = govName == null ? null : govName.trim();
	}

	/**
	 * 获取访问人员名称
	 *
	 * @return username - 访问人员名称
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置访问人员名称
	 *
	 * @param username
	 *            访问人员名称
	 */
	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	/**
	 * 获取地区名称
	 *
	 * @return area_name - 地区名称
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * 设置地区名称
	 *
	 * @param areaName
	 *            地区名称
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName == null ? null : areaName.trim();
	}

	/**
	 * 获取访问路径
	 *
	 * @return module_path - 访问路径
	 */
	public String getModulePath() {
		return modulePath;
	}

	/**
	 * 设置访问路径
	 *
	 * @param modulePath
	 *            访问路径
	 */
	public void setModulePath(String modulePath) {
		this.modulePath = modulePath == null ? null : modulePath.trim();
	}

	/**
	 * 获取模块名称
	 *
	 * @return module_name - 模块名称
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * 设置模块名称
	 *
	 * @param moduleName
	 *            模块名称
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName == null ? null : moduleName.trim();
	}

	/**
	 * 获取访问结果（0-失败，1-成功）
	 *
	 * @return result_type - 访问结果（0-失败，1-成功）
	 */
	public Boolean getResultType() {
		return resultType;
	}

	/**
	 * 设置访问结果（0-失败，1-成功）
	 *
	 * @param resultType
	 *            访问结果（0-失败，1-成功）
	 */
	public void setResultType(Boolean resultType) {
		this.resultType = resultType;
	}

}