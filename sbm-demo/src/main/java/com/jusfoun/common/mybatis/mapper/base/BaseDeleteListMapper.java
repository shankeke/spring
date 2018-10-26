package com.jusfoun.common.mybatis.mapper.base;

import com.jusfoun.common.mybatis.mapper.delete.DeleteByPrimaryKeysMapper;
import com.jusfoun.common.mybatis.mapper.delete.DeleteListByPrimaryKeyMapper;
import com.jusfoun.common.mybatis.mapper.delete.DeleteListMapper;

import tk.mybatis.mapper.annotation.RegisterMapper;

/**
 * 说明：批量删除接口. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月21日 下午7:05:13
 */
@RegisterMapper
public interface BaseDeleteListMapper<T>
		extends//
			DeleteListMapper<T>, //
			DeleteListByPrimaryKeyMapper<T>, //
			DeleteByPrimaryKeysMapper<T>//
{

}
