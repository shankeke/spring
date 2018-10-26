package com.jusfoun.common.mybatis.mapper.extension;

import com.jusfoun.common.mybatis.mapper.MyIdableMapper;

import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 描述:存在ID属性的实体持久层接口类，一并实现扩展的查询接口。 <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月16日 上午11:15:38
 */
@RegisterMapper
public interface BaseIdableExtensionMapper<T>
		extends //
			MyIdableMapper<T>, //
			BaseExtensionSelectMapper<T> //
{
}
