package com.jusfoun.mapper.ds0;

import com.jusfoun.common.mybatis.mapper.BaseWithAssociateSelectMapper;
import com.jusfoun.entity.SysUser;

public interface SysUserMapper extends BaseWithAssociateSelectMapper<SysUser> {

	SysUser selectByUsername(String username);

}