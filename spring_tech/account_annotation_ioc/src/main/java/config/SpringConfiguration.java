package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 配置类，作用相当于 .xml
 */
@Configuration
@ComponentScan("com.sisyphus")
public class SpringConfiguration {

    /**
     * 创建一个 QueryRunner 对象
     * @param dataSource
     * @return
     */
    @Bean(name="runner")
    public QueryRunner createQueryRunner(DataSource dataSource) {
        return new QueryRunner(dataSource);
    }

    /**
     * 创建数据源对象
     * @return
     */
    @Bean(name="dataSource")
    public DataSource createDataSource() {
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/spring_tech?useSSL=false");
            ds.setUser("root");
            ds.setPassword("Sisyphus12");

            return ds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
