package com.designpattern;

/**
 * 饮品抽象类:
* @ClassName: Beverage
* @Description:
* @author yalonz
* @date 2020年1月16日
*
*/
public abstract class Beverage {
	protected String	description	= "Unkonwn Beverage";

	public String getDescription() {
		return description;
	}

	public abstract double cost();
}
