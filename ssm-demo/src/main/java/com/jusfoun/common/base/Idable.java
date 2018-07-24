package com.jusfoun.common.base;

import java.io.Serializable;

import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述: ID属性模型. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月24日 下午12:52:54
 */
@ApiModel
public class Idable<PK extends Serializable> implements Serializable {
	private static final long serialVersionUID = 3760161492605218869L;

	@Id
	@ApiModelProperty("主键")
	private PK id;

	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}

	/**
	 * 描述:判断ID是否为空. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月12日 下午2:39:50
	 * @return ID是否为空
	 */
	public boolean idIsNull() {
		return id == null;
	}

	/**
	 * 描述:判断ID是否不为空. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月12日 下午2:39:50
	 * @return ID是否不为空
	 */
	public boolean idIsNotNull() {
		return !idIsNull();
	}
}
