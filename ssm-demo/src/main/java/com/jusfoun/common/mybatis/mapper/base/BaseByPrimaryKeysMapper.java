package com.jusfoun.common.mybatis.mapper.base;

import com.jusfoun.common.mybatis.mapper.delete.DeleteByPrimaryKeysMapper;
import com.jusfoun.common.mybatis.mapper.select.SelectByPrimaryKeysMapper;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.IdsMapper;

/**
 * 描述 :根据主键集合批量操作接口. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月22日 下午2:57:48
 */
@RegisterMapper
public interface BaseByPrimaryKeysMapper<T> extends//
		IdsMapper<T>, //
		DeleteByPrimaryKeysMapper<T>, //
		SelectByPrimaryKeysMapper<T> //
{

}
