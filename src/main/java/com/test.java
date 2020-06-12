package com;

public class test {
	public static void main(String[] args) {
		Integer a = 2;
		int b = 2;
		Integer a1 = 128;
		Integer b1 = 128;
		int a2 = 400;
		int b2 = 400;
		System.out.println(2711543/1000/60); //false   1
		System.out.println(a1 == b1); //true  2
		System.out.println(a2 ==b2); //true  3

	}

	private static int g(Integer x) {
		int m = 2;
		m = x * m;
		x = m - 1;
		return x + m;
	}
}
