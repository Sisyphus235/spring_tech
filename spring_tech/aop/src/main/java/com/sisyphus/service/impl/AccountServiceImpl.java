package com.sisyphus.service.impl;

import com.sisyphus.service.IAccountService;

/**
 * 账户业务层实现类
 */
public class AccountServiceImpl implements IAccountService {

    public void saveAccount() {
        System.out.println("执行了保存账户");
    }

    public void updateAccount(int i) {
        System.out.println("执行了更新账户" + i);
    }

    public int deleteAccount() {
        System.out.println("执行了删除账户");
        return 0;
    }
}
