package com.jusfoun.common.base.tree;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import com.jusfoun.common.util.list.IListUtil;

/**
 * 描述:树状结构数据查询检索处理接口. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月24日 下午2:24:03
 */
public interface Treeable<T extends Treeable<T>> {

	/**
	 * 描述:检索字段. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月24日 下午1:50:07
	 * @param regex
	 *            检索字段
	 * @return 检索筛选结果
	 */
	default List<T> search(List<T> rootList, String regex) {
		List<T> subList = null;
		T node = null;
		Iterator<T> ite = rootList.iterator();
		while (ite.hasNext()) {
			node = ite.next();
			subList = node.getSubList();
			if (isMatch(node, regex) || IListUtil.hasData(subList)) {
				if (IListUtil.hasData(subList)) {
					// 回调继续检索下一层数据
					subList = search(subList, regex);
					if (IListUtil.hasData(subList)) {
						node.setSubList(subList);
					} else {
						if (isMatch(node, regex)) {
							node.setLeaf(true);
						} else {
							ite.remove();
						}
					}
				} else {
					node.setLeaf(true);
				}
			} else {
				ite.remove();
			}
		}
		// 检查是否完成了检索，如果没有继续回调该方法进行检索直到所有的检索完成
		if (isEnd(rootList, true)) {
			return rootList;
		} else {
			return search(rootList, regex);
		}

	}

	/**
	 * 描述:获取子集 <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月24日 下午1:50:07
	 * @return 获取子集
	 */
	List<T> getSubList();

	/**
	 * 描述:设置子集 <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月24日 下午1:50:07
	 * @param subList
	 *            子集
	 */
	void setSubList(List<T> subList);

	/**
	 * 描述:是否遍历结束. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月24日 下午1:48:26
	 * @param b
	 *            初始值
	 * @return 遍历结束
	 */
	default boolean isEnd(List<T> subList, boolean b) {
		List<T> subs = null;
		if (IListUtil.hasData(subList)) {
			for (T node : subList) {
				subs = node.getSubList();
				if (IListUtil.hasData(subs)) {
					b = isEnd(subs, b);
				} else {
					b = b && node.isLeaf();
				}
			}
		}
		return b;
	}

	/**
	 * 描述:获取匹配的字段值集合. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月24日 下午1:45:03
	 * @return 匹配
	 */
	String[] matchFeilds();

	/**
	 * 描述: 是否匹配检索字段. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月24日 下午1:45:03
	 * @param regex
	 *            检索字段
	 * @return 匹配
	 */
	default boolean isMatch(T t, String regex) {
		boolean b = true;
		Pattern p = Pattern.compile(regex);
		String[] matchFeilds = t.matchFeilds();
		if (matchFeilds != null && matchFeilds.length > 0) {
			for (String s : matchFeilds) {
				b &= p.matcher(s).find();
			}
		}
		return b;
	}

	/**
	 * 描述: 设置是否是叶子节点. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月24日 下午1:42:02
	 */
	void setLeaf(boolean leaf);

	/**
	 * 描述: 是否是叶子节点. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月24日 下午1:42:02
	 * @return 是否是叶子节点
	 */
	boolean isLeaf();
}
