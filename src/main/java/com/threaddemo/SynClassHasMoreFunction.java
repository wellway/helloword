package com.threaddemo;

public class SynClassHasMoreFunction {

	public synchronized void f1() {
		long start = System.currentTimeMillis();
		try {
			Thread.sleep(1000 * 2);
			long end = System.currentTimeMillis();
			System.out.println("f1 spending time..." + (end - start));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void f2() {
		long start = System.currentTimeMillis();
		try {
			Thread.sleep(1000 * 5);
			long end = System.currentTimeMillis();
			System.out.println("f2 spending time..." + (end - start));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		final SynClassHasMoreFunction syn = new SynClassHasMoreFunction();
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true)
					syn.f1();
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true)
					syn.f2();
			}
		}).start();
	}
}
