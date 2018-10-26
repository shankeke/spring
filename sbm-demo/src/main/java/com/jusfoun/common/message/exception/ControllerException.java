package com.jusfoun.common.message.exception;

import com.jusfoun.common.message.result.ErrType;

/**
 * 说明： Controller层异常. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午10:59:11
 */
public class ControllerException extends CoreException {
	private static final long serialVersionUID = -7428495813034319173L;

	public ControllerException(Integer code) {
		super(code);
	}

	public ControllerException(Integer code, String message) {
		super(code, message);
	}

	public ControllerException(ErrType type) {
		this(type.getCode(), type.getMessage());
	}

	public ControllerException(ErrType type, String message) {
		this(type.getCode(), message);
	}

	public ControllerException(Integer code, String message, Throwable cause) {
		super(code, message, cause);
	}

	public ControllerException(ServiceException e) {
		super(e.getCode(), e.getMessage(), e.getCause());
	}

}
