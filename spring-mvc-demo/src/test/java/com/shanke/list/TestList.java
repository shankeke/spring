package com.shanke.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class TestList {
	public static final int N = 50000;

	@SuppressWarnings("rawtypes")
	public static List values;

	static {
		Integer vals[] = new Integer[N];
		Random r = new Random();
		for (int i = 0, currval = 0; i < N; i++) {
			currval += r.nextInt(100) + 1;
			vals[i] = new Integer(currval);
		}
		values = Arrays.asList(vals);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public long timeListBinarySearch(List lst) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < N; i++) {
			int index = Collections.binarySearch(lst, values.get(i));
			if (index != i)
				System.out.println("***错误***");
		}
		return System.currentTimeMillis() - start;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public long timeListInsertAndRemove(List list) {
		long start = System.currentTimeMillis();
		Object o = new Object();
		for (int i = 0; i < N; i++)
			list.add(0, o);
		return System.currentTimeMillis() - start;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void test() {
		System.out.println("ArrayList消耗时间："
				+ timeListBinarySearch(new ArrayList(values)));
		System.out.println("LinkedList消耗时间："
				+ timeListBinarySearch(new LinkedList(values)));

		System.out.println("ArrayList耗时："
				+ timeListInsertAndRemove(new ArrayList()));
		System.out.println("LinkedList耗时："
				+ timeListInsertAndRemove(new LinkedList()));
	}

	@Test
	public void testCollections() {
		// Create a list
		String[] strArray = new String[] { "z", "a", "C" };
		List<String> list = Arrays.asList(strArray);
		// Sort
		Collections.sort(list); // C, a, z
		System.out.println(list);
		// Case-insensitive sort
		Collections.sort(list, String.CASE_INSENSITIVE_ORDER); // a, C, z
		System.out.println(list);
		// Reverse-order sort
		Collections.sort(list, Collections.reverseOrder()); // z, a, C
		System.out.println(list);
		// Case-insensitive reverse-order sort
		Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
		System.out.println(list);
		Collections.reverse(list); // z, C, a
		System.out.println(list);
	}
}
