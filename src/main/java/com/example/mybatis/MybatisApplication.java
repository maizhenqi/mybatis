package com.example.mybatis;

import org.apache.ibatis.binding.MapperProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


@SpringBootApplication
//@EnableAutoDataSourceProxy
public class MybatisApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(MybatisApplication.class, args);
    }




}
