package com.shanke.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述 : 使用servlet做一个请求代理. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016-9-1 上午11:49:50
 */
@WebServlet(urlPatterns = "/proxy", initParams = { @WebInitParam(name = "url", value = "http://202.99.207.241:7080/Itaxer/", description = "使用servlet做一个请求代理,initParams中url参数是需要代理的服务地址") })
public class ProxyServlet extends HttpServlet {
	private static final long serialVersionUID = -7366284099849447519L;
	private static final String SERVLETNAME = "servletName";
	private static final String PROXYURLNAME = "proxyUrl";

	/** 自定义的代理服务器地址 */
	private String proxyurl;

	/** 需要代理的服务地址 */
	private String url;

	/**
	 * 描述 :对servlet进行请求处理，并将结果在指定输出流中输出. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-9-1 上午11:50:51
	 * @param target
	 *            target 参数列表
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private void process(HttpServletRequest req, HttpServletResponse resp,
			String[] target) throws MalformedURLException, IOException {
		// 取得连接
		HttpURLConnection huc = null;
		if (proxyurl != null && proxyurl.length() > 0)
			huc = (HttpURLConnection) new URL(proxyurl + target[0])
					.openConnection();
		else
			huc = (HttpURLConnection) new URL(url + target[0]).openConnection();

		// 设置连接属性
		huc.setDoOutput(true);
		huc.setRequestMethod("POST");
		huc.setUseCaches(false);
		huc.setInstanceFollowRedirects(true);
		huc.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		huc.connect();

		// 往目标servlet中提供参数
		OutputStream targetOS = huc.getOutputStream();
		targetOS.write(target[1].getBytes());
		targetOS.flush();
		targetOS.close();

		// 取得页面输出,并设置页面编码及缓存设置
		resp.setContentType(huc.getContentType());
		resp.setHeader("Cache-Control", huc.getHeaderField("Cache-Control"));
		resp.setHeader("Pragma", huc.getHeaderField("Pragma"));
		resp.setHeader("Expires", huc.getHeaderField("Expires"));
		OutputStream os = resp.getOutputStream();

		// 将目标servlet的输入流直接往页面输出
		InputStream targetIS = huc.getInputStream();
		int r;
		while ((r = targetIS.read()) != -1) {
			os.write(r);
		}
		targetIS.close();
		os.flush();
		os.close();

		huc.disconnect();
	}

	/**
	 * 描述 :将参数中的目标分离成由目标servlet名称和参数组成的数组. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-9-2 下午4:16:59
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("rawtypes")
	private String[] parse(Map map) throws UnsupportedEncodingException {
		String[] arr = { "", "" };
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry me = (Entry) iter.next();
			String[] varr = (String[]) me.getValue();
			if (SERVLETNAME.equals(me.getKey())) {
				// 取出servlet名称
				arr[0] = varr[0];
			} else if (PROXYURLNAME.equals(me.getKey())) {
				proxyurl = varr[0];
			} else {
				// 重新组装参数字符串
				for (int i = 0; i < varr.length; i++) {
					// 参数需要进行转码，实现字符集的统一
					arr[1] += "&" + me.getKey() + "="
							+ URLEncoder.encode(varr[i], "utf-8");
				}
			}
		}
		arr[1] = arr[1].replaceAll("^&", "");
		return arr;
	}

	@Override
	public void init() throws ServletException {
		// 设置目标服务器地址
		url = this.getInitParameter("url");
		if (!url.endsWith("/"))
			url = url + "/";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp, parse(req.getParameterMap()));
	}
}
