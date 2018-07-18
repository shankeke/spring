package com.shanke.web.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.compress.utils.IOUtils;

public class SimpleProxy {

	/**
	 * 使用GET提交到目标服务器。
	 * 
	 * @param request
	 * @param response
	 * @param targetUrl
	 * @throws IOException
	 */
	private void get(HttpServletRequest request, HttpServletResponse response,
			String targetUrl) throws IOException {

		URL url = new URL(targetUrl);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				url.openStream()));

		String line;
		PrintWriter out = response.getWriter();
		while ((line = in.readLine()) != null) {
			out.println(line);
		}
		out.flush();
		in.close();
	}

	/**
	 * 使用POST提交到目标服务器。
	 * 
	 * @param request
	 * @param response
	 * @param targetUrl
	 * @throws IOException
	 */
	private void post(HttpServletRequest request, HttpServletResponse response,
			String targetUrl) throws IOException {
		URL url = new URL(targetUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		// 发送POST请求必须设置如下两行
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");
		// 可以拷贝客户端的head信息作为请求的head参数
		// conn.setRequestProperty("Charsert", "UTF-8");
		conn.setRequestProperty("Content-Type", "application/json");

		// 直接把客户端的BODY传给目标服务器
		OutputStream send = conn.getOutputStream();
		InputStream body = request.getInputStream();
		IOUtils.copy(body, send);
		send.flush();
		send.close();
		body.close();

		// 获取返回值
		BufferedReader in = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		PrintWriter out = response.getWriter();
		String line;
		while ((line = in.readLine()) != null) {
			out.println(line);
		}
		out.flush();
	}

}
