package com.lock;

public class SyncBlock {
	static Object	obj	= new Object();

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			final int j = i;
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println("==="+j);
						SyncBlockParam(j);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}).start();
		}

	}

	public static void SyncBlockParam(Integer id) throws InterruptedException {
		//synchronized锁可重入锁，获取锁阻塞顺序1，2,3,4，释放锁执行顺序：4,3,2，1
		System.out.println(id);
		synchronized (obj) {
			System.out.println(System.currentTimeMillis() + "___" + id);
			Thread.sleep(1000);
		}
	}

	public static void _SyncBlockParam(Integer id) throws InterruptedException {
		//synchronized锁可重入锁，获取锁阻塞顺序1，2,3,4，释放锁执行顺序：4,3,2，1
		synchronized (id) {
			System.out.println(System.currentTimeMillis() + "___" + id);
			Thread.sleep(1000);
		}
	}
}
