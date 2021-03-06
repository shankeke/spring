package com.shanke.excel;


/**
 * 描述 : 结果包装类. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016-8-26 下午1:38:04
 */
public class BaseResult {

	private int code;// 错误码
	private String message;// 错误信息

	private Object content;// 数据

	public BaseResult() {
		this.setType(ResultType.SUCC);
	}

	public BaseResult(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public BaseResult(int code, String message, Object content) {
		this.code = code;
		this.message = message;
		this.content = content;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	/**
	 * 描述 : 设置处理结果. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-8-30 上午10:29:09
	 * @param type
	 *            处理结果类型，默认采用类型type的处理消息message
	 */
	public void setType(ResultType type) {
		this.setCode(type.getCode());
		this.setMessage(type.getMessage());
	}

	/**
	 * 描述 : 设置处理结果. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-8-30 上午10:28:25
	 * @param type
	 *            处理结果类型
	 * @param message
	 *            处理结果信息
	 */
	public void setType(ResultType type, String message) {
		this.setCode(type.getCode());
		this.setMessage(message);
	}

	/**
	 * 描述 : 结果枚举. <br>
	 * <p>
	 * 
	 * Copyright (c) 2016 优识云创(YSYC)
	 * 
	 * @author Uknower-yjw
	 * @date 2016-8-25 上午10:56:33
	 */
	public enum ResultType {

		SUCC(0, "请求处理成功"), //
		UNKOWN(-1, "请求处理返回结果失败"), //
		ERROR(9, "请求处理异常"), //
		FAIL(110, "请求处理失败"), //
		VALIDATE_ERROR(100, "数据校验有误"), //
		SIG_ERROR(1303, "参数校验有误"), //
		ACCESS_TOKEN_ERROR(1304, "登录信息有误，请重新登录"), //
		NOMORE(10000, "没有更多数据");

		private final int code;
		private final String message;

		private ResultType(int code, String message) {
			this.code = code;
			this.message = message;
		}

		public int getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}

		public static ResultType valueOf(int code) {
			for (ResultType type : values()) {
				if (type.getCode() == code) {
					return type;
				}
			}
			return UNKOWN;
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseResult other = (BaseResult) obj;
		if (code != other.code)
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseResult [code=" + code + ", message=" + message
				+ ", content=" + content + "]";
	}
}
