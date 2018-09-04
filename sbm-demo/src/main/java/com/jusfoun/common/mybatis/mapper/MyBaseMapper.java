package com.jusfoun.common.mybatis.mapper;

import com.jusfoun.common.mybatis.mapper.delete.DeleteListMapper;

import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.additional.update.differ.UpdateByDifferMapper;
import tk.mybatis.mapper.additional.update.force.UpdateByPrimaryKeySelectiveForceMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.RowBoundsMapper;
import tk.mybatis.mapper.common.base.delete.DeleteMapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;
import tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper;
import tk.mybatis.mapper.common.base.select.SelectAllMapper;
import tk.mybatis.mapper.common.base.select.SelectCountMapper;
import tk.mybatis.mapper.common.base.select.SelectMapper;
import tk.mybatis.mapper.common.base.select.SelectOneMapper;
import tk.mybatis.mapper.common.example.DeleteByExampleMapper;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;
import tk.mybatis.mapper.common.example.SelectCountByExampleMapper;
import tk.mybatis.mapper.common.example.SelectOneByExampleMapper;
import tk.mybatis.mapper.common.example.UpdateByExampleMapper;
import tk.mybatis.mapper.common.example.UpdateByExampleSelectiveMapper;

/**
 * 描述:基本增删改查操作接口. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午10:26:17
 */
@RegisterMapper
public interface MyBaseMapper<T>
		extends //
			InsertMapper<T>, //
			InsertSelectiveMapper<T>, //
			InsertListMapper<T>, //
			UpdateByExampleMapper<T>, //
			UpdateByExampleSelectiveMapper<T>, //
			DeleteMapper<T>, //
			DeleteListMapper<T>, //
			DeleteByExampleMapper<T>, //
			SelectOneMapper<T>, //
			SelectOneByExampleMapper<T>, //
			SelectMapper<T>, //
			SelectAllMapper<T>, //
			SelectByExampleMapper<T>, //
			RowBoundsMapper<T>, //
			SelectCountMapper<T>, //
			SelectCountByExampleMapper<T>, //
			UpdateByDifferMapper<T>, //
			UpdateByPrimaryKeySelectiveForceMapper<T> //
{
}
