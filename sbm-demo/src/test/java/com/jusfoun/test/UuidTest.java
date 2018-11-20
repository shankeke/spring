package com.jusfoun.test;

import java.util.UUID;

import org.junit.Test;

public class UuidTest {

	@Test
	public void test() {
		System.out.println(UUID.randomUUID().toString());
		System.out.println(UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"));
		System.out.println(UUID.nameUUIDFromBytes("www.jusfoun.com".getBytes()));
	}

}
