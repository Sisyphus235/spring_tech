package test;

import config.SpringConfiguration;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试 QueryRunner 单例多例
 */
public class QueryRunnerTest {

    @Test
    public void testQueryRunner() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        QueryRunner qr = ac.getBean("runner", QueryRunner.class);
        QueryRunner qr1 = ac.getBean("runner", QueryRunner.class);
        System.out.println(qr == qr1);
    }
}
