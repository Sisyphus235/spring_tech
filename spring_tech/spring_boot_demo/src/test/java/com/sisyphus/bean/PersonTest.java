package com.sisyphus.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonTest {

    @Autowired
    private Person person;

    @Test
    public void testPerson() {
        System.out.println(person);
    }
}
