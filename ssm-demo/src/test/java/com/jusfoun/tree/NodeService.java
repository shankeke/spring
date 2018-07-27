package com.jusfoun.tree;

import com.jusfoun.common.base.tree.TreeableService;

public class NodeService implements TreeableService<Node, Integer> {

	@Override
	public Node selectTree(Integer rootId) {
		return NodeDataBuilder.initRoot();
	}

}
