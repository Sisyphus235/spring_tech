package com.sisyphus.service.impl;

import com.sisyphus.dao.IAccountDao;
import com.sisyphus.domain.Account;
import com.sisyphus.service.IAccountService;
import com.sisyphus.utils.TransactionManager;

import java.util.List;

/**
 * 账户业务层实现类
 */
public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;
    private TransactionManager transactionManager;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public List<Account> findAllAccount() {
        try {
            //开启事务
            transactionManager.beginTransaction();
            //执行操作
            List<Account> accounts = accountDao.findAllAccount();
            //提交事务
            transactionManager.commit();
            //返回结果
            return accounts;
        } catch (Exception e) {
            //回滚操作
            transactionManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //释放连接
            transactionManager.release();
        }
    }

    public Account findAccountById(Integer accountId) {
        try {
            //开启事务
            transactionManager.beginTransaction();
            //执行操作
            Account account = accountDao.findAccountById(accountId);
            //提交事务
            transactionManager.commit();
            //返回结果
            return account;
        } catch (Exception e) {
            //回滚操作
            transactionManager.rollback();
            throw new RuntimeException(e);
        } finally {
            //释放连接
            transactionManager.release();
        }
    }

    public void saveAccount(Account account) {
        try {
            //开启事务
            transactionManager.beginTransaction();
            //执行操作
            accountDao.saveAccount(account);
            //提交事务
            transactionManager.commit();
        } catch (Exception e) {
            //回滚操作
            transactionManager.rollback();
        } finally {
            //释放连接
            transactionManager.release();
        }
    }

    public void updateAccount(Account account) {
        try {
            //开启事务
            transactionManager.beginTransaction();
            //执行操作
            accountDao.updateAccount(account);
            //提交事务
            transactionManager.commit();
        } catch (Exception e) {
            //回滚操作
            transactionManager.rollback();
        } finally {
            //释放连接
            transactionManager.release();
        }
    }

    public void deleteAccount(Integer accountId) {
        try {
            //开启事务
            transactionManager.beginTransaction();
            //执行操作
            accountDao.deleteAccount(accountId);
            //提交事务
            transactionManager.commit();
        } catch (Exception e) {
            //回滚操作
            transactionManager.rollback();
        } finally {
            //释放连接
            transactionManager.release();
        }
    }

    public void transfer(String sourceName, String targetName, Float money) {
        try {
            //开启事务
            transactionManager.beginTransaction();
            //执行操作
            Account source = accountDao.findAccountByName(sourceName);
            Account target = accountDao.findAccountByName(targetName);
            source.setMoney(source.getMoney() - money);
            target.setMoney(target.getMoney() + money);
            accountDao.updateAccount(source);
            accountDao.updateAccount(target);
            int i = 1/0;
            //提交事务
            transactionManager.commit();

        } catch (Exception e) {
            //回滚操作
            transactionManager.rollback();
            e.printStackTrace();
        } finally {
            //释放连接
            transactionManager.release();
        }
    }
}
