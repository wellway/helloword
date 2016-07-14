package com.jms.pubsbudemo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {
	ConnectionFactory factory;
	Connection connection = null;
	// MessageConsumer：消息接受者
	MessageConsumer consumer;
	// Session：一个发送或接收消息的线程
	Session session;
	// Destination：消息的目的地;消息发送给谁.
	Destination destination;
	Destination[] destinations;
	String brokerURL = "tcp://localhost:61616";

	public Consumer() throws JMSException {
		factory = new ActiveMQConnectionFactory(brokerURL);
		connection = factory.createConnection();
		connection.start();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}

	public Session getSession() {
		return session;
	}
}
