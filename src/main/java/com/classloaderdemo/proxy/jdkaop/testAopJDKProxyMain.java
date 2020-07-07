package com.classloaderdemo.proxy.jdkaop;

import org.junit.Test;

// 基于动态代理类AOP测试案例
public class testAopJDKProxyMain {
	@Test
	public void testJDKProxy() throws Exception {
		System.out.println("无代理前 调用方法 userService.saveUser 输出......");
		IUserService userService = new UserServiceImpl();
		userService.saveUser("zby", "1234567890");
		System.out.println("有代理后AOP 是怎么样的？ Proxy......");
		IUserService proxyUserService = (IUserService) JDKDynamicProxyGenerator.generatorJDKProxy(userService, new CustomAspect());
		proxyUserService.saveUser("zby", "1234567890");
		/** 制造异常,两个入参都是null */
		proxyUserService.saveUser(null, null);
	}
}
