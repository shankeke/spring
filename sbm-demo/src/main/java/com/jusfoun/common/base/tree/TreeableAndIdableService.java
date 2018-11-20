package com.jusfoun.common.base.tree;

import java.io.Serializable;
import java.util.List;

import com.jusfoun.common.base.id.Idable;
import com.jusfoun.common.message.exception.ServiceException;

/**
 * 说明：树状数据结构的数据模型业务处理接口定义. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月26日 上午11:44:43
 */
public interface TreeableAndIdableService<T extends Treeable<T> & Idable<PK>, PK extends Serializable> extends TreeableService<T> {

	TreeableMapper<T, PK> getTreeableMapper();

	/**
	 * 说明： 树数据查询，可根据根节点主键和关键字检索. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月26日 上午11:46:51
	 * @param rootId
	 *            根节点主键
	 * @param keyword
	 *            检索关键字
	 * @return 根节点及符合条件的后代节点
	 * @throws ServiceException
	 */
	default T selectTree(PK rootId, String keyword) throws ServiceException {
		return selectTree(selectTree(rootId), keyword);
	}

	/**
	 * 说明：根据节点ID查询以此节点为根的所有子节点. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年11月15日 下午4:42:59
	 * @param rootId
	 *            根节点ID
	 * @return 该节点及所有的子节点树
	 * @throws ServiceException
	 */
	default T selectTree(PK rootId) throws ServiceException {
		return getTreeableMapper().selectTree(rootId);
	}

	/**
	 * 说明： 根据父节点ID查询子集列表. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年11月15日 下午4:41:16
	 * @param parentId
	 *            父节点ID
	 * @return 子集列表
	 */
	default List<T> selectByParentId(PK parentId)throws ServiceException {
		return getTreeableMapper().selectByParentId(parentId);
	}

}
