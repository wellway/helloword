package com.classloaderdemo.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.beans.BeanGenerator;

import org.junit.Assert;
import org.junit.Test;
//应用cglib动态生成对象
public class DynamicCreateObj {

	@Test
	public void testBeanGenerator() throws Exception {
		BeanGenerator beanGenerator = new BeanGenerator();
		beanGenerator.addProperty("value", String.class);
		beanGenerator.addProperty("name", String.class);
		Object myBean = beanGenerator.create();
		Method setValue = myBean.getClass().getMethod("setValue", String.class);
		setValue.invoke(myBean, "Hello cglib");

		Method setName = myBean.getClass().getMethod("setName", String.class);
		setName.invoke(myBean, "heey");

		Method getValue = myBean.getClass().getMethod("getValue");
		Method getName = myBean.getClass().getMethod("getName");
		System.out.println(getValue.invoke(myBean));
		System.out.println(getName.invoke(myBean));
		Assert.assertEquals("Hello cglib", getValue.invoke(myBean));
	}
}
