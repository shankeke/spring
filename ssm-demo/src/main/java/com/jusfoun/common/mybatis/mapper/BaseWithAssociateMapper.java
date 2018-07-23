package com.jusfoun.common.mybatis.mapper;

import com.jusfoun.common.mybatis.mapper.base.BaseWithAssociateSelectMapper;

/**
 * 描述:需要继承动态生成sql并且手动封装一些复杂关联查询sql的接口可以继承改接口，并实现自己的关联查询sql。 <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月16日 上午11:15:38
 */
public interface BaseWithAssociateMapper<T> extends MyMapper<T>, BaseWithAssociateSelectMapper<T> {
	// FIXME 特别注意，这里继承了BaseWithAssociateSelectMapper<T>接口，改接口下的方法需要在xml文件中手动实现
}
