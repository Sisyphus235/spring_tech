package com.sisyphus.strategy;

public class FlyNoWay implements FlyInterface {

    @Override
    public void fly() {
        System.out.println("sorry, I cannot fly!");
    }
}
