package com.jusfoun.common.base.id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

/**
 * 说明： Long类型作为ID. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月24日 下午12:52:54
 */
public class LongIdable implements Idable<Long> {
	private static final long serialVersionUID = 9126190618948914748L;

	@ApiModelProperty("主键")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
