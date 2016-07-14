package com.mapdemo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

//	 private static ConcurrentHashMap<Integer, Integer> map = new  ConcurrentHashMap<Integer, Integer>();

	private static Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	public static void main(String[] args) {
		
				new Thread(new Runnable() {

					public void run() {
						map.put(3, 33);
					}
				}).start();

				new Thread(new Runnable() {

					public void run() {
						map.put(5, 55);
					}
				}).start();
				new Thread(new Runnable() {
					public void run() {
						map.put(4, 44);
					}
				}).start();

				new Thread(new Runnable() {
					public void run() {
						map.put(2, 22);
					}
				}).start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(map);
			
	}
}