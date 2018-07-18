package com.jusfoun.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jusfoun.common.base.service.impl.BaseWithAssociateServiceImpl;
import com.jusfoun.common.cache.CacheConsts;
import com.jusfoun.common.exception.ServiceException;
import com.jusfoun.common.result.ErrType;
import com.jusfoun.common.util.list.IListUtil;
import com.jusfoun.entity.SysRole;
import com.jusfoun.entity.SysRoleUser;
import com.jusfoun.entity.SysUser;
import com.jusfoun.mapper.ds0.SysRoleUserMapper;
import com.jusfoun.mapper.ds0.SysUserMapper;
import com.jusfoun.service.SysUserService;

/**
 * 描述 :系统用户信息管理. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月24日 下午7:00:05
 */
@Service("sysUserService")
public class SysUserServiceImpl extends BaseWithAssociateServiceImpl<SysUser> implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysRoleUserMapper sysRoleUserMapper;

	@Override
	public int insertWithCache(SysUser sysUser) {
		return insertSelective(sysUser);
	}

	@CacheEvict(value = CacheConsts.CACHE_SECURITY, allEntries = true)
	@Override
	public int deleteWithRoles(Long id) throws ServiceException {
		try {
			// 删除用户角色
			SysRoleUser t = new SysRoleUser();
			t.setUserId(id);
			sysRoleUserMapper.delete(t);

			// 删除用户信息
			return sysUserMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ErrType.SYSUSER_DELETE_ERROR);
		}
	}

	@CacheEvict(value = CacheConsts.CACHE_SECURITY, allEntries = true)
	@Override
	public void updateByPrimaryKeySelectiveWithCache(SysUser sysUser) {
		sysUser.setPassword(null);
		updateByPrimaryKeySelective(sysUser);
	}

	@CacheEvict(value = CacheConsts.CACHE_SECURITY, allEntries = true)
	@Override
	public void modifyRoles(SysUser sysUser) throws ServiceException {
		SysRoleUser record = new SysRoleUser();
		record.setUserId(sysUser.getId());
		sysRoleUserMapper.delete(record);

		Set<SysRole> sysRoles = sysUser.getSysRoles();
		if (IListUtil.hasData(sysRoles)) {
			List<SysRoleUser> list = new ArrayList<SysRoleUser>();
			for (SysRole sysRole : sysRoles) {
				record = new SysRoleUser();
				record.setUserId(sysUser.getId());
				record.setRoleId(sysRole.getId());
				list.add(record);
			}
			sysRoleUserMapper.insertList(list);
		}
	}

	@Cacheable(value = CacheConsts.CACHE_SECURITY, key = "'security_cache_sysuser_' + #username", unless = "#result == null")
	@Override
	public SysUser selectByUsername(String username) throws ServiceException {
		return sysUserMapper.selectByUsername(username);
	}

	@Cacheable(value = CacheConsts.CACHE_SECURITY, key = "'security_cache_sysuser_' + #id", unless = "#result == null")
	@Override
	public SysUser selectPKWithCache(Long id) throws ServiceException {
		return selectPKWithAssociate(id);
	}

}
