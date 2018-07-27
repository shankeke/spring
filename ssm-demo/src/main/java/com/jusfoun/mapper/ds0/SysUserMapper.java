package com.jusfoun.mapper.ds0;

import com.jusfoun.common.mybatis.mapper.BaseIdableWithAssociateMapper;
import com.jusfoun.entity.SysUser;

public interface SysUserMapper extends BaseIdableWithAssociateMapper<SysUser> {

	SysUser selectByUsername(String username);

}