package com.jusfoun.client;

import org.junit.Test;

public class TestEquals {

	@Test
	public void test() {

		String s1 = "week";
		System.out.println(s1.equals("week"));

		String s2 = "week";
		System.out.println(s1.equals(s2));
		System.out.println(s1 == s2);
	}
}
