package org.mybatis.generator.plugins.mybatis3;

import java.util.Map;

import org.mybatis.generator.internal.util.StringUtility;

/**
 * 说明： Service层代码生成插件.<br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018-11-28 18:05:06
 */
public class ServiceGeneratorPlugin extends AbstractGeneratorPlugin {

	@Override
	public void generate(String topPackage, String targetProject, String javaClassName, Map<String, String> root) {
		// 判断指定的包名是否存在，否则按照默认规则生成
		if (!StringUtility.stringHasValue(targetPackage)) {
			targetPackage = topPackage.replaceAll("\\.", "/") + ".service";
		}
		generate(targetProject, targetPackage, javaClassName, "Service.java", "service.ftl", root);
		// 生成ServiceImpl代码
		String serviceImplTargetPackage = targetPackage + ".impl";
		generate(targetProject, serviceImplTargetPackage, javaClassName, "ServiceImpl.java", "service-impl.ftl", root);
	}

}
