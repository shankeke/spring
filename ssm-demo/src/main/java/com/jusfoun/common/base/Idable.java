package com.jusfoun.common.base;

import java.io.Serializable;

/**
 * 描述: ID属性模型. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月24日 下午12:52:54
 */
public interface Idable<PK extends Serializable> extends Serializable {

	/**
	 * 描述: 获取主键信息. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月25日 下午5:47:33
	 * @return 主键信息
	 */
	PK getId();

	/**
	 * 描述:设置主键信息. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月25日 下午5:47:58
	 * @param id
	 *            主键信息
	 */
	void setId(PK id);

	/**
	 * 描述:判断ID是否为空. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月12日 下午2:39:50
	 * @return ID是否为空
	 */
	default boolean idIsNull() {
		return getId() == null;
	}

	/**
	 * 描述:判断ID是否不为空. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月12日 下午2:39:50
	 * @return ID是否不为空
	 */
	default boolean idIsNotNull() {
		return !idIsNull();
	}
}
