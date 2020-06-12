package com.commons;

public class WhileReturnTest {
	public static void main(String[] args) {
		boolean flag = false;
		for (int i = 10; i < 100; i++) {
			for (int j = 0; j < i; j++) {
				System.out.println("aaaaaaaaaaaaaaaa" + i);
				return;
			}
			flag = true;
			System.out.println("----------" + i);
		}
		System.out.println("end");
	}
}
