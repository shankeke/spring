package com.jusfoun.mapper.ds0;

import com.jusfoun.common.mybatis.mapper.extension.BaseIdableExtensionMapper;
import com.jusfoun.entity.SysUser;

public interface SysUserMapper extends BaseIdableExtensionMapper<SysUser> {

	SysUser selectByUsername(String username);

}