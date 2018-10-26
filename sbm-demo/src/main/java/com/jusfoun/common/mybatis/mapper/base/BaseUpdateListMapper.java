package com.jusfoun.common.mybatis.mapper.base;

import com.jusfoun.common.mybatis.mapper.update.UpdateListByPrimaryKeyMapper;
import com.jusfoun.common.mybatis.mapper.update.UpdateListByPrimaryKeySelectiveMapper;

import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 说明：批量更新接口，该接口类适用于存在主键的数据表，更新记录时传入的数据记录需要存在主键值. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月21日 下午7:05:13
 */
@RegisterMapper
public interface BaseUpdateListMapper<T>
		extends//
			UpdateListByPrimaryKeyMapper<T>, //
			UpdateListByPrimaryKeySelectiveMapper<T>//
{

}
