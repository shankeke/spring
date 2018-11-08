package com.jusfoun.test;

import java.util.Arrays;

import org.junit.Test;

import com.jusfoun.common.message.result.BaseResponse;
import com.jusfoun.entity.SysModule;
import com.jusfoun.entity.SysUser;

public class ObjectTest {

	@Test
	public void test() {
		SysUser t = new SysUser();
		Object t1 = t;
		System.out.println(t1.getClass());
		System.out.println(t1.getClass().getComponentType());
	}

	@Test
	public void test1() {
		SysUser t = new SysUser();
		BaseResponse<SysUser> response = BaseResponse.success(t);
		SysModule t2 = new SysModule();

		SysUser t1 = response.getContent();
		System.out.println(t1.getClass());
		System.out.println(t2.getClass());
		System.out.println(t1.getClass().equals(SysUser.class));
		System.out.println(t2.getClass().equals(SysModule.class));
		System.out.println(t2.getClass().equals(SysUser.class));
	}

	private void output(Object... objs) {
		Arrays.stream(objs).forEach(t -> System.out.println(t));
	}

	@Test
	public void test2() {
		output(1, 23, "23", (byte) 12, 'd', new SysModule());
		output(new Object[]{1, 23, "23", (byte) 12, 'd', new SysModule()});
	}

}
