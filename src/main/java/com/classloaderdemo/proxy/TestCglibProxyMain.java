package com.classloaderdemo.proxy;

public class TestCglibProxyMain {
	public static void main(String[] args) throws Exception {
		// 代理类class文件存入本地磁盘方便我们反编译查看源码  D:/$Proxy0.class
		XiaoA obj = (XiaoA) new Meipo().getInstance(XiaoA.class);
		System.out.println(obj.getClass());
		obj.findLove();
	}
}
