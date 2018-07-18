package com.shanke.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import com.shanke.utils.page.PageUtil;

public class PageUtilTest {

	/***************** 测试代码 ******************/
	// 测试字符串集合的排序分页
	@Test
	public void testStrSort() {
		PageUtil<String, String> pageUtil = new PageUtil<String, String>();
		final Random ran = new Random();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 100; i++)
			list.add(ran.nextInt(1000) + "");
		System.out.println(list.toString());

		Map<String, String> map = new HashMap<String, String>();
		for (String s : list)
			map.put(s, s);
		System.out.println(map);

		Map<String, String> order = pageUtil.order(map, false);
		System.out.println(order);

		List<String> extractMap2List = pageUtil.extractMap2List(order);
		System.out.println(extractMap2List);

		List<String> page = pageUtil.page(extractMap2List, 2, 2);
		System.out.println(page);
	}

	// 测试int数据集合的排序分页\
	@Test
	public void testIntSort() {
		PageUtil<Integer, Integer> pageUtil = new PageUtil<Integer, Integer>();
		final Random ran = new Random();
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 100; i++)
			list.add(ran.nextInt(1000));
		System.out.println(list.toString());

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Integer s : list) {
			map.put(s, s);
		}
		System.out.println(map);

		Map<Integer, Integer> order = pageUtil.order(map, true);
		System.out.println(order);

		List<Integer> extractMap2List = pageUtil.extractMap2List(order);
		System.out.println(extractMap2List);

		List<Integer> page = pageUtil.page(extractMap2List, 2, 2);
		System.out.println(page);
	}

	// 测试int数据集合的排序分页
	@Test
	public void testObjSort() {
		PageUtil<Foo, Integer> pageUtil = new PageUtil<Foo, Integer>();
		final Random ran = new Random();
		List<Foo> list = new ArrayList<Foo>();
		for (int i = 0; i < 100; i++)
			list.add(new Foo(ran.nextInt(1000) + "", ran.nextInt(1000)));
		System.out.println(list.toString());

		Map<Foo, Integer> map = new HashMap<Foo, Integer>();
		for (Foo s : list) {
			map.put(s, s.getAge());
		}
		System.out.println(map);

		Map<Foo, Integer> order = pageUtil.order(map, true);
		System.out.println(order);

		List<Integer> extractMap2List = pageUtil.extractMap2List(order);
		System.out.println(extractMap2List);

		List<Integer> page = pageUtil.page(extractMap2List, 2, 2);
		System.out.println(page);
	}

	@Test
	public void testListSort() {
		PageUtil<Foo, Foo> pageUtil = new PageUtil<Foo, Foo>();
		final Random ran = new Random();
		List<Foo> list = new ArrayList<Foo>();
		for (int i = 0; i < 100; i++)
			list.add(new Foo(ran.nextInt(1000) + "", ran.nextInt(1000)));
		System.out.println(list.toString());
		Collections.sort(list);
		System.out.println(list.toString());
		List<Foo> page = pageUtil.page(list, 2, 2);
		System.out.println(page);
		// list.subList(fromIndex, toIndex);
	}

	@Test
	public void testListSub() {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 100; i++)
			list.add(i);
		System.out.println(list.toString());
		Collections.sort(list);
		System.out.println(list.toString());

		PageUtil<Integer, Integer> pageUtil = new PageUtil<Integer, Integer>();
		List<Integer> page = pageUtil.page(list, 2, 2);
		System.out.println(page);
	}
}

class Foo implements Comparable<Foo> {
	private String name;
	private Integer age;

	public Foo() {
	}

	public Foo(String name) {
		this.name = name;
	}

	public Foo(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Foo [name=" + name + ", age=" + age + "]";
	}

	@Override
	public int compareTo(Foo o) {
		int compareToIgnoreCase = name.compareToIgnoreCase(o.getName());
		if (compareToIgnoreCase != 0)
			return compareToIgnoreCase;
		return age.compareTo(o.getAge());
	}
}
