package com.shanke.web.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 描述 :<br>
 * 处理客户端请求参数解码逻辑。由于有的接口上行参数使用base64编码，需要在后台处理时进行解码。 <br>
 * 这里通过滤器统一处理这些需要解码的接口的参数，在过滤器中将参数解码后放回请求参数列表中<br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2017-3-6 上午10:42:17
 * @version 1.0
 */
@Configuration
@WebFilter(//
filterName = "DecryptFilter", urlPatterns = "/*", initParams = {//
@WebInitParam(name = "include_urls", value = "/test/test3") //
})
public class DecryptFilter extends OncePerRequestFilter {
	private static Logger log = LoggerFactory.getLogger(DecryptFilter.class);

	private String include_urls;

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request)
			throws ServletException {
		// TODO 如果初始化的参数include_urls里面包含当前请求路径，则需要过滤处理，否则不处理
		List<String> list = null;
		if (!StringUtils.isEmpty(include_urls)) {
			String[] urls = include_urls.split(",");
			list = Arrays.asList(urls);
		}
		if (list != null && list.size() > 0) {
			String sRequestUri = request.getRequestURI();
			String contextPath = request.getContextPath();
			sRequestUri = sRequestUri.replaceFirst(contextPath, "");
			return !list.contains(sRequestUri);
		}
		return super.shouldNotFilter(request);
	}

	// 对参数进行解码处理
	public String decrypt(HttpServletRequest request, String name, String input) {
		String ret = input;
		// 如果为空，返回
		if (null == input || "(null)".equals(input.trim())
				|| "null".equals(input.trim())) {
			return ret;
		}
		// 解码
		try {
			ret = new String(Base64.decodeBase64(input));
		} catch (Exception e) {
			log.error(request.getRequestURI() + " error decode " + name + "="
					+ input, e);
		}
		return ret;
	}

	@Override
	protected void doFilterInternal(final HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		// 重新包装请求参数
		chain.doFilter(new HttpServletRequestWrapper(request) {
			protected Map<String, String[]> parameters = new HashMap<String, String[]>();

			@Override
			public String getParameter(String name) {
				if (parameters.containsKey(name)) {
					String[] values = parameters.get(name);
					if (values != null && values.length > 0) {
						return values[0];
					}
				}
				String value = super.getParameter(name);
				String retValue = decrypt(this, name, value);
				parameters.put(retValue, new String[] { retValue });
				return retValue;
			}

			@Override
			public String[] getParameterValues(String name) {
				if (parameters.containsKey(name)) {
					String[] values = parameters.get(name);
					return values;
				}
				String[] values = super.getParameterValues(name);
				if (values == null) {
					return null;
				}
				for (int i = 0; i < values.length; i++) {
					values[i] = decrypt(this, name, values[i]);
				}
				parameters.put(name, values);
				return values;
			}
		}, response);
	}

	public String getInclude_urls() {
		return include_urls;
	}

	public void setInclude_urls(String include_urls) {
		this.include_urls = include_urls;
	}
}
