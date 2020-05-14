package com.sisyphus.factory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 创建 Bean 对象的工厂
 */
public class BeanFactory {

    private static Properties props;

    //定义一个容器，用来存放单例对象集
    private static Map<String, Object> beans;

    //为 Properties 对象赋值
    static {
        try {
            //实例化对象
            props = new Properties();
            //获取 properties 文件的流对象
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(in);
            //实例化容器
            beans = new HashMap<String, Object>();
            //取出配置文件中所有 keys
            Enumeration keys = props.keys();
            //遍历枚举
            while(keys.hasMoreElements()) {
                //从配置文件中取出 key
                String key = keys.nextElement().toString();
                //获取配置文件的对应 value
                String beanPath = props.getProperty(key);
                //反射创建对象
                Object value = Class.forName(beanPath).getConstructor().newInstance();//初始化创建一次，是单例的
                //存入容器中
                beans.put(key, value);
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化 properties 失败");
        }
    }

    private static void createBean(String beanName) {
        try {
            String beanPath = props.getProperty(beanName);
            Object bean = Class.forName(beanPath).getConstructor().newInstance();
            beans.put(beanName, bean);
        } catch (Exception e) {
            throw new ExceptionInInitializerError("properties 配置错误");
        }

    }

    /**
     * 根据 bean 名称获取 bean 对象
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        //静态代码创建容器的时候，bean 之间可能有依赖，通过 creatBean 方法解耦合 bean 之间的实例化
        if (!beans.containsKey(beanName)) {
            createBean(beanName);
        }
        return beans.get(beanName);
    }
}

