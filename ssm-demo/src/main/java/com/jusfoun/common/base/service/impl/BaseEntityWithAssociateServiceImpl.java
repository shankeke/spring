package com.jusfoun.common.base.service.impl;

import com.jusfoun.common.base.BaseEntity;
import com.jusfoun.common.base.service.BaseEntityWithAssociateService;
import com.jusfoun.common.exception.ServiceException;

/**
 * 描述 : 继承BaseEntity的实体业务操作通用方法接口实现. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午10:35:45
 */
public abstract class BaseEntityWithAssociateServiceImpl<T extends BaseEntity<T>> extends BaseWithAssociateServiceImpl<T> implements BaseEntityWithAssociateService<T> {

	@Override
	public int saveBase(T t) throws ServiceException {
		if (t.idIsNull()) {
			return myMapper.insertSelective(t);
		} else {
			return myMapper.updateByPrimaryKeySelective(t);// 注意：这里是更新部分字段
		}
	}

	@Override
	public int updateBase(T t) throws ServiceException {
		return myMapper.updateByPrimaryKeySelective(t);// 注意：这里是更新部分字段
	}
}
