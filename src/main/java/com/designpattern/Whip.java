package com.designpattern;

public class Whip extends CondimentDecorator {

    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }

    //加奶泡之后，价格增加0.60
    public double cost() {
        return .60 + beverage.cost();
    }
}