package com.jusfoun.mapper.ds0;

import org.apache.ibatis.annotations.Mapper;

import com.jusfoun.common.base.tree.TreeableMapper;
import com.jusfoun.common.mybatis.mapper.BaseIdableWithAssociateMapper;
import com.jusfoun.entity.SysGov;

@Mapper
public interface SysGovMapper extends BaseIdableWithAssociateMapper<SysGov>, TreeableMapper<SysGov, Long> {
	/**
	 * 描述 :根据ID 查询名称. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 上午10:53:35
	 * @param id
	 *            ID
	 * @return 名称
	 */
	String selectNameByPrimaryKey(Long id);

}