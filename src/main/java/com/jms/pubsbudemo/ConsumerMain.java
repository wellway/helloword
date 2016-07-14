package com.jms.pubsbudemo;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;

public class ConsumerMain {
	public static void main(String[] args) {
		String[] stocks = new String[] { "a", "b", "c", "d", "e" };
		Consumer consumer;
		try {
			consumer = new Consumer();
			for (String stock : stocks) {
				Destination destination = consumer.getSession().createTopic(
						"STOCKS." + stock);
				MessageConsumer messageConsumer = consumer.getSession()
						.createConsumer(destination);
				messageConsumer.setMessageListener(new Listener());
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}
