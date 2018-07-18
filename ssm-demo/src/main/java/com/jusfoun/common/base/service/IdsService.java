package com.jusfoun.common.base.service;

import tk.mybatis.mapper.common.IdsMapper;

/**
 * 描述 :包含有ID主键的实体类型继承改接口实现ID批量的操作. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月15日 下午5:57:18
 */
public interface IdsService<T> extends IdsMapper<T> {
}
