package com.threaddemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
/**
 * CountDownLatch，一个同步辅助类(一个同步工具类)，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待,
 * 直到其他线程的操作执行完后再执行
 * 
 * 
 * CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。每当一个线程完成了自己的任务后，计数器的值就会减1。
 * 当计数器值到达0时，它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。
 * 
 * 
 *  CountDownLatch是什么？
 *	CountDownLatch如何工作？
 *	在实时系统中的应用场景
 *	应用范例
 * @author yalongz
 *
 */
public class CountDownLatchDemo {
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(2);// 两个工人的协作
		Worker worker1 = new Worker("Mr wang", 5000, latch);
		Worker worker2 = new Worker("Lily", 3000, latch);
		worker1.start();
		worker2.start();
		try {
			latch.await();// 等待所有工人完成工作
			System.out.println("all work done at " + sdf.format(new Date()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	static class Worker extends Thread {
		String workerName;
		int workTime;
		CountDownLatch latch;

		public Worker(String workerName, int workTime, CountDownLatch latch) {
			this.workerName = workerName;
			this.workTime = workTime;
			this.latch = latch;
		}

		public void run() {
			try {
				System.out.println("Worker " + workerName + " do work begin at "+ sdf.format(new Date()));
				doWork();// 工作了
				System.out.println("Worker " + workerName + " do work complete at "+ sdf.format(new Date()));
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				latch.countDown();// 工人完成工作，计数器减一 (最好放finally执行)
			}						
		}

		private void doWork() {
			try {
				Thread.sleep(workTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
