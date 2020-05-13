package com.sisyphus.ui;

import com.sisyphus.service.IAccountService;
import com.sisyphus.service.impl.AccountServiceImpl;

/**
 * 模拟表现层，用于调用业务层
 */
public class Client {
    public static void main(String[] args) {
        IAccountService as = new AccountServiceImpl(); //代码耦合
        as.saveAccount();
    }
}
