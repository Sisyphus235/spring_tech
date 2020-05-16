package com.sisyphus.test;

import com.sisyphus.domain.Account;
import com.sisyphus.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Junit 测试配置
 */
public class AccountServiceTest {

    @Test
    public void testFindAll() {
        //获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //获取业务层对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        //执行方法
        List<Account> accountList = as.findAllAccount();
        for(Account account: accountList) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        //获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //获取业务层对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        //执行方法
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        //获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //获取业务层对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        //执行方法
        Account account = new Account();
        account.setName("ddd");
        account.setMoney(1000f);
        as.saveAccount(account);
    }

    @Test
    public void testUpdate() {
        //获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //获取业务层对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        //执行方法
        Account account = as.findAccountById(3);
        account.setMoney(1000f);
        System.out.println(account);
        as.updateAccount(account);
    }

    @Test
    public void testDelete() {
        //获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //获取业务层对象
        IAccountService as = ac.getBean("accountService", IAccountService.class);
        //执行方法
        as.deleteAccount(4);
    }
}
