package com.sisyphus.strategy;

public abstract class Duck {
    FlyInterface flyInterface;
    QuackInterface quackInterface;

    public void performFly() {
        flyInterface.fly();
    }

    public void performQuack() {
        quackInterface.quack();
    }

    public void swim() {
        System.out.println("all duck can swim!");
    }

    public void setFlyInterface(FlyInterface flyInterface) {
        this.flyInterface = flyInterface;
    }

    public void setQuackInterface(QuackInterface quackInterface) {
        this.quackInterface = quackInterface;
    }
}
