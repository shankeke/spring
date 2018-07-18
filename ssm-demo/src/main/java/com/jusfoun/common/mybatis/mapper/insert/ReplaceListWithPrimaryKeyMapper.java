package com.jusfoun.common.mybatis.mapper.insert;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

/**
 * 描述 : 覆盖式批量插入，需要指定记录的主键值. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月22日 上午10:43:18
 */
public interface ReplaceListWithPrimaryKeyMapper<T> {

	/**
	 * 描述:覆盖式批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，该接口限制实体包含主键属性并且插入时指定主键值。<br>
	 * 假如表中的一个旧记录与一个用于PRIMARY KEY或一个UNIQUE索引的新记录具有相同的值，则在新记录被插入之前，旧记录被删除。
	 * 注意，除非表有一个PRIMARY
	 * KEY或UNIQUE索引，否则，使用一个REPLACE语句没有意义。该语句会与INSERT相同，因为没有索引被用于确定是否新行复制了其它的行。
	 * <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月7日 下午2:12:35
	 * @param recordList
	 *            插入的数据集合
	 * @return 影响的数据条数
	 */
	@Options(useGeneratedKeys = false, keyProperty = "id")
	@InsertProvider(type = ReplaceListWithPrimaryKeyProvider.class, method = "dynamicSQL")
	int replaceListWithPrimaryKey(List<T> recordList);

}
