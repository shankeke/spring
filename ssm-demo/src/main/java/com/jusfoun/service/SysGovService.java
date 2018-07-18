package com.jusfoun.service;

import java.util.List;

import com.jusfoun.common.base.service.BaseWithAssociateService;
import com.jusfoun.entity.SysGov;
import com.jusfoun.entity.SysUser;

/**
 * 描述 : 组织机构管理. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 上午10:03:40
 */
public interface SysGovService extends BaseWithAssociateService<SysGov> {

	/**
	 * 描述 :根据ID 查询名称. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 上午10:53:35
	 * @param id
	 *            ID
	 * @return 名称
	 */
	String selectNameByPrimaryKey(Integer id);

	/**
	 * 描述 : 根据父节点ID查询子节点列表. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月23日 上午10:54:28
	 * @param parentId
	 *            父节点ID
	 * @return 子节点集合
	 */
	List<SysUser> selectByParentId(Integer parentId);

}
