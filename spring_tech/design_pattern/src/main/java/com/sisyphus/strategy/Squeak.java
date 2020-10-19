package com.sisyphus.strategy;

public class Squeak implements QuackInterface {

    @Override
    public void quack() {
        System.out.println("squeak!");
    }
}
