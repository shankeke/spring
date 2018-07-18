package com.shanke.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述 :spring boot 处理servlet. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016年7月30日 下午10:17:44
 */
// 不指定name的情况下，name默认值为类全路径，即org.springboot.sample.servlet.MyServlet2
@WebServlet(urlPatterns = "/test/myservlet2", description = "测试servlet2")
public class MyServlet2 extends HttpServlet {
	private static final long serialVersionUID = -416300365529985105L;
	private static final Logger logger = LoggerFactory.getLogger(MyServlet2.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info(">>>>>>>>>>doGet2()<<<<<<<<<<<");
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info(">>>>>>>>>>doPost2()<<<<<<<<<<<");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Hello World</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>大家好，我的名字叫Servlet</h1>");
		out.println("</body>");
		out.println("</html>");
	}
}
