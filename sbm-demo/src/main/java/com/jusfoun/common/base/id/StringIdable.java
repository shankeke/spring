package com.jusfoun.common.base.id;

import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

/**
 * 说明： UUID类型作为主键. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月24日 下午12:52:54
 */
public class StringIdable implements Idable<String> {
	private static final long serialVersionUID = 9126190618948914748L;

	@ApiModelProperty("主键")
	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
