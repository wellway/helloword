package com.classloaderdemo.proxy.jdkaop;

//定义切点角色接口 因为是基于JDK实现的Aop ，所以委托类需要基于接口实现。
//AOP基于动态代理 实现
public interface IUserService {
	void saveUser(String username, String password) throws Exception;
}
