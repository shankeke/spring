package com.jusfoun.common.mybatis.mapper.delete;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;

/**
 * 描述 : 根据条件进行批量删除. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月21日 上午11:44:11
 */
public interface DeleteListMapper<T> {

	/**
	 * 描述 :根据条件进行批量删除，依照方法参数集合中每个实体的非空字段为条件删除对应记录. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月4日 下午5:46:27
	 * @param recordList
	 *            要删除的集合
	 * @return 影响的数据条数
	 */
	@DeleteProvider(type = DeleteListByPrimaryKeyProvider.class, method = "dynamicSQL")
	int deleteList(List<T> recordList);
}
