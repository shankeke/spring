package com.jusfoun.common.mybatis.mapper.extend;

import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 描述:定义一些根据自定义特殊条件查询或者返回自定义ResultMap结果集的接口，继承该接口类记得在xml文件中定义对应的动态sql映射。 <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月16日 上午11:15:38
 */
@RegisterMapper
public interface BaseWithAssociateSelectMapper<T> {

	/**
	 * 描述: 查询数据条数. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月15日 下午4:25:23
	 * @param params
	 *            查询条件
	 * @return 符合条件的数据条数
	 */
	int selectCountWithAssociate(Map<String, Object> params);

	/**
	 * 描述: 查询符合条件的数据集合，其中返回的数据可能包含附加关联字段. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月15日 下午4:25:23
	 * @param params
	 *            查询条件
	 * @return 符合条件的数据集合
	 */
	List<T> selectListWithAssociate(Map<String, Object> params);

	/**
	 * 描述: 根据条件查询唯一一条符合条件的记录. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月15日 下午4:25:23
	 * @param params
	 *            查询条件，一般该条件最多能匹配到唯一一条数据
	 * @return 符合条件的唯一数据记录
	 */
	T selectOneWithAssociate(Map<String, Object> params);

}
