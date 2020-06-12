package com.lock;

import java.util.concurrent.TimeUnit;

public class lockUutils {
	public static  synchronized void getA() throws InterruptedException {
		System.out.println("A");
		TimeUnit.SECONDS.sleep(5);
		System.out.println("A---");
	}

	public static synchronized void getB() throws InterruptedException {
		System.out.println("b");
		TimeUnit.SECONDS.sleep(2);
		System.out.println("b--");
	}
}
