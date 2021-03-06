package com.jusfoun.common.message.result;

import com.jusfoun.common.enums.valuable.IntegerValuable;

/**
 * 说明： 业务异常枚举类. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年10月11日 下午7:37:37
 */
public enum ErrType implements IntegerValuable {

	/*********************** 基础错误 ********************** */
	SUCCESS(0, "请求成功"), //
	FAILED(1, "请求失败"), //

	/*********************** 常用http请求错误 ********************** */
	BAD_REQUEST(400, "请求参数错误"), //
	UNAUTHORIZED(401, "请求未授权"), //
	FORBIDDEN(403, "禁止访问"), //
	NOT_FOUND(404, "请求资源未找到"), //
	METHOD_NOT_ALLOWED(405, "请求方法不支持"), //
	NOT_ACCEPTABLE(406, "响应失败"), //
	PROXY_AUTHENTICATION_REQUIRED(407, "代理未授权"), //
	REQUEST_TIMEOUT(408, "请求超时"), //
	CONFLICT(409, "请求冲突"), //
	GONE(410, "资源不可用"), //
	INTERNAL_SERVER_ERROR(500, "内部服务器错误"), //
	NOT_IMPLEMENTED(501, "请求资源未实现"), //
	BAD_GATEWAY(502, "路由错误"), //
	SERVICE_UNAVAILABLE(503, "服务不可用"), //
	GATEWAY_TIMEOUT(504, "路由超时"),

	/*********************** 通用实体错误 ********************** */
	ENTITY_EMPTY(1001, "数据为空"), //
	ENTITY_EXIST(1002, "数据已经存在"), //
	ENTITY_NOT_EXIST(1003, "数据不存在"), //
	ENTITY_SAVE_ERROR(1004, "数据保存失败"), //
	ENTITY_UPDATE_ERROR(1005, "数据更新失败"), //
	ENTITY_DELETE_ERROR(1006, "数据删除失败"), //
	ENTITY_QUERY_LIST_ERROR(1007, "查询数据列表失败"), //
	ENTITY_QUERY_INFO_ERROR(1008, "查询数据详情失败"), //

	/*********************** 组织架构信息异常********************** */
	SYSGOV_ENTITY_NULL(1541, "组织架构实体为空"), //
	SYSGOV_ENTITY_EXIST_ERROR(1541, "组织架构已经存在"), //
	SYSGOV_ENTITY_NOT_EXIST(1541, "组织架构不存在"), //
	SYSGOV_SAVE_ERROR(1542, "组织架构保存异常"), //
	SYSGOV_UPDATE_ERROR(1543, "组织架构更新异常"), //
	SYSGOV_DELETE_ERROR(1544, "组织架构删除异常"), //
	SYSGOV_QUERY_LIST_ERROR(1545, "组织架构列表查询异常"), //
	SYSGOV_QUERY_INFO_ERROR(1546, "组织架构详情查询异常"), //

	/*********************** 系统用户信息异常********************** */
	SYSUSER_ENTITY_NULL(1561, "系统用户实体为空"), //
	SYSUSER_ENTITY_EXIST_ERROR(1561, "系统用户已经存在"), //
	SYSUSER_ENTITY_NOT_EXIST(1561, "系统用户不存在"), //
	SYSUSER_SAVE_ERROR(1562, "系统用户保存异常"), //
	SYSUSER_UPDATE_ERROR(1563, "系统用户更新异常"), //
	SYSUSER_DELETE_ERROR(1564, "系统用户删除异常"), //
	SYSUSER_QUERY_LIST_ERROR(1565, "系统用户列表查询异常"), //
	SYSUSER_QUERY_INFO_ERROR(1566, "系统用户详情查询异常"), //
	SYSUSER_AUTH_PASSWORD_ERROR(1567, "用户密码错误"), //
	SYSUSER_UPDATE_PASSWORD_ERROR(1568, "用户密码修改失败"), //

	/*********************** 系统角色信息异常********************** */
	SYSROLE_ENTITY_NULL(1581, "系统角色实体为空"), //
	SYSROLE_ENTITY_EXIST_ERROR(1582, "系统角色已经存在"), //
	SYSROLE_ENTITY_NOT_EXIST(1583, "系统角色不存在"), //
	SYSROLE_SAVE_ERROR(1584, "系统角色保存异常"), //
	SYSROLE_UPDATE_ERROR(1585, "系统角色更新异常"), //
	SYSROLE_DELETE_ERROR(1586, "系统角色删除异常"), //
	SYSROLE_QUERY_LIST_ERROR(1587, "系统角色列表查询异常"), //
	SYSROLE_QUERY_INFO_ERROR(1588, "系统角色详情查询异常"), //

	/*********************** 系统权限信息异常********************** */
	SYSPRIVS_ENTITY_NULL(1601, "系统权限实体为空"), //
	SYSPRIVS_ENTITY_EXIST_ERROR(1602, "系统权限已经存在"), //
	SYSPRIVS_ENTITY_NOT_EXIST(1603, "系统权限不存在"), //
	SYSPRIVS_SAVE_ERROR(1604, "系统权限保存异常"), //
	SYSPRIVS_UPDATE_ERROR(1605, "系统权限更新异常"), //
	SYSPRIVS_DELETE_ERROR(1606, "系统权限删除异常"), //
	SYSPRIVS_QUERY_LIST_ERROR(1607, "系统权限列表查询异常"), //
	SYSPRIVS_QUERY_INFO_ERROR(1608, "系统权限详情查询异常"), //

	/*********************** 系统日志信息异常********************** */
	SYSLOG_ENTITY_NULL(1621, "系统日志实体为空"), //
	SYSLOG_ENTITY_EXIST_ERROR(1622, "系统日志已经存在"), //
	SYSLOG_ENTITY_NOT_EXIST(1623, "系统日志不存在"), //
	SYSLOG_SAVE_ERROR(1624, "系统日志保存异常"), //
	SYSLOG_UPDATE_ERROR(1625, "系统日志更新异常"), //
	SYSLOG_DELETE_ERROR(1626, "系统日志删除异常"), //
	SYSLOG_QUERY_LIST_ERROR(1627, "系统日志列表查询异常"), //
	SYSLOG_QUERY_INFO_ERROR(1628, "系统日志详情查询异常"), //

	FILE_MAX_UPLOAD_SIZE_EXCEEDED_ERROR(1761, "文件过大"), //
	FILE_FORMAT_NOT_ALLOW_ERROR(1762, "文件格式错误"), //
	FILE_IO_WRITE_ERROR(1763, "文件写入失败"), //
	FILE_IO_READ_ERROR(1764, "文件读入失败"), //
	FILE_NOT_FOUND_ERROR(1765, "文件未找到");

	private final Integer code;
	private final String message;

	private ErrType(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public Integer getValue() {
		return code;
	}

	@Override
	public String getLabel() {
		return message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public static ErrType valueOf(Integer code) {
		if (code != null) {
			for (ErrType type : values()) {
				if (type.equalsTo(code)) {
					return type;
				}
			}
		}
		return ErrType.FAILED;
	}

	public static String getMessageByCode(Integer code) {
		if (code != null) {
			for (ErrType type : values()) {
				if (type.equalsTo(code)) {
					return type.getMessage();
				}
			}
		}
		return null;
	}

}
