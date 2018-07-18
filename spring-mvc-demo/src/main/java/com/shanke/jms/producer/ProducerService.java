package com.shanke.jms.producer;

import javax.jms.Destination;

public interface ProducerService {

	void sendMessage(Destination destination, String message);

}