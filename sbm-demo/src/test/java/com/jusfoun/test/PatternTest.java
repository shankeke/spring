package com.jusfoun.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class PatternTest {

	@Test
	public void test() {
		Pattern p = Pattern.compile("国");
		Matcher matcher = p.matcher("中国万里长城");
		System.out.println(matcher.find());
		System.out.println(matcher.matches());
		System.out.println(matcher.start());
		System.out.println(matcher.end());
		System.out.println(matcher.group());
		System.out.println(matcher.groupCount());
		System.out.println(matcher.hitEnd());
	}
}
