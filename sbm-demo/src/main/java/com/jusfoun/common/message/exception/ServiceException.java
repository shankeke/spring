package com.jusfoun.common.message.exception;

import com.jusfoun.common.message.result.ErrType;

/**
 * 描述 :Service 层异常. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午10:59:30
 */
public class ServiceException extends CoreException {
	private static final long serialVersionUID = 1494875666602336146L;

	public ServiceException(Integer code) {
		super(code);
	}

	public ServiceException(Integer code, String message) {
		super(code, message);
	}

	public ServiceException(ErrType type) {
		this(type.getCode(), type.getMessage());
	}

	public ServiceException(ErrType type, String message) {
		this(type.getCode(), message);
	}

	public ServiceException(Integer code, String message, Throwable cause) {
		super(code, message, cause);
	}
}
