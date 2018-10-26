package com.jusfoun.tree;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.jusfoun.common.base.tree.TreeableService;

/**
 * 说明： 多层树结构实现搜索构成树的功能. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月16日 上午9:37:28
 */
public class NodeSearchTest {
	private TreeableService<Node> treeableService = new NodeService();

	@Test
	public void test() {
		Node root = NodeDataBuilder.initRoot();
		System.out.println(JSON.toJSONString(root));
		System.out.println(JSON.toJSONString(treeableService.selectTree(root, "node_1")));
		System.out.println(JSON.toJSONString(treeableService.selectTree(root, "6_7")));
		System.out.println(JSON.toJSONString(treeableService.selectTree(root, "node_152_153")));
	}

}
