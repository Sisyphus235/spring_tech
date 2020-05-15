package com.sisyphus.ui;

import com.sisyphus.service.IAccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟表现层，用于调用业务层
 */
public class Client {
    public static void main(String[] args) {
        //获取 spring 核心容器
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //根据 id 获取 bean 对象
        IAccountService as = (IAccountService) ac.getBean("accountService");
        IAccountService as2 = (IAccountService) ac.getBean("accountService");
        System.out.println(as == as2);
        as.saveAccount();

        ac.close();
    }
}
