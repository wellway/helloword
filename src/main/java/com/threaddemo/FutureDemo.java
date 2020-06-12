package com.threaddemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService executor = Executors.newCachedThreadPool();
		Future future = executor.submit(new Task());
		//这一步get会阻塞当前线程
		System.out.println(future.get());
		executor.shutdown();
	}

	private static class Task implements Callable<Integer> {

		@Override
		public Integer call() throws Exception {
			System.out.println("子线程在进行计算");
			//Thread.sleep(2000);
			return 1;

		}

	}
}
