package com.jusfoun.mapper.ds0;

import org.apache.ibatis.annotations.Mapper;

import com.jusfoun.common.mybatis.mapper.extension.BaseIdableExtensionMapper;
import com.jusfoun.entity.SysUser;

@Mapper
public interface SysUserMapper extends BaseIdableExtensionMapper<SysUser> {

	SysUser selectByUsername(String username);

}