package com.jusfoun.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.jusfoun.common.util.list.IListUtil;

/**
 * 描述 : 多层树结构实现搜索构成树的功能. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月16日 上午9:37:28
 */
public class NodeSearchTest {
	private List<Node> rootTree;

	private static int id = 0;

	// 初始化一个随机的多层树
	@Before
	public void init() {
		Random random = new Random();
		int len = 0;
		rootTree = new ArrayList<Node>() {
			private static final long serialVersionUID = -4046386793046456599L;
			{
				int len = random.nextInt(1000) + 100;
				for (int i = 0; i < len; i++) {
					add(new Node(id++, 0, "node_" + i + "_" + id));
				}
			}
		};

		List<Node> subs = null;
		List<Node> subs1 = null;
		for (Node node : rootTree) {
			len = 2 + random.nextInt(2);
			for (int i = 0; i < len; i++) {
				node.getSubs().add(new Node(id++, node.getId(), "node_" + id + "_" + id));
			}
			subs = node.getSubs();
			for (Node n : subs) {
				len = 1 + random.nextInt(10);
				for (int i = 0; i < len; i++) {
					n.getSubs().add(new Node(id++, n.getId(), "node_" + id + "_" + id));
				}
				subs1 = n.getSubs();
				for (Node n1 : subs1) {
					len = 1 + random.nextInt(5);
					for (int i = 0; i < len; i++) {
						n1.getSubs().add(new Node(id++, n1.getId(), "node_" + id + "_" + id));
					}
				}
			}
		}
	}

	public List<Node> search(List<Node> rootTree, String keyWord) {
		if (!IListUtil.hasData(rootTree)) {
			return null;
		}
		
		Node node = null;
		List<Node> subs = null;
		Iterator<Node> ite = rootTree.iterator();
		while (ite.hasNext()) {
			node = ite.next();
			subs = node.getSubs();

			if (isMatch(node, keyWord) || IListUtil.hasData(subs)) {
				if (IListUtil.hasData(subs)) {
					subs = search(subs, keyWord);
					if (IListUtil.hasData(subs)) {
						node.setSubs(subs);
					} else {
						if (isMatch(node, keyWord)) {
							node.setLast(true);
							node.setSubs(null);
						} else {
							ite.remove();
						}
					}
				} else {
					node.setSubs(null);
					node.setLast(true);
				}
			} else {
				ite.remove();
			}
		}
		if (isEnd(rootTree, true)) {
			return rootTree;
		} else {
			return search(rootTree, keyWord);
		}
	}

	/**
	 * 描述 :判断是否已经遍历完成. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月16日 上午9:28:10
	 * @param rootTree
	 *            遍历的集合
	 * @param b
	 *            是否遍历完成
	 * @return 遍历结果
	 */
	public boolean isEnd(List<Node> rootTree, boolean b) {
		List<Node> subs = null;
		if (IListUtil.hasData(rootTree)) {
			for (Node node : rootTree) {
				subs = node.getSubs();
				if (IListUtil.hasData(subs)) {
					b = isEnd(subs, b);
				} else {
					b = b && node.isLast();
				}
			}
		}
		return b;
	}

	public boolean isMatch(Node t, String keyWord) {
		return t != null && StringUtils.isNotEmpty(t.getName()) && t.getName().contains(keyWord);
	}

	@Test
	public void test() {
		// 找一个名字像'node_1'的人
		System.out.println(JSON.toJSONString(search(rootTree, "node_1")));
		System.out.println(JSON.toJSONString(search(rootTree, "1_1")));
		System.out.println(JSON.toJSONString(search(rootTree, "node_1_2")));
	}

	@Test
	public void check() {
		System.out.println(isEnd(rootTree, true));
	}
}
