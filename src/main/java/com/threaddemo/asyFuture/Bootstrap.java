package com.threaddemo.asyFuture;

/*我有N个耗时任务，可能是一次网络请求，可能是一个耗时文件IO，可能是一堆复杂的逻辑，我在主线程里发起这个任务的调用，
 但不希望它阻塞主线程，而期望它执行完毕（成功\失败）后，来发起一次回调，最好还有超时、异常的回调控制。
 据此，我们拆分出几个角色，master主线程，调度器（发起异步调用），worker（异步工作线程）。然后就是将他们组合起来，
 完成各种异步回调，以及每个worker的正常、异常、超时等的回调。
 */
public class Bootstrap {
	public static void main(String[] args) {
		Bootstrap bootstrap = new Bootstrap();
		Worker worker = bootstrap.newWorker();
		System.out.println("create wrapper...");
		Wrapper wrapper = new Wrapper();
		wrapper.addListener(new Listener() {
			
			@Override
			public void result(Object result) {
				System.out.println(Thread.currentThread().getName());
				System.out.println(result);
			}
		});
		wrapper.setParams("good");
		wrapper.setWorker(worker);
		bootstrap.doworker(wrapper);
//		.addListener(new Listener() {
//
//			@Override
//			public void result(Object result) {
//				
//			}
//		});
	}

	private Wrapper doworker(Wrapper wrapper) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				Worker worker = wrapper.getWorker();
				String result = worker.action(wrapper.getParams());
				System.out.println("result:" + result);
				System.out.println("listener:" + wrapper.getListener());
				wrapper.getListener().result(result);

			}
		}).start();

		return wrapper;
	}

	private Worker newWorker() {
		return new Worker() {

			@Override
			public String action(Object object) {
//				try {
//					System.out.println("start action...");
//					Thread.sleep(100);
//					System.out.println("end action...");
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				return object + " world";
			}
		};
	}
}
