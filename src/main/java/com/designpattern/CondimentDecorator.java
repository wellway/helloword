package com.designpattern;

/**
 * @ClassName: CondimentDecorator
 * @Description:辅料抽象类
 * @author yalonz
 * @date 2020年1月16日
 *
 */
public abstract class CondimentDecorator extends Beverage {
	//所有调料装饰者必须重新实现getDescription();
	public abstract String getDescription();
}
