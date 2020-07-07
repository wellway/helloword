package com.classloaderdemo.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class MyMethodInterceptor implements MethodInterceptor {

	private String	interceptorNo;

	public MyMethodInterceptor(String interceptorNo) {
		super();
		this.interceptorNo = interceptorNo;
	}

	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		Object retValFromSuper = null;

		System.out.println("#######interceptor:" + interceptorNo + "####execut method:" + method.getName());

		retValFromSuper = proxy.invokeSuper(obj, args);
		System.out.println("return:"+retValFromSuper);
		return retValFromSuper;
	}
}
