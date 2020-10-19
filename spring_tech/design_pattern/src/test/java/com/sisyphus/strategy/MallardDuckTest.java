package com.sisyphus.strategy;

import static org.junit.Assert.*;

public class MallardDuckTest {
    public static void main(String[] args) {
        MallardDuck mallardDuck = new MallardDuck();
        mallardDuck.performFly();
        mallardDuck.performQuack();
        System.out.println("Ooh, I have been transformed!!!");
        mallardDuck.setFlyInterface(new FlyNoWay());
        mallardDuck.setQuackInterface(new Squeak());
        mallardDuck.performFly();
        mallardDuck.performQuack();
    }
}