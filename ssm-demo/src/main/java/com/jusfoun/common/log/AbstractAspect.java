package com.jusfoun.config.aop;

import java.util.HashMap;
import java.util.Map;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

public abstract class AbstractAspect {

	protected static String[] types = { "java.lang.Integer", "java.lang.Double", "java.lang.Float", "java.lang.Long",
			"java.lang.Short", "java.lang.Byte", "java.lang.Boolean", "java.lang.Char", "java.lang.String", "int",
			"double", "long", "short", "byte", "boolean", "char", "float" };

	protected Map<String, Object> getFieldsName(Class<?> cls, String clazzName, String methodName, Object[] args)
			throws NotFoundException {
		Map<String, Object> map = new HashMap<String, Object>();

		ClassPool pool = ClassPool.getDefault();
		// ClassClassPath classPath = new ClassClassPath(this.getClass());
		ClassClassPath classPath = new ClassClassPath(cls);
		pool.insertClassPath(classPath);

		CtClass cc = pool.get(clazzName);
		CtMethod cm = cc.getDeclaredMethod(methodName);
		MethodInfo methodInfo = cm.getMethodInfo();
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
		if (attr == null) {
			// exception
		}
		// String[] paramNames = new String[cm.getParameterTypes().length];
		int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
		for (int i = 0; i < cm.getParameterTypes().length; i++) {
			map.put(attr.variableName(i + pos), args[i]);// paramNames即参数名
		}
		return map;
	}
}
