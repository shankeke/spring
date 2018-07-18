package com.jusfoun.web.filter.wapper;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 描述 : 继承HttpServletRequestWrapper，创建装饰类，以达到修改HttpServletRequest参数的目的. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月9日 下午7:48:54
 */
public class ModifyParametersWrapper extends HttpServletRequestWrapper {
	private final Map<String, String> headers;

	ModifyParametersWrapper(HttpServletRequest request) {
		super(request);
		this.headers = new HashMap<>();
	}

	void putHeader(String name, String value) {
		this.headers.put(name, value);
	}

	public String getHeader(String name) {
		// check the custom headers first
		String headerValue = headers.get(name);

		if (headerValue != null) {
			return headerValue;
		}
		// else return from into the original wrapped object
		return ((HttpServletRequest) getRequest()).getHeader(name);
	}

	public Enumeration<String> getHeaderNames() {
		// create a set of the custom header names
		Set<String> set = new HashSet<>(headers.keySet());

		// now add the headers from the wrapped request object
		Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();
		while (e.hasMoreElements()) {
			// add the names of the request headers into the list
			String n = e.nextElement();
			set.add(n);
		}
		// create an enumeration from the set and return
		return Collections.enumeration(set);
	}
}