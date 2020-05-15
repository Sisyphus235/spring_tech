package com.sisyphus.factory;

import com.sisyphus.service.IAccountService;
import com.sisyphus.service.impl.AccountServiceImpl;

public class StaticFactory {
    public static IAccountService getAccountService() {
        return new AccountServiceImpl();
    }
}
