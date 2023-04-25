package com.example.mybatis.aop;

import com.example.mybatis.config.DB;
import com.example.mybatis.config.DynamicDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Order(-1)
@Aspect
@Component
public class DynamicDataSourceAspect {


    @Pointcut("@annotation(com.example.mybatis.config.DB)")
    public void ponincut(){}

    @SuppressWarnings("rawtypes")
    @Before("ponincut()")
    public void beforeAspect(JoinPoint joinPoint){

        //获取当前访问的类名
        Class<?> className = joinPoint.getTarget().getClass();
        System.out.println("当前类："+className);

        //获取当前访问方法名
        String methodName = joinPoint.getSignature().getName();
        System.out.println("当前方法名："+methodName);

        //获取当前访问的参数类型
        Class[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        for (Class parameterType : parameterTypes) {
            System.out.println("参数类型："+parameterType);
        }
        //获取当前访问的参数名字
        String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        for (String parameterName : parameterNames) {
            System.out.println("参数名字："+parameterName);
        }
        //获取当前的参数值
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("参数值："+arg);
        }
        //获取当前的数据源
        String defaultdb = DynamicDataSource.DEFAULTDB;
        String process = DynamicDataSource.PIG;

        try {
            //获取当前方法的对象
            Method method = className.getMethod(methodName, parameterTypes);
            System.out.println("当前的对象1："+method);
            DB classAnnotion = className.getAnnotation(DB.class);
            System.out.println(classAnnotion);
            //判断是否有DB该注解
            if (method.isAnnotationPresent(DB.class)){
                //获取当前方法的DB注解
                DB annotation = method.getAnnotation(DB.class);
                System.out.println("当前方法的注解:"+annotation);
                //获取当前注解的值
                defaultdb = annotation.value();
                System.out.println("当前注解的值:"+defaultdb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //切换数据源
        DynamicDataSource.setDataSorce(defaultdb);
    }

    /**
     * 清除数据源
     */
    @After("ponincut()")
    public  void afterAspect(){
        DynamicDataSource.clearDataSource();
    }
}
