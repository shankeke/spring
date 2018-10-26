package com.jusfoun.common.base.tree;

import java.io.Serializable;

import com.jusfoun.common.base.id.Idable;
import com.jusfoun.common.message.exception.ServiceException;

/**
 * 说明：树状数据结构的数据模型业务处理接口定义. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月26日 上午11:44:43
 */
public interface TreeableAndIdableService<T extends Treeable<T> & Idable<PK>, PK extends Serializable> extends TreeableService<T>, TreeableMapper<T, PK> {

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

}
