package us.codecraft.tinyioc.aop;

/**
 * @author yihua.huang@dianping.com
 */
public interface Pointcut {

	// 获取Class过滤器
	ClassFilter getClassFilter();

	// 获取方法匹配器
	MethodMatcher getMethodMatcher();

}
