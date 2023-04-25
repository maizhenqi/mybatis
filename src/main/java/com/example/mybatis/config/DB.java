package com.example.mybatis.config;

import java.lang.annotation.*;

/**
 * 数据库
 * 
 * @Author scott
 * @email jeecgos@163.com
 * @Date 2019年1月14日
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited  //可以继承
public @interface DB {
	String value();

}
