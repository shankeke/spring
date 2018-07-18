package com.shanke.web.controller.jms;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shanke.jms.invoke.JmsTemplateInvoke;

/**
 * 描述 :JMS消息处理. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016年8月6日 下午11:52:39
 */
@Controller
public class JMSController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(JMSController.class);

	private static final long TIMETOLIVE = 1000000;

	@Resource(name = "jmsQueueTemplate")
	private JmsTemplate jmsTemplate;
	@Resource
	private Queue reqQueue;
	@Resource
	private Queue resQueue;

	@RequestMapping(value = "/jmstest", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> loginForm(Model model) throws JMSException {
		Map<String, Object> map = new HashMap<String, Object>();
		/*
		 * JmsUtils jmsUtils = new JmsUtils(jmsTemplate); Message message =
		 * jmsUtils.sendAndReceive(new Long(1), reqQueue, resQueue, TIMETOLIVE);
		 */

		JmsTemplateInvoke jmsTemplateInvoke = new JmsTemplateInvoke(
				jmsTemplate, reqQueue, resQueue, TIMETOLIVE);
		String message = jmsTemplateInvoke.syncRequestProcessor("发送了");

		map.put("message", message);
		return map;
	}
}
