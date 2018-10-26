package com.jusfoun.common.base.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jusfoun.common.base.page.IPage;
import com.jusfoun.common.message.exception.ServiceException;
import com.jusfoun.common.mybatis.mapper.extension.BaseExtensionSelectMapper;

/**
 * 描述 :一些通用的扩展查询接口，可用于涉及到关联数据的查询等. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月21日 下午12:56:29
 */
public interface BaseExtensionService<T> extends BaseService<T> {

	BaseExtensionSelectMapper<T> getBaseExtensionSelectMapper();

	/**
	 * 描述 : 查询条数，有模糊查询等参数. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月15日 下午4:30:22
	 * @param params
	 *            查询条件
	 * @return 符合条件的数据总条数
	 * @throws ServiceException
	 */
	default int selectExtensionCount(Map<?, ?> params) throws ServiceException {
		return getBaseExtensionSelectMapper().selectExtensionCount(params);
	}

	/**
	 * 描述: 根据条件查询所有数据，附加关联字段. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2018年1月22日 下午3:02:49
	 * @param params
	 *            查询条件
	 * @param orderByClause
	 *            排序条件，根据查询的字段排序，如：“id asc,name desc”
	 * @return 查询结果集
	 * @throws ServiceException
	 */
	default List<T> selectExtensionList(Map<?, ?> params, String orderByClause) throws ServiceException {
		startOrderBy(orderByClause);// 排序
		return selectExtensionList(params);
	}

	/**
	 * 描述: 根据条件查询所有数据，附加关联字段. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月22日 下午2:59:11
	 * @param params
	 *            查询条件
	 * @return 查询结果集
	 * @throws ServiceException
	 */
	default List<T> selectExtensionList(Map<?, ?> params) throws ServiceException {
		return getBaseExtensionSelectMapper().selectExtensionList(params);
	}

	/**
	 * 描述 : 查询分页列表包含附加属性. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月15日 下午4:25:23
	 * @param params
	 *            查询条件
	 * @param page
	 *            分页信息，如果改成参数为空则不分页，查询所有
	 * @return 分页数据包含附加字段
	 * @throws ServiceException
	 */
	default List<T> selectExtensionList(Map<?, ?> params, IPage<T> page) throws ServiceException {
		startPage(page);
		return getBaseExtensionSelectMapper().selectExtensionList(params);
	}

	/**
	 * 描述 : 查询分页列表包含附加字段. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月15日 下午4:25:23
	 * @param params
	 *            查询条件
	 * @param page
	 *            分页信息，如果改成参数为空则不分页，查询所有
	 * @return 分页数据包含附加字段
	 * @throws ServiceException
	 */
	default IPage<T> selectExtensionPage(Map<?, ?> params, IPage<T> page) throws ServiceException {
		Integer totalCount = selectExtensionCount(params);
		if (totalCount <= 0)
			return new IPage<T>();

		if (page == null)
			page = new IPage<T>(IPage.DEFAULT_PAGENUM, totalCount);

		page.setTotalCount(totalCount);
		page.setList(selectExtensionList(params, page));

		return page;
	}

	/**
	 * 描述 : 根据条件查询唯一一条记录. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月15日 下午4:25:23
	 * @param params
	 *            查询条件
	 * @return 符合条件的唯一一条记录，没查到返回空，如果多条则会抛出异常
	 */
	default T selectExtensionOne(Map<?, ?> params) throws ServiceException {
		return getBaseExtensionSelectMapper().selectExtensionOne(params);
	}

	/**
	 * 描述 : 根据主键查询唯一的记录（当主键名称默认的“id”）. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月15日 下午4:25:23
	 * @param id
	 *            主键值
	 * @return 主键对应记录，没查到返回空
	 */
	default T selectExtensionPK(Object id) throws ServiceException {
		return selectExtensionPK("id", id);
	}

	/**
	 * 描述 : 根据主键查询唯一的记录（当主键名称不是默认的“id”时，指定主键名称和主键值）. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月15日 下午4:25:23
	 * @param primaryKeyName
	 *            主键名称
	 * @param primaryKeyValue
	 *            主键值
	 * @return 主键对应记录，无记录返回空
	 */
	default T selectExtensionPK(String primaryKeyName, Object primaryKeyValue) throws ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(primaryKeyName, primaryKeyValue);// 默认的主键名称为id，也可以修改改参数同时传入主键名称和主键值
		return selectExtensionOne(params);
	}

}
