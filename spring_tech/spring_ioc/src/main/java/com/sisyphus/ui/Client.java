package com.sisyphus.ui;

import com.sisyphus.dao.IAccountDao;
import com.sisyphus.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟表现层，用于调用业务层
 */
public class Client {
    public static void main(String[] args) {
        //获取 spring 核心容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //根据 id 获取 bean 对象
        IAccountService as = (IAccountService) ac.getBean("accountService");
        as.saveAccount();
        IAccountService as2 = (IAccountService) ac.getBean("accountService2");
        as2.saveAccount();
        IAccountService as3 = (IAccountService) ac.getBean("accountService3");
        as3.saveAccount();
        IAccountDao ad = ac.getBean("accountDao", IAccountDao.class);
        System.out.println(as);
        System.out.println(ac);

    }
}
