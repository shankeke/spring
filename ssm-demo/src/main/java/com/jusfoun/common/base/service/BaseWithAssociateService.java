package com.jusfoun.common.base.service;

/**
 * 描述 :一些涉及到关联数据的查询接口. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月16日 上午11:14:47
 */
public interface BaseWithAssociateService<T> extends BaseService<T>, BaseWithAssociateSelectService<T> {
}