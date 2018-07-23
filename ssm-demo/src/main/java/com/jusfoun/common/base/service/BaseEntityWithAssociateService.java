package com.jusfoun.common.base.service;

import com.jusfoun.common.base.BaseEntity;

/**
 * 描述 :继承BaseEntity的实体业务操作通用方法接口抽象. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午10:35:45
 */
public interface BaseEntityWithAssociateService<T extends BaseEntity<T>> extends BaseService<T>, BaseWithAssociateSelectService<T>, BaseEntityDmlService<T> {
}
