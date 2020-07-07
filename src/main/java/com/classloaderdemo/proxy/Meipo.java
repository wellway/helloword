package com.classloaderdemo.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class Meipo implements MethodInterceptor {

	public Object getInstance(Class clazz) throws Exception {
		//输出class文件
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\code");
		//第一步：创建一个Enchancer对象
		Enhancer enhancer = new Enhancer();
		//第二步:设置需要拦截的类
		enhancer.setSuperclass(clazz);
		//第三步：设置回调，添加拦截器
		enhancer.setCallback(this);
		//生成代理类对象并且返回
		return enhancer.create();
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("我是媒婆：");
		System.out.println("------------");
		proxy.invokeSuper(obj, args);
		System.out.println("------------");
		System.out.println("寻找中....找到啦");
		return null;
	}

}
