package com.jusfoun.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.jusfoun.common.cache.CacheConsts;
import com.jusfoun.common.enums.UsingStatus;
import com.jusfoun.common.message.exception.ServiceException;
import com.jusfoun.common.mybatis.mapper.MyBaseMapper;
import com.jusfoun.common.mybatis.mapper.MyIdableMapper;
import com.jusfoun.common.mybatis.mapper.extend.BaseWithAssociateSelectMapper;
import com.jusfoun.common.utils.EntityUtils;
import com.jusfoun.common.utils.ICollections;
import com.jusfoun.entity.SysModule;
import com.jusfoun.mapper.ds0.SysModuleMapper;
import com.jusfoun.security.RawGrantedAuthority;
import com.jusfoun.service.SysModuleService;

/**
 * 描述:系统权限业务接口实现. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年10月12日 下午5:50:49
 */
@Service
public class SysModuleServiceImpl implements SysModuleService {

	@Autowired
	private SysModuleMapper sysModuleMapper;

	@Override
	public BaseWithAssociateSelectMapper<SysModule> getBaseWithAssociateSelectMapper() {
		return sysModuleMapper;
	}

	@Override
	public MyIdableMapper<SysModule> getMyIdableMapper() {
		return sysModuleMapper;
	}

	@Override
	public MyBaseMapper<SysModule> getMyBaseMapper() {
		return sysModuleMapper;
	}

	// 清理所有的关于安全的缓存
	@CacheEvict(value = CacheConsts.CACHE_SECURITY, allEntries = true)
	@Override
	public void initSysModules(SysModule root) throws ServiceException {
		if (root == null)
			return;
		// 初始化一些属性
		root.setCreatorId(root.getCreatorId() == null ? 1 : root.getCreatorId());
		root.setUpdaterId(root.getUpdaterId() == null ? 1 : root.getUpdaterId());
		Date date = new Date();
		root.setCreateDate(root.getCreateDate() == null ? date : root.getCreateDate());
		root.setUpdateDate(root.getUpdateDate() == null ? date : root.getUpdateDate());

		root.setUpdaterName(StringUtils.isEmpty(root.getUpdaterName()) ? "admin" : root.getUpdaterName());
		root.setCreatorName(StringUtils.isEmpty(root.getCreatorName()) ? "admin" : root.getCreatorName());

		root.setRemark(StringUtils.isEmpty(root.getRemark()) ? root.getName() : root.getRemark());
		root.setStatus(root.getStatus() == null ? UsingStatus.ENABLE.getValue() : root.getStatus());
		saveSysModulesByLoop(root);
	}

	/**
	 * 描述 : 以递归的方式保存权限节点. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月12日 上午9:50:47
	 * @param root
	 *            根节点
	 */
	private void saveSysModulesByLoop(SysModule root) {
		if (root == null)
			return;
		sysModuleMapper.insert(root);
		// 保存子节点
		List<SysModule> list = root.getSubList();
		if (ICollections.hasElements(list)) {
			Long pid = root.getId();
			for (SysModule item : list) {
				item.setParentId(pid);
				item.setCreatorId(item.getCreatorId() == null ? root.getCreatorId() : item.getCreatorId());
				item.setUpdaterId(item.getUpdaterId() == null ? root.getUpdaterId() : item.getUpdaterId());
				item.setCreateDate(item.getCreateDate() == null ? root.getCreateDate() : item.getCreateDate());
				item.setUpdateDate(item.getUpdateDate() == null ? root.getUpdateDate() : item.getUpdateDate());
				item.setUpdaterName(StringUtils.isEmpty(item.getUpdaterName()) ? root.getUpdaterName() : item.getUpdaterName());
				item.setCreatorName(StringUtils.isEmpty(item.getCreatorName()) ? root.getCreatorName() : item.getCreatorName());
				item.setRemark(StringUtils.isEmpty(item.getRemark()) ? root.getName() : item.getRemark());
				item.setStatus(item.getStatus() == null ? root.getStatus() : item.getStatus());
				// 递归调用
				saveSysModulesByLoop(item);
			}
		}
	}

	@Cacheable(value = CacheConsts.CACHE_SECURITY, key = "'security_cache_sysmodule_' + #parentId", unless = "#result == null")
	@Override
	public List<SysModule> selectByParentId(Long parentId) throws ServiceException {
		if (parentId == null)
			parentId = 0L;
		return sysModuleMapper.selectByParentId(parentId);
	}

	@Cacheable(value = CacheConsts.CACHE_SECURITY, key = "'security_cache_sysmodule_url_'+ #clientId", unless = "#result == null")
	@Override
	public Set<String> selectUrlByClientId(String clientId) throws ServiceException {
		List<SysModule> list = null;
		if (StringUtils.isNotEmpty(clientId)) {
			SysModule t = new SysModule();
			t.setClientId(clientId);
			list = select(t);
		} else {
			list = selectAll();
		}
		if (ICollections.hasElements(list)) {
			return list.parallelStream().map(t -> t.getUrl()).collect(Collectors.toSet());
		}
		return Sets.newHashSet();
	}

	@Cacheable(value = CacheConsts.CACHE_SECURITY, key = "'security_cache_sysmodule_allauthorities'+#clientId", unless = "#result == null")
	@Override
	public Collection<? extends GrantedAuthority> selectAuthorities(String clientId) throws ServiceException {
		Set<String> urls = selectUrlByClientId(clientId);
		if (ICollections.hasElements(urls)) {
			return urls.parallelStream().map(t -> new RawGrantedAuthority(t)).collect(Collectors.toSet());
		}
		return null;
	}

	@Cacheable(value = CacheConsts.CACHE_SECURITY, key = "'security_cache_sysmodule_byRootId'+#rootId", unless = "#result == null")
	@Override
	public SysModule selectTree(Long rootId) throws ServiceException {
		return sysModuleMapper.selectTree(EntityUtils.getDefaultIfNull(rootId, 0L));
	}

}
