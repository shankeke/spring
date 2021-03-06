package org.mybatis.generator.plugins.mybatis3;

import java.util.Map;

import org.mybatis.generator.internal.util.StringUtility;

/**
 * 说明： Controller底层代码生成插件. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018-11-28 18:03:48
 */
public class ControllerGeneratorPlugin extends AbstractGeneratorPlugin {

	@Override
	public void generate(String topPackage, String targetProject, String javaClassName, Map<String, String> root) {
		// 设置注入的Service包名
		root.put("injectPackage", topPackage + ".service");

		// 判断指定的包名是否存在，否则按照默认规则生成
		if (!StringUtility.stringHasValue(targetPackage)) {
			targetPackage = topPackage.replaceAll("\\.", "/") + ".controller";
		}
		generate(targetProject, targetPackage, javaClassName, "Controller.java", "controller.ftl", root);
	}

}
