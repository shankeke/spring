package com.jusfoun.common.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jusfoun.common.base.service.BaseWithAssociateService;
import com.jusfoun.common.exception.ServiceException;
import com.jusfoun.common.mybatis.IPage;
import com.jusfoun.common.mybatis.mapper.BaseWithAssociateSelectMapper;

/**
 * 描述 :一些涉及到关联数据的查询接口. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月21日 下午12:56:29
 */
public abstract class BaseWithAssociateServiceImpl<T> extends BaseServiceImpl<T> implements BaseWithAssociateService<T> {

	@Autowired
	protected BaseWithAssociateSelectMapper<T> baseWithAssociateSelectMapper;

	@Override
	public Integer selectCountWithAssociate(Map<String, Object> params) throws ServiceException {
		return baseWithAssociateSelectMapper.selectCountWithAssociate(params);
	}

	@Override
	public List<T> selectListWithAssociate(Map<String, Object> params, String orderByClause) throws ServiceException {
		startOrderBy(orderByClause);// 排序
		return baseWithAssociateSelectMapper.selectListWithAssociate(params);
	}

	@Override
	public List<T> selectListWithAssociate(Map<String, Object> params) throws ServiceException {
		return selectListWithAssociate(params, "");
	}

	@Override
	public List<T> selectListWithAssociate(Map<String, Object> params, IPage<T> page) throws ServiceException {
		startPage(page);
		return baseWithAssociateSelectMapper.selectListWithAssociate(params);
	}

	@Override
	public IPage<T> selectPageWithAssociate(Map<String, Object> params, IPage<T> page) throws ServiceException {
		Integer totalCount = selectCountWithAssociate(params);
		if (totalCount <= 0)
			return new IPage<T>();

		if (page == null)
			page = new IPage<T>(IPage.DEFAULT_PAGENUM, totalCount);

		page.setTotalCount(totalCount);
		page.setList(selectListWithAssociate(params, page));

		return page;
	}

	@Override
	public T selectOneWithAssociate(Map<String, Object> params) throws ServiceException {
		return baseWithAssociateSelectMapper.selectOneWithAssociate(params);
	}

	@Override
	public T selectPKWithAssociate(Object id) throws ServiceException {
		return selectPKWithAssociate("id", id);
	}

	@Override
	public T selectPKWithAssociate(String primaryKeyName, Object primaryKeyValue) throws ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(primaryKeyName, primaryKeyValue);// 默认的主键名称为id，也可以修改改参数同时传入主键名称和主键值
		return selectOneWithAssociate(params);
	}

}
