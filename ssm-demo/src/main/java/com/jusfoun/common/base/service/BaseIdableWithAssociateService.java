package com.jusfoun.common.base.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * 描述 : 业务层实现基类. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月12日 上午10:55:07
 */
@Transactional
public interface BaseIdableWithAssociateService<T> extends BaseWithAssociateService<T>, BaseIdableService<T> { }
