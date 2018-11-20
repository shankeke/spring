package com.jusfoun.mapper.ds0;

import org.apache.ibatis.annotations.Mapper;

import com.jusfoun.common.base.tree.TreeableMapper;
import com.jusfoun.common.mybatis.mapper.extension.BaseIdableExtensionMapper;
import com.jusfoun.entity.TArea;

@Mapper
public interface TAreaMapper extends BaseIdableExtensionMapper<TArea>, TreeableMapper<TArea, Long> {

}