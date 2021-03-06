package com.jusfoun.common.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;

/**
 * 说明：抽象一个类型处理器，使用模版方法模式构建一个模板，该模板由不同的子类去实现进而完成不同的实现逻辑，用于处理复杂的java
 * bean序列化成数据库类型和从数据库反序列化成复杂的java bean对象. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年1月17日 下午1:47:50
 */
public abstract class AbstractComplexTypeHandler<T> extends BaseTypeHandler<T> {

	/**
	 * 指定默认字符集
	 */
	protected static final String DEFAULT_CHARSET = "UTF-8";

	/**
	 * 默认的字符件的分隔符
	 */
	protected static final String DEFAULT_REGEX = ",";

	/**
	 * 说明： 将实体序列化为字符串. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月18日 上午9:16:26
	 * @param t
	 *            实体对象
	 * @return 转化后的字符串
	 */
	public abstract String translate2Str(T t);

	/**
	 * 说明： 将序列化的数据转成的字符串转化成java对象. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月18日 上午9:19:21
	 * @param result
	 *            结果字符串
	 * @return java对象
	 */
	public abstract T translate2Bean(String result);
}
