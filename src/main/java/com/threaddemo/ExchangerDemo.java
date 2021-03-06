package com.threaddemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * Exchanger可以在两个线程之间交换数据，只能是2个线程，他不支持更多的线程之间互换数据。
 *
 * 当线程A调用Exchange对象的exchange()方法后，他会陷入阻塞状态，直到线程B也调用了exchange()方法，然后以线程安全的方式交换数据，
 * 之后线程A和B继续运行
 * 
 * @author yalongz
 *
 */
public class ExchangerDemo {
	public static void main(String[] args) {
		Exchanger<List<Integer>> exchanger = new Exchanger<>();
		new Consumer(exchanger).start();
		new Producer(exchanger).start();
	}
}

class Producer extends Thread {
	List<Integer> list = new ArrayList<>();
	Exchanger<List<Integer>> exchanger = null;
	
	public Producer(Exchanger<List<Integer>> exchanger) {
		super();
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			
			System.out.print("==="+i+";"+list+";");
			list.clear();
			for (int j = 0; j < 10; j++) {
				list.add(i + j);
			}
			try {
				list = exchanger.exchange(list);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Consumer extends Thread {
	List<Integer> list = new ArrayList<>();
	Exchanger<List<Integer>> exchanger = null;

	public Consumer(Exchanger<List<Integer>> exchanger) {
		super();
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				list = exchanger.exchange(list);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int j = 0; j < 10; j++) {
				
				if (j == 9) {
					System.out.println(list.get(j)+"");
				} else {
					System.out.print(list.get(j)+"  ");
				}
				list.set(j, list.get(j)+10);
			}
		}

	}
}
