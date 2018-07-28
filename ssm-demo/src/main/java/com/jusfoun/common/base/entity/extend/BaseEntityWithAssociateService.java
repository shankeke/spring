package com.jusfoun.common.base.entity.extend;

import com.jusfoun.common.base.service.BaseWithAssociateService;

/**
 * 描述 : 继承BaseEntity的实体业务操作通用方法接口实现. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午10:35:45
 */
public interface BaseEntityWithAssociateService<T extends BaseEntity<T>> extends BaseEntityService<T>, BaseWithAssociateService<T> {
}
