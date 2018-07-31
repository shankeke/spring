package com.jusfoun.common.utils;

import java.lang.reflect.Method;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * 描述:spel解析工具. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月31日 上午8:59:38
 */
public class SpelUtils {

	/**
	 * 描述:解析方法注解上面的spel表达式. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月31日 上午9:00:11
	 * @param key
	 *            表达式
	 * @param method
	 *            方法
	 * @param args
	 *            参数列表
	 * @return 解析后的结果
	 */
	public static String parseKey(String key, Method method, Object[] args) {
		// 获取被拦截方法参数名列表(使用Spring支持类库)
		LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
		String[] paraNameArr = u.getParameterNames(method);
		// 使用SPEL进行key的解析
		ExpressionParser parser = new SpelExpressionParser();
		// SPEL上下文
		StandardEvaluationContext context = new StandardEvaluationContext();
		// 把方法参数放入SPEL上下文中
		for (int i = 0; i < paraNameArr.length; i++) {
			context.setVariable(paraNameArr[i], args[i]);
		}
		return parser.parseExpression(key).getValue(context, String.class);
	}

}
