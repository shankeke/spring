package com.jusfoun.tree;

import com.alibaba.fastjson.JSON;
import com.jusfoun.common.base.tree.TreeNode;

/**
 * 描述:定义一个树数据类. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月27日 上午9:34:11
 */
public class Node extends TreeNode<Node> {

	private Integer id;

	private String name;

	public Node(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Node(String name) {
		this.name = name;
	}

	@Override
	public String[] matchFeilds() {
		return new String[]{name};
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
