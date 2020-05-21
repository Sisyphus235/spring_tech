package com.sisyphus.cglib;

public class Producer {

    public void sellProduct(Float money) {
        System.out.println("销售产品，获利" + money);
    }

    public void afterService(Float money) {
        System.out.println("提供售后服务，收费" + money);
    }
}
