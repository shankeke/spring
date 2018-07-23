package com.jusfoun.mapper.ds0;

import java.util.List;

import com.jusfoun.common.mybatis.mapper.BaseWithAssociateMapper;
import com.jusfoun.entity.SysModule;

public interface SysModuleMapper extends BaseWithAssociateMapper<SysModule> {

	/**
	 * 描述 :根据角色ID查询角色拥有的权限列表. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月20日 上午9:16:03
	 * @param roleId
	 *            角色ID
	 * @return 权限列表
	 */
	List<SysModule> selectByRoleId(Long roleId);

	/**
	 * 描述 :根据父节点ID查询权限列表. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月20日 上午9:16:03
	 * @param roleId
	 *            角色ID
	 * @return 权限列表
	 */
	List<SysModule> selectByParentId(Long roleId);

}