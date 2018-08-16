package com.jusfoun.common.base.tree;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jusfoun.common.message.exception.ServiceException;
import com.jusfoun.common.utils.ICollections;

/**
 * 描述:树状数据结构的数据模型业务处理接口定义. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月26日 上午11:44:43
 */
public interface TreeableService<T extends Treeable<T>> {

	/**
	 * 描述: 树数据检索，可根据根节点和关键字检索符合的树数据. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月26日 上午11:46:51
	 * @param root
	 *            根节点
	 * @param keyword
	 *            检索关键字
	 * @return 根节点及符合条件的后代节点
	 * @throws ServiceException
	 */
	default T selectTree(T root, String keyword) throws ServiceException {
		// 如果跟节点存在并且需要根据关键字检索结果
		if (root != null && StringUtils.isNotEmpty(keyword)) {
			List<T> searchList = root.search(root.getSubList(), keyword);
			if (ICollections.hasElements(searchList) || root.isMatch(root, keyword)) {
				root.setSubList(searchList);
			} else {
				root = null;
			}
		}
		return root;
	}

}
