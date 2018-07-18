package com.shanke.jms.invoke;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.jms.BytesMessage;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * 描述 : <类型描述>. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016-9-1 下午2:37:58
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class JmsTemplateInvoke {

	private static final Logger logger = LoggerFactory
			.getLogger(JmsTemplateInvoke.class);

	/** JmsTemplate模板 */
	private JmsTemplate jmsTemplate;

	/** 请求队列 */
	private Queue requestQueue;

	/** 响应队列 */
	private Queue responseQueue;

	/** 响应消息接收超时时间 */
	private long receiveTimeout = 100000;

	public JmsTemplateInvoke(JmsTemplate jmsTemplate, Queue requestQueue,
			Queue responseQueue) {
		this(jmsTemplate, requestQueue, responseQueue, 100000);
	}

	public JmsTemplateInvoke(JmsTemplate jmsTemplate, Queue requestQueue,
			Queue responseQueue, long receiveTimeout) {
		this.jmsTemplate = jmsTemplate;
		this.requestQueue = requestQueue;
		this.responseQueue = responseQueue;
		this.receiveTimeout = receiveTimeout;
	}

	/**
	 * 描述 : MQ异步接口调用 . <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-9-1 下午2:33:17
	 * @param requestMessage
	 */
	public void asynRequestProcessor(String requestMessage) {
		Map attributes = new HashMap();
		attributes.put(MQConstants.JMS_CORRELATION_ID,
				RequestIDGenerator.generateMessageRequestID());
		sendToProcessQueue(requestMessage, attributes);
	}

	/**
	 * 描述 : MQ同步接口调用. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-9-1 下午2:33:47
	 * @param requestMessage
	 * @return
	 */
	public String syncRequestProcessor(String requestMessage) {
		Map attributes = new HashMap();
		String jmsCorrelationID = RequestIDGenerator.generateMessageRequestID();
		attributes.put(MQConstants.JMS_CORRELATION_ID, jmsCorrelationID);

		// 向队列中发送消息
		sendToProcessQueue(requestMessage, attributes);

		// 根据jmsCorrelationID来接收同步返回的消息
		String messageText = reciveFromReceiptQueue(jmsCorrelationID);

		return messageText;
	}

	/**
	 * 描述 :向队列中发送消息. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-9-1 下午2:34:22
	 * @param msgText
	 * @param attributes
	 */
	public void sendToProcessQueue(final String msgText, Map attributes) {

		final String correlationID = (String) attributes
				.get(MQConstants.JMS_CORRELATION_ID);
		final Destination replyTo = (Destination) attributes
				.get(MQConstants.JMS_REPLY_TO);
		final String type = (String) attributes.get(MQConstants.JMS_TYPE);
		jmsTemplate.send(requestQueue, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				BytesMessage bm = session.createBytesMessage();
				// bm.setIntProperty(MQConstants.JMS_IBM_CHARACTER_SET, 1208);
				try {
					bm.writeBytes(msgText.getBytes("utf-8"));
				} catch (UnsupportedEncodingException e) {
					logger.error(e.getMessage(), e);
					throw new JMSException(e.getMessage());
				}
				bm.setJMSCorrelationID(correlationID);
				bm.setJMSReplyTo(replyTo);
				bm.setJMSType(type);
				return bm;
			}
		});
		logger.debug(
				"[MessageProcessor:sendToProcessQueue()]: [jmsCorrelationID={}]: [message={}] send to queue is successful!",
				correlationID, msgText);

	}

	/**
	 * 描述 :根据jmsCorrelationID来接收同步返回的消息 . <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-9-1 下午2:14:29
	 * @param jmsCorrelationID
	 * @return
	 */
	private String reciveFromReceiptQueue(String jmsCorrelationID) {
		// 设置接收等待的超时时间
		BytesMessage msg = null;
		String msgText = null;
		ByteArrayOutputStream byteStream = null;
		try {
			jmsTemplate.setReceiveTimeout(receiveTimeout);

			// 设置根据JMSCorrelationID过滤需要接收的消息
			String selector = "JMSCorrelationID = 'ID:"
					+ byte2HexStr(jmsCorrelationID.getBytes()) + "'";
			// 接收消息
			msg = (BytesMessage) jmsTemplate.receiveSelected(responseQueue,
					selector);//
			if (msg == null) {
				throw new RuntimeException("socket timeout!"
						+ "jmsCorrelationID=" + jmsCorrelationID);
			}
			byteStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[(int) msg.getBodyLength()];
			msg.readBytes(buffer);
			byteStream.write(buffer, 0, (int) msg.getBodyLength());
			msgText = new String(byteStream.toByteArray());
			logger.debug(
					"[MessageProcessor:reciveFromReceiptQueue()]: [jmsCorrelationID={}]: [response={}] receive from queue is successful!",
					jmsCorrelationID, msgText);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (byteStream != null) {
					byteStream.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return msgText;
	}

	public static String byte2HexStr(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public Queue getRequestQueue() {
		return requestQueue;
	}

	public void setRequestQueue(Queue requestQueue) {
		this.requestQueue = requestQueue;
	}

	public long getReceiveTimeout() {
		return receiveTimeout;
	}

	public void setReceiveTimeout(long receiveTimeout) {
		this.receiveTimeout = receiveTimeout;
	}

	public Queue getResponseQueue() {
		return responseQueue;
	}

	public void setResponseQueue(Queue responseQueue) {
		this.responseQueue = responseQueue;
	}
}
