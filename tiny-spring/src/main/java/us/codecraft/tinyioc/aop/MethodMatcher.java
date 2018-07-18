package us.codecraft.tinyioc.aop;

import java.lang.reflect.Method;

/**
 * @author yihua.huang@dianping.com
 */
public interface MethodMatcher {

	@SuppressWarnings("rawtypes")
	boolean matches(Method method, Class targetClass);
}
