package com.jusfoun.mapper.ds1;

import org.apache.ibatis.annotations.Mapper;

import com.jusfoun.common.mybatis.mapper.extend.BaseIdableWithAssociateMapper;
import com.jusfoun.entity.TCountry;

@Mapper
public interface TCountryMapper extends BaseIdableWithAssociateMapper<TCountry> {
}