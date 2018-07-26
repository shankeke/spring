package com.jusfoun.mapper.ds1;

import org.apache.ibatis.annotations.Mapper;

import com.jusfoun.common.base.tree.TreeableMapper;
import com.jusfoun.common.mybatis.mapper.BaseWithAssociateMapper;
import com.jusfoun.entity.TArea;

@Mapper
public interface TAreaMapper extends BaseWithAssociateMapper<TArea> ,TreeableMapper<TArea, Long> {

	 
}