package com.jusfoun.service;

import com.jusfoun.common.base.entity.extend.BaseEntityWithAssociateService;
import com.jusfoun.common.message.exception.ServiceException;
import com.jusfoun.entity.SysRole;

/**
 * 描述 : 角色管理. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 下午4:29:06
 */
public interface SysRoleService extends BaseEntityWithAssociateService<SysRole> {

	/**
	 * 描述 : 保存角色和角色的权限关系. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 下午4:27:41
	 * @param sysRole
	 *            角色
	 * @throws ServiceException
	 */
	void saveWithAssociate(SysRole sysRole) throws ServiceException;

	/**
	 * 描述 : 更新角色和角色的权限关系. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 下午4:27:41
	 * @param sysRole
	 *            角色
	 * @throws ServiceException
	 */
	void updateWithAssociate(SysRole sysRole) throws ServiceException;

	/**
	 * 描述 : 删除角色信息和角色对应的权限. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月2日 上午11:12:45
	 * @param id
	 *            记录ID
	 * @throws ServiceException
	 */
	void deleteRoleWithModules(Long id) throws ServiceException;

}
