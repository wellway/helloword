package com.designpattern;

public class Mocha extends CondimentDecorator {

    Beverage beverage;
    
    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    //加摩卡之后，价格增加1.00
    public double cost() {
        return 1.00 + beverage.cost();
    }
}

