package com.jusfoun.common.exception;

import com.jusfoun.common.result.ErrType;

/**
 * 描述 : 定义业务异常. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月13日 上午10:08:47
 */
public class CoreException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Integer code;// 状态码
	private String message;// 异常信息

	public CoreException() {
	}

	public CoreException(Integer code) {
		this(code, ErrType.getMessageByCode(code));
	}

	public CoreException(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public CoreException(ErrType type) {
		this(type.getCode(), type.getMessage());
	}

	public CoreException(ErrType type, String message) {
		this(type.getCode(), message);
	}

	public CoreException(Integer code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}

}
