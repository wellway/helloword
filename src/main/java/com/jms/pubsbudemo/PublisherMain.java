package com.jms.pubsbudemo;

import javax.jms.JMSException;

public class PublisherMain {
	public static void main(String[] args){
		
		// Create publisher
		Publisher publisher = null;
		try {
			publisher = new Publisher();
			String[] stocks = new String[]{"a","b","c","d","e"};  
			// Set topics
			publisher.setTopics(stocks);

			for (int i = 0; i < 10; i++) {
				publisher.sendMessage(stocks);
				System.out.println("Publisher '" + i + " price messages");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// Close all resources
			publisher.close();
		} catch (JMSException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
}
