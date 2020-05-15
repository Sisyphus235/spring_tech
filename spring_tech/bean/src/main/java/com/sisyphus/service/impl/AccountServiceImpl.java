package com.sisyphus.service.impl;

import com.sisyphus.service.IAccountService;

/**
 * 账户的业务层实现
 */
public class AccountServiceImpl implements IAccountService {

    public void saveAccount() {
        System.out.println("service 中的 saveAccount 执行了");
    }

    public void init() {
        System.out.println("AccountServiceImpl 初始化了");
    }

    public void destroy() {
        System.out.println("AccountServiceImpl 销毁了");
    }
}
