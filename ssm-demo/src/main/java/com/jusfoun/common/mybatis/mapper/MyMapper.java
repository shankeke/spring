package com.jusfoun.common.mybatis.mapper;

import com.jusfoun.common.mybatis.mapper.base.BaseDeleteListMapper;
import com.jusfoun.common.mybatis.mapper.base.BaseReplaceMapper;
import com.jusfoun.common.mybatis.mapper.base.BaseUpdateListMapper;
import com.jusfoun.common.mybatis.mapper.insert.InsertListWithPrimaryKeyMapper;
import com.jusfoun.common.mybatis.mapper.select.SelectByPrimaryKeysMapper;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 描述 : 被继承的<code>Mapper</code>，一般业务Mapper继承它. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午10:26:17
 */
@RegisterMapper
public interface MyMapper<T> extends //
		Mapper<T>, //
		MySqlMapper<T>, //
		InsertListWithPrimaryKeyMapper<T>, //
		SelectByPrimaryKeysMapper<T>, //
		BaseReplaceMapper<T>, //
		BaseDeleteListMapper<T>, //
		BaseUpdateListMapper<T>//
{
	// FIXME 特别注意，该接口不能被mybatis mapper扫描器扫描到，否则会出错
}
