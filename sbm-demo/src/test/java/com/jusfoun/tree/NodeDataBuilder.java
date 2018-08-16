package com.jusfoun.tree;

import java.util.List;
import java.util.Random;

/**
 * 描述 : 生产树结构数据. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月16日 上午9:37:28
 */
public class NodeDataBuilder {
	private static int id = 0;

	// 初始化一个随机的多层树
	public static Node initRoot() {
		Random random = new Random();
		int len = 0;
		Node root = new Node("node_" + 0 + "_" + 0);
		List<Node> subList0 = null;
		List<Node> subList1 = null;
		len = 2 + random.nextInt(2);
		for (int i = 0; i < len; i++) {
			root.getSubList().add(new Node("node_" + id++ + "_" + id++));
		}
		subList0 = root.getSubList();
		for (Node n : subList0) {
			len = 1 + random.nextInt(10);
			for (int i = 0; i < len; i++) {
				n.getSubList().add(new Node("node_" + id++ + "_" + id++));
			}
			subList1 = n.getSubList();
			for (Node n1 : subList1) {
				len = 1 + random.nextInt(5);
				for (int i = 0; i < len; i++) {
					n1.getSubList().add(new Node("node_" + id++ + "_" + id++));
				}
			}
		}
		return root;
	}
}
