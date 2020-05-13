package com.sisyphus.service.impl;

import com.sisyphus.dao.IAccountDao;
import com.sisyphus.factory.BeanFactory;
import com.sisyphus.service.IAccountService;

/**
 * 账户的业务层实现
 */
public class AccountServiceImpl implements IAccountService {

//    private IAccountDao accountDao = new AccountDaoImpl();//代码耦合
    private IAccountDao accountDao = (IAccountDao)BeanFactory.getBean("accountDao"); //工厂模式反射创建解耦

    public void saveAccount() {
        accountDao.saveAccount();
    }
}
