package com.sisyphus.bean;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "person")
@Component
@Data
public class Person {

    private String name;
    private Integer age;
    private Boolean married;
    private Date birth;

    private Map<String, Object> resume;
    private List<String> hobbies;
}
