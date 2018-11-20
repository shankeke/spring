package com.jusfoun.common.base.tree;

import java.io.Serializable;
import java.util.List;

import com.jusfoun.common.message.exception.ServiceException;

/**
 * 说明：树状结构数据查询接口. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月24日 下午2:24:03
 */
public interface TreeableMapper<T extends Treeable<T>, PK extends Serializable> {

	/**
	 * 说明： 根据根节点主键查询地根节点及后代节点. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月24日 下午3:50:48
	 * @param rootId
	 *            根节点主键
	 * @return 根节点及子孙节点
	 */
	T selectTree(PK rootId);

	/**
	 * 说明： 根据父节点ID查询子集列表. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年11月15日 下午3:33:21
	 * @param parentId
	 *            父节点ID
	 * @return 子集列表
	 * @throws ServiceException
	 */
	List<T> selectByParentId(PK parentId);
}
