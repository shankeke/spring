package com.jusfoun.common.result;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述 :定义接口返回的数据包装. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月13日 上午10:04:28
 */
@ApiModel
@JsonIgnoreProperties(value = {"handler"})
public class BaseResponse<T> implements Serializable {
	private static final long serialVersionUID = 3218508078738927801L;

	/**
	 * 状态码
	 */
	@ApiModelProperty("状态码")
	private Integer code;

	/**
	 * 错误信息
	 */
	@ApiModelProperty("错误信息")
	private String message;

	/**
	 * 业务报文
	 */
	@ApiModelProperty("业务报文")
	@JsonInclude(value = Include.NON_NULL)
	private T content;

	public BaseResponse() {
	}

	public BaseResponse(Integer code) {
		this.code = code;
	}

	public BaseResponse(Integer code, String messge) {
		this.code = code;
		this.message = messge;
	}

	public BaseResponse(Integer code, String message, T content) {
		this.code = code;
		this.message = message;
		this.content = content;
	}

	public boolean ok() {
		return this.code != null && this.code.intValue() == 0;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "BaseResponse [code=" + code + ", message=" + message + ", content=" + content + "]";
	}

	/**
	 * 描述 : 基本方法. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月13日 上午10:03:33
	 * @param code
	 *            状态码
	 * @param message
	 *            状态信息
	 * @param content
	 *            业务报文
	 * @return 返回报文对象
	 */
	public static <T> BaseResponse<T> execute(Integer code, String message, T content) {
		BaseResponse<T> r = new BaseResponse<T>(code, message);
		if (content != null)
			r.setContent(content);
		return r;
	}

	public static <T> BaseResponse<T> execute(ErrType type, T content) {
		return execute(type.getCode(), type.getMessage(), content);
	}

	public static <T> BaseResponse<T> execute(Integer code, String message) {
		return execute(code, message, null);
	}

	public static <T> BaseResponse<T> execute(ErrType type) {
		return execute(type.getCode(), type.getMessage());
	}

	public static <T> BaseResponse<T> execute(Integer code) {
		return execute(code, ErrType.getMessageByCode(code));
	}

	public static <T> BaseResponse<T> fail(Integer code) {
		return execute(code);
	}

	public static <T> BaseResponse<T> fail(Integer code, String message) {
		return execute(code, message);
	}

	public static <T> BaseResponse<T> fail(ErrType type) {
		return execute(type);
	}

	public static <T> BaseResponse<T> fail(ErrType type, String message) {
		return execute(type.getCode(), message);
	}

	public static <T> BaseResponse<T> success() {
		return execute(ErrType.SUCCESS);
	}

	public static <T> BaseResponse<T> success(T content) {
		return execute(ErrType.SUCCESS, content);
	}

	public static <T> BaseResponse<T> exception(ErrType type) {
		return execute(type);
	}

	public static <T> BaseResponse<T> exception(Integer code, String message) {
		return execute(code, message);
	}

	public static <T> BaseResponse<T> exception(ErrType type, String message) {
		return execute(type.getCode(), message);
	}
}
