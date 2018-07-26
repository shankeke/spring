package com.jusfoun.common.base.tree;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jusfoun.common.exception.ServiceException;
import com.jusfoun.common.util.list.IListUtil;

/**
 * 描述:树状数据结构的数据模型业务处理接口定义. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月26日 上午11:44:43
 */
public interface TreeableService<T extends Treeable<T>, PK extends Serializable> extends TreeableMapper<T, PK> {

	/**
	 * 描述: 树图数据查询，可根据根节点主键和关键字检索. <br>
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
		T root = selectTree(rootId);
		// 如果跟节点存在并且需要根据关键字检索结果
		if (root != null && StringUtils.isNotEmpty(keyword)) {
			List<T> searchList = root.search(root.getSubList(), keyword);
			if (IListUtil.hasData(searchList) || root.isMatch(root, keyword)) {
				root.setSubList(searchList);
			}
		}
		return root;
	}

}
