package com.shanke.jms.producer;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class ProducerServiceImpl implements ProducerService {

	@Resource(name = "jmsQueueTemplate")
	private JmsTemplate jmsTemplate;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.shanke.jms.producer.ProducerService#sendMessage(javax.jms.Destination,
	 *      java.lang.String)
	 */
	@Override
	public void sendMessage(Destination destination, final String message) {
		System.out.println("---------------生产者发送消息-----------------");
		System.out.println("---------------生产者发了一个消息：" + message);
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}

}
