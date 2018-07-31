package com.jusfoun.common.base.tree;

import java.util.List;

import javax.persistence.Transient;

import com.google.common.collect.Lists;
import com.jusfoun.common.utils.ICollections;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述:树状结构数据查询处理接口. <br>
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
	@Transient
	private List<T> subList;

	/**
	 * 是否是叶子节点
	 */
	@ApiModelProperty("是否是叶子节点")
	@Transient
	private boolean leaf;

	@Override
	public List<T> getSubList() {
		if (!ICollections.hasData(subList)) {
			subList = Lists.newArrayList();
		}
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
