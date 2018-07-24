package com.jusfoun.service;

import com.jusfoun.common.base.service.BaseService;
import com.jusfoun.common.exception.ServiceException;
import com.jusfoun.entity.TArea;

/**
 * 描述:地区管理业务接口. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月24日 上午11:04:56
 */
public interface TAreaService extends BaseService<TArea> {

	/**
	 * 描述: 查询地区信息树图，并可根据地区名称检索. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月24日 上午11:03:58
	 * @param areaName
	 *            地区名称
	 * @return 地区根节点
	 */
	TArea selectAreaTree(String areaName) throws ServiceException;

	/**
	 * 描述: 根据ID查询地区及子孙节点. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月24日 下午3:50:48
	 * @param id
	 *            主键
	 * @return 地区信息及子孙节点
	 * @throws ServiceException
	 */
	TArea selectTreeById(Long id) throws ServiceException;

}
