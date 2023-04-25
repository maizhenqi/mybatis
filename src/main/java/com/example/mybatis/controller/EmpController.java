package com.example.mybatis.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("emp")
public class EmpController {

    @GetMapping("lol")
    @ResponseBody
    public Object lol(){
        return "lol";
    }

}
