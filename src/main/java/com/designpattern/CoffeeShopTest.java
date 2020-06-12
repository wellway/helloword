package com.designpattern;

/**
* @ClassName: CoffeeShopTest
* @Description:装饰者模式
* @author yalonz
* @date 2020年1月16日
*
*/
public class CoffeeShopTest {
	public static void main(String[] args) {
		Beverage beverage = new Espresso();

		beverage = new Milk(beverage);
		beverage = new Mocha(beverage);
		beverage = new Whip(beverage);
		System.out.println("Espresso饮品组成:" + beverage.getDescription() + ", 总价格:$" + beverage.cost());

		Beverage beverage2 = new Latte();
		beverage2 = new Whip(beverage2);
		beverage2 = new Mocha(beverage2);
		System.out.println("Latte饮品组成:" + beverage2.getDescription() + ", 总价格:$" + beverage2.cost());

		Beverage beverage3 = new Cappuccino();
		beverage3 = new Mocha(beverage3);
		beverage3 = new Whip(beverage3);
		System.out.println("Cappuccino饮品组成:" + beverage3.getDescription() + ", 总价格:$" + beverage3.cost());

	}
}
