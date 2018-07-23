package com.jusfoun.common.mybatis.typehandler.enumtype;

/**
 * 描述:可取常量且可比较大小常量值枚举接口. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年1月17日 下午3:32:53
 */
public interface ValueComparableEnum<T extends Comparable<T>> extends ValueEnum<T> {

	/**
	 * 描述: 常量值与目标常量值比较结果. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月2日 下午2:02:52
	 * @param value
	 *            目标常量值
	 * @return 比较结果
	 * @throws Exception
	 */
	default int valCompareTo(T value) throws NullPointerException {
		if (value != null) {
			return getValue().compareTo(value);
		}
		throw new NullPointerException("对比值为空");
	}

	/**
	 * 描述:常量值与目标常量值是否相等.<br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年5月3日 上午9:37:42
	 * @param value
	 *            目标常量值
	 * @return 是否相等
	 */
	default boolean valEqualsTo(T value) {
		try {
			return valCompareTo(value) == 0;
		} catch (NullPointerException e) {
			return false;
		}
	}

	/**
	 * 描述:常量值与目标常量值是否不相等. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年5月3日 上午9:37:42
	 * @param value
	 *            目标常量值
	 * @return 是否不相等
	 * @throws Exception
	 */
	default boolean valNotEqualsTo(T value) {
		return !valEqualsTo(value);
	}

	/**
	 * 描述:判断常量值是否是大于目标常量值. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年5月3日 上午9:37:42
	 * @param value
	 *            目标常量值
	 * @return 是否大于
	 */
	default boolean valGreaterThan(T value) {
		try {
			return valCompareTo(value) > 0;
		} catch (NullPointerException e) {
			return false;
		}
	}

	/**
	 * 描述:判断常量值是否是大于等于目标常量值. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年5月3日 上午9:37:42
	 * @param value
	 *            目标常量值
	 * @return 是否大于等于
	 */
	default boolean valGreaterThanOrEqualTo(T value) {
		try {
			return valCompareTo(value) >= 0;
		} catch (NullPointerException e) {
			return false;
		}
	}

	/**
	 * 描述:判断常量值是否是小于目标常量值. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年5月3日 上午9:37:42
	 * @param value
	 *            目标常量值
	 * @return 是否小于
	 */
	default boolean valLessThan(T value) {
		try {
			return valCompareTo(value) < 0;
		} catch (NullPointerException e) {
			return false;
		}
	}

	/**
	 * 描述:判断常量值是否是小于等于目标常量值. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年5月3日 上午9:37:42
	 * @param value
	 *            目标常量值
	 * @return 是否小于等于
	 */
	default boolean valLessThanOrEqualTo(T value) {
		try {
			return valCompareTo(value) <= 0;
		} catch (NullPointerException e) {
			return false;
		}
	}

}
