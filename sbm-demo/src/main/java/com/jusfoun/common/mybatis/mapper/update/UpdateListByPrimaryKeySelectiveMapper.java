package com.jusfoun.common.mybatis.mapper.update;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.UpdateProvider;

import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 说明： 根据主键批量修改，需要指定每条数据的主键值，并且只修改非空字段<br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月22日 上午10:43:18
 */
@RegisterMapper
public interface UpdateListByPrimaryKeySelectiveMapper<T> {

	/**
	 * 说明：根据主键批量修改实体非空字段，null值不会被更新。 <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月7日 下午2:16:17
	 * @param recordList
	 *            修改的数据列表
	 * @return 影响的数据条数
	 */
	@Options(useCache = false, useGeneratedKeys = false)
	@UpdateProvider(type = UpdateListByPrimaryKeyProvider.class, method = "dynamicSQL")
	int updateListByPrimaryKeySelective(List<T> recordList);

}
