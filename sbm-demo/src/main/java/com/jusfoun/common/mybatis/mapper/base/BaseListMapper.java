package com.jusfoun.common.mybatis.mapper.base;

import com.jusfoun.common.mybatis.mapper.insert.InsertListWithPrimaryKeyMapper;
import com.jusfoun.common.mybatis.mapper.insert.ReplaceListWithPrimaryKeyMapper;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 说明：批量操作汇总接口. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月21日 下午7:13:30
 */
@RegisterMapper
public interface BaseListMapper<T>
		extends //
			MySqlMapper<T>, //
			InsertListWithPrimaryKeyMapper<T>, //
			ReplaceListWithPrimaryKeyMapper<T>, //
			BaseDeleteListMapper<T>, //
			BaseUpdateListMapper<T> //
{

}
