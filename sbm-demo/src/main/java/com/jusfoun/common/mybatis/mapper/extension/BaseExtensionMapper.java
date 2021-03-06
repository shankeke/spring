package com.jusfoun.common.mybatis.mapper.extension;

import com.jusfoun.common.mybatis.mapper.MyBaseMapper;

import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 说明：需要继承动态生成sql并且手动封装一些复杂关联查询sql的接口可以继承改接口，并实现自己的关联查询sql。 <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月16日 上午11:15:38
 */
@RegisterMapper
public interface BaseExtensionMapper<T>
		extends //
			MyBaseMapper<T>, //
			BaseExtensionSelectMapper<T> //
{
}
