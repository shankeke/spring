package com.jusfoun.mapper.ds0;

import com.jusfoun.common.mybatis.mapper.BaseWithAssociateMapper;
import com.jusfoun.entity.SysUser;

public interface SysUserMapper extends BaseWithAssociateMapper<SysUser> {

	SysUser selectByUsername(String username);

}