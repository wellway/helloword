package com.threaddemo.asyFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

public class BootstrapNew {
	public static void main(String[] args) {
		BootstrapNew bootstrap = new BootstrapNew();
		Worker worker = bootstrap.newWorker();

		Wrapper wrapper = new Wrapper();
		wrapper.setWorker(worker);
		wrapper.setParams("hello");
		//添加结果回调器
		wrapper.addListener(new Listener() {
			@Override
			public void result(Object result) {
				System.out.println(result);
			}
		});
		
		CompletableFuture future = CompletableFuture.supplyAsync(() -> bootstrap.doWork(wrapper));
		try {
			future.get(800, TimeUnit.MILLISECONDS);
		} catch (InterruptedException | TimeoutException | ExecutionException e) {
			//超时了
			wrapper.getListener().result("time out exception");
		}

	}

	private Wrapper doWork(Wrapper wrapper) {
		Worker worker = wrapper.getWorker();
		String result = worker.action(wrapper.getParams());
		wrapper.getListener().result(result);

		return wrapper;
	}

	private Worker newWorker() {
		return new Worker() {
			@Override
			public String action(Object object) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return object + " world";
			}
		};
	}

}
