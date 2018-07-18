package com.shanke.list;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void test() {
		LinkedList<Object> list = new LinkedList<Object>();

		// 添加元素
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		System.out.println(list);

		// 前面插入元素
		list.addFirst("X");
		// 后方追加元素
		list.addLast("Z");
		System.out.println(list);

		// 中间插入元素
		list.add(2, "F");
		System.out.println(list);
		// 中间删除元素
		list.remove(4);
		System.out.println(list);

		// 头尾删除
		list.removeLast();
		System.out.println(list);
		list.removeFirst();
		System.out.println(list);

		// 遍历
		for (Object object : list) {
			System.out.println(object);
		}

		// 获取子集
		List<Object> sub = list.subList(1, 3);
		System.out.println(sub);
		System.out.println(list);

		// 获取最先最后元素
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println(list.getFirst());
		System.out.println(list.getLast());
	}

	// 链表实现队列
	@Test
	public void testQueue() {
		Queue q = new Queue();
		for (int i = 0; i < 5; i++) {
			q.put(i);
			System.out.println(q);
		}
		while (!q.isEmpty()) {
			System.out.println(q);
			System.out.println(q.get());
			System.out.println(q);
		}

	}

	// 链表实现队列
	@Test
	public void testStack() {
		Stack stack = new Stack();
		for (int i = 0; i < 5; i++) {
			stack.push(i);
			System.out.println(stack);
		}

		while (!stack.isEmpty()) {
			System.out.println(stack);
			System.out.println(stack.top());
			System.out.println(stack.pop());
			if (!stack.isEmpty())
				System.out.println(stack.top());
			System.out.println(stack);
		}
	}
}

// 链表实现队列：先进先出
class Queue {
	private LinkedList<Object> list = new LinkedList<Object>();

	public void put(Object v) {
		list.addFirst(v);
	}

	public Object get() {
		return list.removeLast();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public String toString() {
		return "Queue [list=" + list + "]";
	}
}

// 链表实现栈：先进后出
class Stack {
	private LinkedList<Object> list = new LinkedList<Object>();

	public void push(Object v) {
		list.addFirst(v);
	}

	public Object top() {
		return list.getFirst();
	}

	public Object pop() {
		return list.removeFirst();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public String toString() {
		return "Stack [list=" + list + "]";
	}
}