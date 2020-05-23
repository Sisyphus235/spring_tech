package com.sisyphus.factory;

import com.sisyphus.service.IAccountService;
import com.sisyphus.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于创建 service 的代理对象工厂
 */
public class BeanFactory {

    private IAccountService accountService;

    private TransactionManager transactionManager;

    public final void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /**
     * 获取 service 代理对象
     * @return
     */
    public IAccountService getAccountService() {
        return (IAccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 添加事务支持
                     * @param proxy
                     * @param method
                     * @param args
                     * @return
                     * @throws Throwable
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object rtValue = null;
                        try {
                            //开启事务
                            transactionManager.beginTransaction();
                            //执行操作
                            rtValue = method.invoke(accountService, args);
                            //提交事务
                            transactionManager.commit();
                            return rtValue;
                        } catch (Exception e) {
                            //回滚操作
                            transactionManager.rollback();
                            throw new RuntimeException(e);
                        } finally {
                            //释放连接
                            transactionManager.release();
                        }
                    }
                });
    }
}
