package com.sisyphus.ui;

import com.sisyphus.factory.BeanFactory;
import com.sisyphus.service.IAccountService;

/**
 * 模拟表现层，用于调用业务层
 */
public class Client {
    public static void main(String[] args) {
//        IAccountService as = new AccountServiceImpl(); //代码耦合
        IAccountService as = (IAccountService)BeanFactory.getBean("accountService"); //工厂模式反射创建解耦
        as.saveAccount();
    }
}
