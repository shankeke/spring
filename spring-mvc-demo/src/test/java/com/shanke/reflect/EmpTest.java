package com.shanke.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class EmpTest {
	
	private static final String className = "com.shanke.reflect.Emp";

	@Test
	public void newInstance() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		// 第一种方式：
		Class<?> class1 = Class.forName(className);
		Emp newInstance = (Emp) class1.newInstance();
		Assert.assertNotNull(newInstance);

		// 第二种方式：
		// java中每个类型都有class 属性.
		Class<?> class2 = Emp.class;
		newInstance = (Emp) class2.newInstance();
		Assert.assertNotNull(newInstance);

		// 第三种方式：
		// java语言中任何一个java对象都有getClass 方法
		Emp emp = new Emp();
		Class<?> class3 = emp.getClass(); // c3是运行时类 (e的运行时类是Employee)
		newInstance = (Emp) class3.newInstance();
		Assert.assertNotNull(newInstance);
	}

	@Test
	public void getDeclaredFields() throws ClassNotFoundException {
		// 获取整个类
		Class<?> c = Class.forName(className);
		// 获取所有的属性?
		Field[] fs = c.getDeclaredFields();

		// 定义可变长的字符串，用来存储属性
		StringBuffer sb = new StringBuffer();
		// 通过追加的方法，将每个属性拼接到此字符串中
		// 最外边的public定义
		sb.append(Modifier.toString(c.getModifiers()) + " class "
				+ c.getSimpleName() + "{\n");
		// 里边的每一个属性
		for (Field field : fs) {
			sb.append("\t");// 空格
			sb.append(Modifier.toString(field.getModifiers()) + " ");// 获得属性的修饰符，例如public，static等等
			sb.append(field.getType().getSimpleName() + " ");// 属性的类型的名字
			sb.append(field.getName() + ";\n");// 属性的名字+回车
		}
		sb.append("}");

		System.out.println(sb);
	}

	@Test
	public void getDeclaredField() throws ClassNotFoundException,
			NoSuchFieldException, SecurityException, InstantiationException,
			IllegalAccessException {
		// 获取整个类
		Class<?> c = Class.forName(className);
		// 获取id属性
		Field idF = c.getDeclaredField("age");
		// 实例化这个类赋给o
		Object o = c.newInstance();
		// 打破封装
		idF.setAccessible(true); // 使用反射机制可以打破封装性，导致了java对象的属性不安全。
		// 给o对象的id属性赋值"110"
		idF.set(o, 110); // set
		// get
		System.out.println(idF.get(o));
	}

	@Test
	public void getDeclaredMethods() throws ClassNotFoundException,
			NoSuchFieldException, SecurityException, InstantiationException,
			IllegalAccessException {
		// 获取整个类
		Class<?> c = Class.forName(className);
		// 获取id属性
		Method[] ms = c.getDeclaredMethods();

		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = null;

		for (Method m : ms) {
			sb.append(Modifier.toString(m.getModifiers()) + " ");
			sb.append(m.getReturnType().getSimpleName() + " ");
			sb.append(m.getName() + "(");

			sb1 = new StringBuilder();
			Class<?>[] parameterTypes = m.getParameterTypes();
			if (parameterTypes != null && parameterTypes.length > 0) {
				for (Class<?> class1 : parameterTypes) {
					sb1.append(class1.getSimpleName() + " "
							+ class1.getSimpleName().toLowerCase() + ",");
				}
			}
			String string = sb1.toString();
			if (StringUtils.isNotEmpty(string)) {
				string = StringUtils.chomp(string, ",");
			}
			sb.append(string + "){\n}\n");
		}
		System.out.println(sb.toString());
	}
}
