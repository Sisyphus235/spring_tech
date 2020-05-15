package com.sisyphus.factory;

import com.sisyphus.service.IAccountService;
import com.sisyphus.service.impl.AccountServiceImpl;

public class InstanceFactory {
    public IAccountService getAccountService() {
        return new AccountServiceImpl();
    }
}
