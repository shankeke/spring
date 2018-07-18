package com.jusfoun.common.mybatis.mapper.insert;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

/**
 * 描述 : 批量插入，需要指定每条数据的主键值<br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月22日 上午10:43:18
 */
public interface InsertListWithPrimaryKeyMapper<T> {

	/**
	 * 描述 :批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，该接口限制实体包含主键属性并且插入时指定主键值。 <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月7日 下午2:16:17
	 * @param recordList
	 *            插入的数据集
	 * @return 影响的数据条数
	 */
	@Options(useGeneratedKeys = false, keyProperty = "id")
	@InsertProvider(type = InsertListWithPrimaryKeyProvider.class, method = "dynamicSQL")
	int insertListWithPrimaryKey(List<T> recordList);

}
