package com.jusfoun.common.mybatis.mapper.select;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 描述 : 根据主键集合查询. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月22日 下午2:48:07
 */
@RegisterMapper
public interface SelectByPrimaryKeysMapper<T> {

	/**
	 * 描述 : 根据主键集合进行查询，类中只有存在一个带有@Id注解的字段. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月4日 下午5:51:44
	 * @param keys
	 *            主键集合，对应放在sql中in条件中
	 * @return 查询结果
	 */
	@SelectProvider(type = SelectByPrimaryKeysProvider.class, method = "dynamicSQL")
	List<T> selectByPrimaryKeys(List<?> keys);

}
