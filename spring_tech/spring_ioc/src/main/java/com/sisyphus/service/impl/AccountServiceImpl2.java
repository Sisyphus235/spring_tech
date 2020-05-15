package com.sisyphus.service.impl;

import com.sisyphus.dao.IAccountDao;
import com.sisyphus.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * 账户的业务层实现
 */
public class AccountServiceImpl2 implements IAccountService {

    private String name;
    private Integer age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    private Date birthday;

    public void saveAccount() {
        System.out.println("AccountServiceImpl2 saveAccount 执行了" + ", " + name + ", " + age + ", " + birthday);
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountDao accountDao = ac.getBean("accountDao", IAccountDao.class);
        accountDao.saveAccount();
    }
}
