package com.classloaderdemo.proxy.jdk;

public class MonitorUtil {
	private static ThreadLocal<Long>	local	= new ThreadLocal<Long>();

	public static void start() {
		local.set(System.currentTimeMillis());
	}

	public static void end(String method) {
		System.out.println(method + "方法执行时间" + (local.get() - System.currentTimeMillis()));
	}
}
