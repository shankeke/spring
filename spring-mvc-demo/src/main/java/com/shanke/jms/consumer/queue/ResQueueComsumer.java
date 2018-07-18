package com.shanke.jms.consumer.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

@Component("resQueueComsumer")
public class ResQueueComsumer implements MessageListener {
	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("QueueReceiver1接收到消息:"
					+ ((TextMessage) message).getText());
			message.acknowledge();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
