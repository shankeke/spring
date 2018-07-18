package com.shanke.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.shanke.utils.page.ListPageUtil;

public class ListPageUtilTest {

	@Test
	public void sort() {
		ListPageUtil<Integer> u = new ListPageUtil<Integer>();
		List<Integer> list = new ArrayList<Integer>();
		Random ran = new Random();
		for (int i = 0; i < 1000; i++) {
			list.add(ran.nextInt(1000));
		}
		System.out.println(list);
		// u.sort(list);
		// System.out.println(list);
		u.sort(list, false);
		System.out.println(list);
	}

	@Test
	public void page() {
		ListPageUtil<Integer> u = new ListPageUtil<Integer>();
		List<Integer> list = new ArrayList<Integer>();
		Random ran = new Random();
		for (int i = 0; i < 1000; i++) {
			list.add(ran.nextInt(1000));
		}
		u.sort(list);
		System.out.println(list);
		System.out.println(u.page(list, 1, 10));
		u.sort(list, false);
		System.out.println(list);
		System.out.println(u.page(list, 1, 10));
	}
}
