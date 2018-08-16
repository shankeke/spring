package com.jusfoun.mapper.ds1;

import org.apache.ibatis.annotations.Mapper;

import com.jusfoun.common.base.tree.TreeableMapper;
import com.jusfoun.common.mybatis.mapper.extend.BaseIdableWithAssociateMapper;
import com.jusfoun.entity.TArea;

@Mapper
public interface TAreaMapper extends BaseIdableWithAssociateMapper<TArea> ,TreeableMapper<TArea, Long> {

	 
}