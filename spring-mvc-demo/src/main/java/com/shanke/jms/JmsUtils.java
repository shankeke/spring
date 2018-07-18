package com.shanke.jms;

import java.io.Serializable;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.SessionCallback;

public class JmsUtils {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private JmsTemplate jmsTemplate;

	public JmsUtils(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	/**
	 * 描述 :发送消息 . <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-9-1 上午10:38:36
	 * @param obj
	 *            消息
	 * @param reqQueue
	 *            请求队列
	 * @param replyTo
	 *            响应队列
	 * @param timetolive
	 *            超时时间
	 * @return 消息对象
	 */
	public ObjectMessage send(final Serializable obj,
			final Destination reqQueue, final Destination replyTo,
			final long timetolive) {
		return jmsTemplate.execute(new SessionCallback<ObjectMessage>() {
			@Override
			public ObjectMessage doInJms(Session session) throws JMSException {
				// TODO Auto-generated method stub
				ObjectMessage msg = session.createObjectMessage(obj);
				msg.setJMSRedelivered(true);
				msg.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
				msg.setJMSPriority(1);
				msg.setJMSReplyTo(replyTo);
				msg.setJMSExpiration(timetolive);
				msg.setJMSType("1");
				msg.setJMSDestination(reqQueue);
				logger.debug("发送JMS消息成功，JMSMessageID:{}", msg.getJMSMessageID());
				return msg;
			}
		}, true);
	}

	/*
	 * public Message receive(final String messageSelector, final Destination
	 * resQueue, final long timetolive) { return jmsTemplate.execute(new
	 * SessionCallback<Message>() {
	 * 
	 * @Override public Message doInJms(Session session) throws JMSException {
	 * // TODO Auto-generated method stub MessageConsumer consumer =
	 * session.createConsumer(resQueue, messageSelector); consumer =
	 * session.createConsumer(resQueue, "JMSMessageID ='"+messageSelector+'");
	 * return consumer.receive(timetolive); } }, true); }
	 */

	/**
	 * 描述 : 接收消息，响应. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-9-1 上午10:41:57
	 * @param JMSMessageID
	 *            消息ID
	 * @param resQueue
	 *            响应队列
	 * @param timetolive
	 *            超时
	 * @return
	 */
	public Message receive(final String JMSMessageID,
			final Destination resQueue, final long timetolive) {
		return jmsTemplate.execute(new SessionCallback<Message>() {
			@Override
			public Message doInJms(Session session) throws JMSException {
				// TODO Auto-generated method stub
				MessageConsumer consumer = session.createConsumer(resQueue,
						"JMSMessageID ='" + JMSMessageID + "'");
				logger.debug("接收JMS消息，JMSMessageID:{}", JMSMessageID);
				return consumer.receive(timetolive);
			}
		}, true);
	}

	/**
	 * 描述 :同步发送接收消息. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-9-1 上午10:29:36
	 * @param obj
	 *            发送消息
	 * @param reqQueue
	 *            发送队列
	 * @param resQueue
	 *            响应队列
	 * @param timetolive
	 *            超时时间
	 * @return 响应消息
	 * @throws JMSException
	 *             如果错误抛出异常
	 */
	@SuppressWarnings("unused")
	public Message sendAndReceive(Serializable message, Destination reqQueue,
			Destination resQueue, long timetolive) throws JMSException {
		try {
			Object obj = jmsTemplate.receiveSelectedAndConvert(resQueue, "");
			return receive(send(message, reqQueue, resQueue, timetolive)
					.getJMSMessageID(), resQueue, timetolive);
		} catch (JMSException e) {
			logger.error("JMS消息同步处理异常-reqQueue:{},resQueue:{}",
					reqQueue.toString(), resQueue.toString(), e.getMessage(), e);
			throw new JMSException("JMS消息同步处理异常，replyTo:" + reqQueue.toString()
					+ "，resQueue:" + resQueue.toString());
		}
	}
}
