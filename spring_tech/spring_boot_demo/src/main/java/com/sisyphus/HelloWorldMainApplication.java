package com.sisyphus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication 用来标注 spring boot 的主程序
 */
@SpringBootApplication
public class HelloWorldMainApplication {

    public static void main(String[] args) {
        //启动 spring boot 应用
        SpringApplication.run(HelloWorldMainApplication.class, args);
    }
}
