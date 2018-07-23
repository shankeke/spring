package com.jusfoun.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import com.jusfoun.common.base.service.BaseEntityWithAssociateService;
import com.jusfoun.common.exception.ServiceException;
import com.jusfoun.entity.SysModule;

/**
 * 描述 : 系统权限管理. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年10月12日 下午5:50:25
 */
public interface SysModuleService extends BaseEntityWithAssociateService<SysModule> {

	/**
	 * 描述 : 初始化系统权限. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月12日 上午9:46:11
	 * @param root
	 *            根权限对象，包含子权限集合则要保存子权限
	 * @throws ServiceException
	 */
	void initSysModules(SysModule root) throws ServiceException;

	/**
	 * 描述 :根据父节点ID查询权限列表. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月20日 上午9:16:03
	 * @param roleId
	 *            角色ID
	 * @return 权限列表
	 * @throws ServiceException
	 */
	List<SysModule> selectByParentId(Long roleId) throws ServiceException;

	/**
	 * 描述 : 查询系统已有的所有权限值. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月12日 上午9:46:11
	 * @throws ServiceException
	 */
	Set<String> selectUrlByClientId(String clientId) throws ServiceException;

	/**
	 * 描述 : 查询权限对象. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月5日 下午5:07:15
	 * @param clientId
	 * @return
	 * @throws ServiceException
	 */
	Collection<? extends GrantedAuthority> selectAuthorities(String clientId) throws ServiceException;

}
