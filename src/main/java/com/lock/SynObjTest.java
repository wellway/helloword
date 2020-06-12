package com.lock;

public class SynObjTest {
	public static void main(String[] args) {
		final SynObj sy = new SynObj();
		new Thread(new Runnable() {

			@Override
			public void run() {
				sy.showA();
			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				sy.showB();
			}
		}).start();
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				sy.showC();
//			}
//		}).start();
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				sy.showC();
//			}
//		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				sy.showD("4");
			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				sy.showD("5");
			}
		}).start();
	}
}
