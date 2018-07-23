package com.jusfoun.common.base.service.impl;

import com.jusfoun.common.base.BaseEntity;
import com.jusfoun.common.base.service.BaseEntityService;
import com.jusfoun.common.exception.ServiceException;

/**
 * 描述 : 继承BaseEntity的实体业务操作通用方法接口实现. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午10:35:45
 */
public abstract class BaseEntityServiceImpl<T extends BaseEntity<T>> extends BaseServiceImpl<T> implements BaseEntityService<T> {

	@Override
	public int saveBase(T t) throws ServiceException {
		return myMapper.insertSelective(t);
	}

	@Override
	public int updateBase(T t) throws ServiceException {
		return myMapper.updateByPrimaryKeySelective(t);// 注意：这里是更新部分字段
	}
}
