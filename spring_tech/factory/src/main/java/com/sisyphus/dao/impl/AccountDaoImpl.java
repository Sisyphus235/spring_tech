package com.sisyphus.dao.impl;

import com.sisyphus.dao.IAccountDao;

/**
 * 账户持久层实现类
 */
public class AccountDaoImpl implements IAccountDao {
    public void saveAccount() {
        System.out.println("保存了账户");
    }
}
