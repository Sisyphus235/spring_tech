package com.sisyphus.strategy;

public class Quack implements QuackInterface {

    @Override
    public void quack() {
        System.out.println("quack!");
    }
}
