package com.example.mybatis.aop;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;

@Aspect
@Component
public class AspectTest {


    @Pointcut("execution(public * com.example.mybatis.aop.AopTest.save())")
    public void start(){

    }


    @After(value = "start()")
    private void doAfter(){
        System.out.println("after");
    }

    @Before(value = "start()")
    private void doBefore(){
        System.out.println("Before");
    }

}
