package com.sisyphus.service.impl;

import com.sisyphus.dao.IAccountDao;
import com.sisyphus.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/**
 * 账户的业务层实现
 */
public class AccountServiceImpl3 implements IAccountService {

    private String[] myStrs;
    private List<String> myList;
    private Set<String> mySet;
    private Map<String, String> myMap;
    private Properties myProps;

    public void setMyStrs(String[] myStrs) {
        this.myStrs = myStrs;
    }

    public void setMyList(List<String> myList) {
        this.myList = myList;
    }

    public void setMySet(Set<String> mySet) {
        this.mySet = mySet;
    }

    public void setMyMap(Map<String, String> myMap) {
        this.myMap = myMap;
    }

    public void setMyProps(Properties myProps) {
        this.myProps = myProps;
    }

    public void saveAccount() {
        System.out.println("AccountServiceImpl3 saveAccount 执行了");
        System.out.println(Arrays.toString(myStrs));
        System.out.println(myList);
        System.out.println(mySet);
        System.out.println(myMap);
        System.out.println(myProps);
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountDao accountDao = ac.getBean("accountDao", IAccountDao.class);
        accountDao.saveAccount();
    }
}
