package com.jdbc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.PropertyConfigurator;

import com.commons.LogManagement;

public class PoolTest {
	public static void main(String[] args) throws InterruptedException {
		PropertyConfigurator.configure("log4j.properties");
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//		final CountDownLatch countDownLatch = new CountDownLatch(100);
		for (int i = 0; i < 16000; i++) {
			LogManagement.Info(""+i);
			cachedThreadPool.execute(() -> {
                new Thread(new MySQLDemo()).start();
//                countDownLatch.countDown();
            });
		}
		LogManagement.Info("overs");
		Thread.sleep(1000*600);
	}
}
