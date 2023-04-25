package com.example.mybatis.eachother;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bean2 {

    @Autowired
    private Bean1 bean1;

}
