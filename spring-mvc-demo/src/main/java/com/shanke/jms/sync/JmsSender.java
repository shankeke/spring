package com.shanke.jms.sync;

import javax.annotation.Resource;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.SessionCallback;

public class JmsSender {
	@SuppressWarnings("unused")
	private static final long TIMETOLIVE = 300000;

	@Resource
	private JmsTemplate jmsTemplate;
	@Resource
	private Destination destination;
	@Resource
	private Destination replyTo;

	public String send(final Message message, final Destination replyTo,
			final long timetolive) {
		return jmsTemplate.execute(new SessionCallback<String>() {
			@Override
			public String doInJms(Session session) throws JMSException {
				// TODO Auto-generated method stub
				Message msg = session.createMessage();
				msg.setJMSRedelivered(true);
				msg.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
				msg.setJMSPriority(1);
				msg.setJMSReplyTo(replyTo);
				msg.setJMSExpiration(timetolive);
				msg.setJMSType("1");
				return msg.getJMSMessageID();
			}
		}, true);
	}

	public Message receive(final String messageSelector,
			final Destination resQueue, final long timetolive) {
		return jmsTemplate.execute(new SessionCallback<Message>() {
			@Override
			public Message doInJms(Session session) throws JMSException {
				// TODO Auto-generated method stub
				MessageConsumer consumer = session.createConsumer(resQueue,
						messageSelector);
				return consumer.receive(timetolive);
			}
		}, true);
	}
}
