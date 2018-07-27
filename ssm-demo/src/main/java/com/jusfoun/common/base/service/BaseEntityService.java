package com.jusfoun.common.base.service;

import com.jusfoun.common.base.entity.BaseEntity;
import com.jusfoun.common.message.exception.ServiceException;

/**
 * 描述 : 继承BaseEntity的实体业务操作通用方法接口实现. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午10:35:45
 */
public interface BaseEntityService<T extends BaseEntity<T>> extends BaseIdableService<T> {

	/**
	 * 描述: 保存基础信息. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年2月26日 下午2:20:10
	 * @param t
	 *            实体基础信息
	 * @return 保存结果
	 * @throws ServiceException
	 */
	default int saveBase(T t) throws ServiceException {
		return getMyBaseMapper().insertSelective(t);
	}

	/**
	 * 描述: 更新基础信息. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年2月26日 下午2:20:21
	 * @param t
	 *            实体基础信息
	 * @return 更新结果
	 * @throws ServiceException
	 */
	default int updateBase(T t) throws ServiceException {
		return getMyIdableMapper().updateByPrimaryKeySelective(t);
	}
}
