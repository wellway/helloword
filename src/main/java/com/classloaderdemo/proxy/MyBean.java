package com.classloaderdemo.proxy;

public class MyBean implements java.io.Serializable {

	String	sampleProperty	= "original";

	public MyBean() {
	}

	public MyBean(String sampleProperty) {
		super();
		this.sampleProperty = sampleProperty;
	}

	public void setSampleProperty(String value) {
		System.out.println(this + "========in bean setSampleProperty:::: " + sampleProperty);

		this.sampleProperty = value;
	}

	public int doPlug(int num) {
		return num + 1;
	}

}