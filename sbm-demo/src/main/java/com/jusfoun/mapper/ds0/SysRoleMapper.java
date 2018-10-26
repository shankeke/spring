package com.jusfoun.mapper.ds0;

import java.util.List;

import com.jusfoun.common.mybatis.mapper.extension.BaseIdableExtensionMapper;
import com.jusfoun.entity.SysRole;

public interface SysRoleMapper extends BaseIdableExtensionMapper<SysRole> {

	/**
	 * 说明：根据用户ID查询用户角色列表. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月20日 上午9:33:42
	 * @param userId
	 *            用户ID
	 * @return 角色列表
	 */
	List<SysRole> selectByUserId(Long userId);

	/**
	 * 说明：根据用户ID查询用户角色名称列表. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月20日 上午9:33:42
	 * @param userId
	 *            用户ID
	 * @return 角色名称列表
	 */
	List<String> selectNamesByUserId(Long userId);
}