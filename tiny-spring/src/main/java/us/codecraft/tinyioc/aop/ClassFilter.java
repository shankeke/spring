package us.codecraft.tinyioc.aop;

/**
 * @author yihua.huang@dianping.com
 */
public interface ClassFilter {

    @SuppressWarnings("rawtypes")
	boolean matches(Class targetClass);
}
