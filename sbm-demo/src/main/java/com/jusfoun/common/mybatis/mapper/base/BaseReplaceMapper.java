package com.jusfoun.common.mybatis.mapper.base;

import com.jusfoun.common.mybatis.mapper.insert.ReplaceListWithPrimaryKeyMapper;
import com.jusfoun.common.mybatis.mapper.insert.ReplaceMapper;

import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 说明：替换式插入基础接口，该接口类适用于支持替换式插入的数据库，如果插入的数据表存在单个主键或者联合主键，当插入的数据在数据库中存在对应主键的记录时新数据覆盖原来的记录，否则插入新的记录.
 * <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月21日 下午7:08:40
 */
@RegisterMapper
public interface BaseReplaceMapper<T>
		extends//
			ReplaceMapper<T>, //
			ReplaceListWithPrimaryKeyMapper<T>//
{

}
