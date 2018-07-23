package com.jusfoun.common.result;

import com.jusfoun.common.mybatis.typehandler.enumtype.IntegerValueEnum;

/**
 * 描述 : 业务异常枚举类. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年10月11日 下午7:37:37
 */
public enum ErrType implements IntegerValueEnum {

	SUCCESS(0, "调用成功"), //
	FAILED(1, "调用失败"), //
	EMPTY(2, "暂无数据"), //
	PARAMETERS_IS_NULL_ERROR(3, "参数为空"), //
	PARAMETERS_IS_INVALIDAT_ERROR(4, "参数格式错误"), //
	ERROR(9, "系统内部异常"), //

	/**
	 * 权限和数据
	 */
	AUTH_FAILED(401, "认证错误"), //
	UN_AUTHORIZED(403, "未授权的请求"), //
	NOT_FOUND(404, "找不到请求的网页"), //
	MOTHED_DISABLE(405, "方法禁用"), //
	SERVICE_ERROR(500, "服务出错"), //

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
	SYSMODULE_ENTITY_NULL(1601, "系统权限实体为空"), //
	SYSMODULE_ENTITY_EXIST_ERROR(1602, "系统权限已经存在"), //
	SYSMODULE_ENTITY_NOT_EXIST(1603, "系统权限不存在"), //
	SYSMODULE_SAVE_ERROR(1604, "系统权限保存异常"), //
	SYSMODULE_UPDATE_ERROR(1605, "系统权限更新异常"), //
	SYSMODULE_DELETE_ERROR(1606, "系统权限删除异常"), //
	SYSMODULE_QUERY_LIST_ERROR(1607, "系统权限列表查询异常"), //
	SYSMODULE_QUERY_INFO_ERROR(1608, "系统权限详情查询异常"), //

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

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public static ErrType valueOf(Integer code) {
		for (ErrType type : values()) {
			if (type.getCode().intValue() == code.intValue()) {
				return type;
			}
		}
		return ErrType.SUCCESS;
	}

	public static String getMessageByCode(Integer code) {
		for (ErrType type : values()) {
			if (type.getCode().intValue() == code.intValue()) {
				return type.getMessage();
			}
		}
		return null;
	}

}
