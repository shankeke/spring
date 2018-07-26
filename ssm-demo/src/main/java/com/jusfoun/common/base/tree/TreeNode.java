package com.jusfoun.common.base.tree;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述:树状结构数据检所处理接口. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月24日 下午2:24:03
 */
@ApiModel
public abstract class TreeNode<T extends Treeable<T>> implements Treeable<T> {

	/**
	 * 子节点集合
	 */
	@ApiModelProperty("子节点集合")
	private List<T> subList;

	/**
	 * 是否是叶子节点
	 */
	@ApiModelProperty("是否是叶子节点")
	private boolean leaf;

	@Override
	public List<T> getSubList() {
		return subList;
	}

	@Override
	public void setSubList(List<T> subList) {
		this.subList = subList;
	}

	@Override
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	@Override
	public boolean isLeaf() {
		return leaf;
	}
}
