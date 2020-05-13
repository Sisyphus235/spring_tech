package com.sisyphus.service.impl;

import com.sisyphus.dao.IAccountDao;
import com.sisyphus.dao.impl.AccountDaoImpl;
import com.sisyphus.service.IAccountService;

/**
 * 账户的业务层实现
 */
public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao = new AccountDaoImpl();//代码耦合

    public void saveAccount() {
        accountDao.saveAccount();
    }
}
