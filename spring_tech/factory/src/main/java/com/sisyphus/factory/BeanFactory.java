package com.sisyphus.factory;

import java.io.InputStream;
import java.util.Properties;

/**
 * 创建 Bean 对象的工厂
 */
public class BeanFactory {

    private static Properties props;

    static {
        try {
            //实例化对象
            props = new Properties();
            //获取 properties 文件的流对象
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(in);
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化 properties 失败");
        }
    }

    /**
     * 根据 bean 名称获取 bean 对象
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        Object bean = null;
        try {
            String beanPath = props.getProperty(beanName);
            bean = Class.forName(beanPath).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}

