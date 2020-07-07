package com.classloaderdemo.proxy.cglibaop;

public class CustomAspect implements IAspect {

	@Override
	public boolean startTransaction(Object... args) {
		System.out.println("cglib start...");
		return false;
	}

	@Override
	public void endTrasaction() {
		System.out.println("cglib end...");

	}

}
