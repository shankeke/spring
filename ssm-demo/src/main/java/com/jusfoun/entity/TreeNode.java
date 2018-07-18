package com.jusfoun.entity;

import java.io.Serializable;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 描述 : 树枝状结构数据公共属性. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月16日 上午10:26:58
 */
public class TreeNode implements Serializable {
	private static final long serialVersionUID = 1241155855343907153L;

	// 是否是树的最后节点
	@Transient
	@JsonIgnore
	private boolean isLast;

	public boolean isLast() {
		return isLast;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}
}
