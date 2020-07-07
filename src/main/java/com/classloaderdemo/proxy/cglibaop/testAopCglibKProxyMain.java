package com.classloaderdemo.proxy.cglibaop;

import org.junit.Test;

//基于动态代理类AOP测试案例
public class testAopCglibKProxyMain {
	@Test
	public void testCglibProxy() {
		System.out.println("before Proxy......");
		UserServiceImpl userService = new UserServiceImpl();
		userService.saveUser("zby", "1234567890");
		System.out.println("引入Cglib  Proxy代理库 后......");
		UserServiceImpl proxyUserService = (UserServiceImpl) CglibProxyGenerator.generatorCglibProxy(userService, new CustomAspect());
		proxyUserService.saveUser("zby", "1234567890");
	}
}
