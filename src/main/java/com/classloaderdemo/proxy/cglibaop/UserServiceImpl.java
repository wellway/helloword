package com.classloaderdemo.proxy.cglibaop;

//Cglib 是基于类实现的动态代理即业务类只需要实现类即可，不用强制必须实现某个接口为了突出这个优点这里没有实现接口
public class UserServiceImpl {

	public void saveUser(String username, String password) {
		System.out.println("save user[username=" + username + ",password=" + password + "]");
	}

}
