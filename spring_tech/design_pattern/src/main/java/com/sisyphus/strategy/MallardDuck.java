package com.sisyphus.strategy;

public class MallardDuck extends Duck {
    public MallardDuck() {
        quackInterface = new Quack();
        flyInterface = new FlyWithWings();
    }
}
