package com.example.mybatis;

import com.example.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HttpController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("zhuanfa")
    public String zhuanfa(){
        return "aaa.html";
    }

    @RequestMapping("redirect")
    public String redirect(){
        return "redirect:https://www.baidu.com";
    }

    public void test(){}
}
