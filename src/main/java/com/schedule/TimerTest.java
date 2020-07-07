package com.schedule;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer内部是单一线程 ,如果TimerTask抛出RuntimeException,Timer会终止所有任务的运行,而ScheduledThreadPoolExecutor内部是个线程池，所以可以支持多个任务并发执行。
 * 
 * @ClassName: TimerTest
 * @Description:
 * @author yalonz
 * @date 2020年6月16日
 *
 */
public class TimerTest {
	private Timer	timer;
	private long	start;

	public TimerTest() {
		this.timer = new Timer();
		start = System.currentTimeMillis();
	}

	public void timerOne() {
		System.out.println("timerOne start");
		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				System.out.println("timerOne invoked ,the time:" + (System.currentTimeMillis() - start));
				try {
					Thread.sleep(2300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 1000,1000);
	}

	public void timerTwo() {
		System.out.println("timerTwo start");
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				System.out.println("timerTwo invoked ,the time:" + (System.currentTimeMillis() - start));
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 1000);
	}

	public static void main(String[] args) {
		TimerTest test = new TimerTest();
		test.timerOne();
//		test.timerTwo();
	}
}
