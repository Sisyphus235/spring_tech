package test;

import com.sisyphus.domain.Account;
import com.sisyphus.service.IAccountService;
import config.SpringConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Junit 测试配置
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountServiceTest {

    @Autowired
    private IAccountService as = null;

    @Test
    public void testFindAll() {
        //执行方法
        List<Account> accountList = as.findAllAccount();
        for(Account account: accountList) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        //执行方法
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        //执行方法
        Account account = new Account();
        account.setName("ddd");
        account.setMoney(1000f);
        as.saveAccount(account);
    }

    @Test
    public void testUpdate() {
        //执行方法
        Account account = as.findAccountById(3);
        account.setMoney(1000f);
        System.out.println(account);
        as.updateAccount(account);
    }

    @Test
    public void testDelete() {
        //执行方法
        as.deleteAccount(4);
    }
}
