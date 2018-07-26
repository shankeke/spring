package com.jusfoun.service;

import com.jusfoun.common.base.service.BaseEntityWithAssociateService;
import com.jusfoun.common.base.tree.TreeableService;
import com.jusfoun.entity.SysGov;

/**
 * 描述 : 组织机构管理. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 上午10:03:40
 */
public interface SysGovService extends BaseEntityWithAssociateService<SysGov>, TreeableService<SysGov, Long> {

	/**
	 * 描述 :根据ID 查询名称. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 上午10:53:35
	 * @param id
	 *            ID
	 * @return 名称
	 */
	String selectNameByPrimaryKey(Long id);

}
