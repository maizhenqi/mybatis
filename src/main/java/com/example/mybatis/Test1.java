package com.example.mybatis;

import org.springframework.stereotype.Component;

@Component
public class Test1 implements TestInterface {


    @Override
    public void hello() {
        System.out.println("hello1");
    }

}
