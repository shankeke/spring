package com.jusfoun.common.mybatis.typehandler.enumtype;

import java.util.Arrays;

/**
 * 描述:可取常量枚举接口. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年1月17日 下午3:32:53
 */
public interface ValueEnum<T> {

	/**
	 * 描述: 获取枚举常量值. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月17日 下午4:41:47
	 * @return 枚举常量值
	 */
	T getValue();

	/**
	 * 描述:判断常量值是否在常量集合中. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年5月3日 上午9:37:42
	 * @param values
	 *            目标常量集合
	 * @return 是否目标常量集合中
	 */
	@SuppressWarnings("unchecked")
	default boolean valIn(T... values) {
		return Arrays.asList(values).contains(getValue());
	}

	/**
	 * 描述:判断状态值是否不在目标常量集合中. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年5月3日 上午9:37:42
	 * @param values
	 *            目标常量集合
	 * @return 是否不在目标常量集合中
	 */
	@SuppressWarnings("unchecked")
	default boolean valNotIn(T... values) {
		return !valIn(values);
	}

}
