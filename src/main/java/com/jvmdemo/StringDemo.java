package com.jvmdemo;

public class StringDemo {
	public static void main(String[] args) {
		String A = "abc";
		String B = "abc";
		String C = new String("abc");
		String D = new String("abc");

		System.out.println(A == B);
		System.out.println(C == D);
		System.out.println(A == D);
	}
}
