package com.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class BeanLifeCycle {
	public static void main(String[] args) {

		System.out.println("现在开始初始化容器");

		//	ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:beans.xml");
		ApplicationContext factory = new FileSystemXmlApplicationContext("D:/githubworspace/helloword/src/main/java/com/spring/beans.xml");

		System.out.println("容器初始化成功");
		//得到Preson，并使用
		Person person = factory.getBean("person", Person.class);
		System.out.println(person);

		System.out.println("现在开始关闭容器！");
		((AbstractApplicationContext) factory).registerShutdownHook();
	}
}
