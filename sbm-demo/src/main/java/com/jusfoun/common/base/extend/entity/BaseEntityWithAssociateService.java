package com.jusfoun.common.base.extend.entity;

import com.jusfoun.common.base.service.BaseExtensionService;

/**
 * 说明： 继承BaseEntity的实体业务操作通用方法接口实现. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午10:35:45
 */
public interface BaseEntityWithAssociateService<T extends BaseEntity<T>> extends BaseEntityService<T>, BaseExtensionService<T> {
}
