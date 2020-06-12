package com.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
	ReentrantLock	lock	= new ReentrantLock();

	public void lock() throws InterruptedException {
		System.out.println("lock");
		lock.lock();
		System.out.println("加锁");
		TimeUnit.SECONDS.sleep(4);
		lock.unlock();
		System.out.println("lock--");
	}

	public void unlock() throws InterruptedException {
		System.out.println("unlock");
		lock.lock();
		System.out.println("解锁");
		TimeUnit.SECONDS.sleep(3);
		lock.unlock();
		System.out.println("unlock--");
	}

	public static void main(String[] args) {
		LockTest lock = new LockTest();
		ExecutorService exec = Executors.newCachedThreadPool();
		Runnable run = new Runnable() {
			@Override
			public void run() {
				try {
					lock.lock();
					//					lockUutils.getA();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Runnable run2 = new Runnable() {
			@Override
			public void run() {
				try {
					//					lockUutils.getB();
					lock.unlock();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};
		exec.execute(run);
		exec.execute(run2);
//		exec.shutdown();
	}
}
