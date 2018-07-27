package com.jusfoun.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.jusfoun.common.cache.CacheConsts;
import com.jusfoun.common.enums.UsingStatus;
import com.jusfoun.common.exception.ServiceException;
import com.jusfoun.common.message.result.ErrType;
import com.jusfoun.common.mybatis.mapper.MyBaseMapper;
import com.jusfoun.common.mybatis.mapper.MyIdableMapper;
import com.jusfoun.common.mybatis.mapper.extend.BaseWithAssociateSelectMapper;
import com.jusfoun.common.util.list.IListUtil;
import com.jusfoun.entity.SysModule;
import com.jusfoun.entity.SysRole;
import com.jusfoun.entity.SysRoleModule;
import com.jusfoun.mapper.ds0.SysRoleMapper;
import com.jusfoun.mapper.ds0.SysRoleModuleMapper;
import com.jusfoun.service.SysRoleService;

/**
 * 描述:系统角色业务接口实现. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月23日 下午5:22:38
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysRoleModuleMapper sysRoleModuleMapper;

	@Override
	public BaseWithAssociateSelectMapper<SysRole> getBaseWithAssociateSelectMapper() {
		return sysRoleMapper;
	}

	@Override
	public MyIdableMapper<SysRole> getMyIdableMapper() {
		return sysRoleMapper;
	}

	@Override
	public MyBaseMapper<SysRole> getMyBaseMapper() {
		return sysRoleMapper;
	}

	@Override
	public void saveWithAssociate(SysRole sysRole) throws ServiceException {
		if (sysRole == null) {
			throw new ServiceException(ErrType.SYSROLE_ENTITY_NULL);
		}
		try {
			sysRole.setStatus(UsingStatus.Enable.getValue());
			Date createTime = new Date();
			sysRole.setCreateDate(createTime);
			sysRole.setUpdateDate(createTime);
			sysRoleMapper.insert(sysRole);

			// 保存关系
			Set<SysModule> sysModules = sysRole.getSysModules();
			if (IListUtil.hasData(sysModules)) {
				Long roleId = sysRole.getId();
				List<SysRoleModule> list = new ArrayList<SysRoleModule>();
				SysRoleModule record = null;
				for (SysModule t : sysModules) {
					record = new SysRoleModule();
					record.setModuleId(t.getId());
					record.setRoleId(roleId);
					list.add(record);
				}
				sysRoleModuleMapper.insertList(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ErrType.SYSROLE_SAVE_ERROR);
		}
	}

	// 修改角色的时候需要同时修改用户的权限，所以需要同时清理权限的缓存信息
	@CacheEvict(value = CacheConsts.CACHE_SECURITY, allEntries = true)
	@Override
	public void updateWithAssociate(SysRole sysRole) throws ServiceException {
		if (sysRole == null) {
			throw new ServiceException(ErrType.SYSROLE_ENTITY_NULL);
		}
		try {
			sysRole.setUpdateDate(new Date());
			sysRoleMapper.updateByPrimaryKeySelective(sysRole);

			// 删除原来的关系
			Long roleId = sysRole.getId();
			SysRoleModule record = new SysRoleModule();
			record.setRoleId(roleId);
			sysRoleModuleMapper.delete(record);

			Set<SysModule> sysModules = sysRole.getSysModules();
			if (IListUtil.hasData(sysModules)) {
				// 保存新关系
				List<SysRoleModule> list = new ArrayList<SysRoleModule>();
				for (SysModule t : sysModules) {
					record = new SysRoleModule();
					record.setModuleId(t.getId());
					record.setRoleId(roleId);
					list.add(record);
				}
				sysRoleModuleMapper.insertList(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ErrType.SYSROLE_UPDATE_ERROR);
		}
	}

	// 删除角色的时候需要同时修改用户的权限，所以需要同时清理权限的缓存信息
	@CacheEvict(value = CacheConsts.CACHE_SECURITY, allEntries = true)
	@Override
	public void deleteRoleWithModules(Long id) throws ServiceException {
		try {
			// 删除角色对应的权限关系
			SysRoleModule t = new SysRoleModule();
			t.setRoleId(id);
			sysRoleModuleMapper.delete(t);

			// 删除角色
			sysRoleMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ErrType.SYSROLE_DELETE_ERROR);
		}
	}
}
