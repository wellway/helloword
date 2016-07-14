package com.lockdemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 不同方法中共享锁，他们相互之间会竞争得到锁
 * 
 * @author yalongz
 *
 */
public class ReentrantLockDemo {
	ReentrantLock lock = new ReentrantLock();
	private Map<String, Object> map = new HashMap<String, Object>();

	public void read(String i) {

		try {
			long start = System.currentTimeMillis();
			System.out.println("start lock read: " + i + "..syc count.."
					+ lock.getHoldCount());
			lock.lock();
			long end = System.currentTimeMillis();
			System.out.println("end   lock read: " + i + "..spend times.."
					+ (end - start) + "..syc count.." + lock.getHoldCount());
			Thread.sleep(1000 * 8);
			System.out.println(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void write(String i) {
		try {
			long start = System.currentTimeMillis();
			System.out.println("start lock write :" + i + "..syc count.."
					+ lock.getHoldCount());
			lock.lock();
			long end = System.currentTimeMillis();
			System.out.println("end   lock write :" + i + "..spend times.."
					+ (end - start) + "..syc count.." + lock.getHoldCount());
			Thread.sleep(2000);
			map.put(i, i);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		final ReentrantLockDemo reent = new ReentrantLockDemo();
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					reent.write("W" + i);
				}
			}
		});
		executor.execute(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					reent.write("WD" + i);
				}
			}
		});

		executor.execute(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					reent.read("R" + i);
				}

			}
		});

		executor.execute(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					reent.read("RD" + i);
				}

			}
		});

		try {
			TimeUnit.MINUTES.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		executor.shutdown();
	}

}
