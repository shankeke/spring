package com.jusfoun.service;

import com.jusfoun.common.base.service.BaseIdableWithAssociateService;
import com.jusfoun.common.base.tree.TreeableAndIdableService;
import com.jusfoun.entity.TArea;

/**
 * 描述:地区管理业务接口. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月24日 上午11:04:56
 */
public interface TAreaService extends BaseIdableWithAssociateService<TArea>, TreeableAndIdableService<TArea, Long> {
}