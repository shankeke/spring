package com.shanke.dubbo.comm.exception;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述 : 对spring mvc异常拦截处理. <br>
 * <p>
 * Copyright (c) 2015 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016-3-29 下午2:03:48
 */
public class ExceptionHandler implements HandlerExceptionResolver {
	private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

		logger.error("Catch Exception: ", ex);
		Map<String, Object> err = new HashMap<String, Object>();
		if (ex instanceof UndeclaredThrowableException) {
			// if (((UndeclaredThrowableException)
			// ex).getUndeclaredThrowable().getCause() instanceof ResultCode)
			// {
			// err.put("msg", ((ResultCode) ((UndeclaredThrowableException)
			// ex).getUndeclaredThrowable()
			// .getCause()).getMsg());
			// }
		} else {
			err.put("msg", ex.getMessage());
		}
		return new ModelAndView("common/alarm", err);
		// if (ex instanceof RuntimeException)
		// {
		// return new ModelAndView("runEx");
		// }
		// else if (ex instanceof SQLException)
		// {
		// return new ModelAndView("sqlEx");
		// }
		// if (ex instanceof UnsupportedEncodingException)
		// {
		// return new ModelAndView("unsupEx");
		// }
		// else if (ex instanceof NoSuchAlgorithmException)
		// {
		// return new ModelAndView("nosuchEx");
		// }
		// if (ex instanceof IllegalArgumentException)
		// {
		// return new ModelAndView("illegaargEx");
		// }
		// else if (ex instanceof XmlPullParserException)
		// {
		// return new ModelAndView("xmlpullEx");
		// }
		// if (ex instanceof InvocationTargetException)
		// {
		// return new ModelAndView("invocationEx");
		// }
		// else if (ex instanceof IllegalAccessException)
		// {
		// return new ModelAndView("illegaaccEx");
		// }
		// else if (ex instanceof IntrospectionException)
		// {
		// return new ModelAndView("illegaaccEx");
		// }
		// else if (ex instanceof IOException)
		// {
		// return new ModelAndView("ioEx");
		// }
		// else
		// {
		// return new ModelAndView("exception");
		// }

	}

}
