package com.classloaderdemo.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * 
 * 这里先从一个简单的示例入手，包含3个类: 1、MyBean 一个普通的javabean ,有两个方法。 2、MyMethodInterceptor 一个拦截器， 在方法执行前，向控制台输出一些信息。 3、ProxyMain
 * 
 * 这里需要注意的是，拦截器虽然可以定义多个， 但是经过filter 后生效的只能有一个。 当然你可以不定义filter，但那样当你传入多个拦截器时，就会 enhance.create 的时候就会抛出异常。*【 但是spring 中定义可以定义多个拦截器，那是由于spring 在拦截器上又做了一层封装， 下一篇文章会介绍到 】*
 * 
 * @ClassName: ProxyMain
 * @Description:
 * @author yalonz
 * @date 2020年6月23日
 *
 */
public class ProxyMain {
	public static void main(String[] args) {
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "d:/code");

		// 第一步，创建一个Enhancer对象
		Enhancer e = new Enhancer();
		// 第二步，设置需要拦截的class
		e.setSuperclass(MyBean.class);
		// 第三部，添加两个拦截器，
		MethodInterceptor[] interceptors = new MethodInterceptor[1];
		interceptors[0] = new MyMethodInterceptor("0");//第一个拦截器
//		interceptors[1] = new MyMethodInterceptor("1");//第二个拦截器
		e.setCallbacks(interceptors);

		//第四步，设置拦截器的过滤规则
//		e.setCallbackFilter(new CallbackFilter() {
//			public int accept(Method method) {
//				if (method.getDeclaringClass().getName().equals("java.lang.Object")) {
//					// 当执行的是 从父类 object 上继承下来的方法如 equals， toString  等将调用拦截器材数组中的第二个拦截器。
//					return 0;
//				}
//				return 0;//默认调用第一个拦截器
//			}
//		});
		//cglib 生成代理类
		MyBean mybean = (MyBean) e.create();
//		mybean.toString();
		mybean.setSampleProperty("TEST222222");
//		mybean.setSampleProperty("TEST333333");

	}
}
