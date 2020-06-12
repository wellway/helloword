package com.classloaderdemo.proxy;

import static org.junit.Assert.assertEquals;
import net.sf.cglib.beans.BeanCopier;

import org.junit.Test;

public class BeanCopierTest {
	@Test
	public void testBeanCopier() throws Exception {
		BeanCopier copier = BeanCopier.create(SampleBean.class, OtherSampleBean.class, false);//设置为true，则使用converter
		SampleBean myBean = new SampleBean();
		myBean.setValue("Hello cglib");
		OtherSampleBean otherBean = new OtherSampleBean();
		copier.copy(myBean, otherBean, null); //设置为true，则传入converter指明怎么进行转换
		assertEquals("Hello cglib", otherBean.getValue());
	}
}
