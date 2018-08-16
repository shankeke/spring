package com.jusfoun.web.filter.gzip;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * 描述 : 支持数据压缩. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月27日 下午4:21:44
 */
@WebFilter(filterName = "gzipSupportFilter", urlPatterns = "/*")
public class GzipSupportFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(new GzipRequestWrapper((HttpServletRequest) request), response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
