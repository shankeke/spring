package com.jusfoun.common.mybatis.mapper;

import com.jusfoun.common.mybatis.mapper.delete.DeleteByPrimaryKeysMapper;
import com.jusfoun.common.mybatis.mapper.delete.DeleteListByPrimaryKeyMapper;
import com.jusfoun.common.mybatis.mapper.insert.InsertListWithPrimaryKeyMapper;
import com.jusfoun.common.mybatis.mapper.insert.ReplaceListWithPrimaryKeyMapper;
import com.jusfoun.common.mybatis.mapper.insert.ReplaceMapper;
import com.jusfoun.common.mybatis.mapper.select.SelectByPrimaryKeysMapper;
import com.jusfoun.common.mybatis.mapper.update.UpdateListByPrimaryKeyMapper;
import com.jusfoun.common.mybatis.mapper.update.UpdateListByPrimaryKeySelectiveMapper;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.base.delete.DeleteByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.select.ExistsWithPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.select.SelectByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeySelectiveMapper;
import tk.mybatis.mapper.common.special.InsertUseGeneratedKeysMapper;

/**
 * 描述:存在主键的实体根据主键参数进行增删改查操作接口. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午10:26:17
 */
@RegisterMapper
public interface MyIdableMapper<T>
		extends //
			InsertListWithPrimaryKeyMapper<T>, //
			InsertUseGeneratedKeysMapper<T>, //
			ReplaceMapper<T>, //
			ReplaceListWithPrimaryKeyMapper<T>, //
			UpdateByPrimaryKeyMapper<T>, //
			UpdateByPrimaryKeySelectiveMapper<T>, //
			UpdateListByPrimaryKeyMapper<T>, //
			UpdateListByPrimaryKeySelectiveMapper<T>, //
			DeleteByPrimaryKeyMapper<T>, //
			DeleteByPrimaryKeysMapper<T>, //
			DeleteListByPrimaryKeyMapper<T>, //
			SelectByPrimaryKeyMapper<T>, //
			SelectByPrimaryKeysMapper<T>, //
			IdsMapper<T>, //
			ExistsWithPrimaryKeyMapper<T>//
{
}
