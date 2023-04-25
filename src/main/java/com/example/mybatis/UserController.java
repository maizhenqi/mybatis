package com.example.mybatis;

import com.alibaba.fastjson.JSON;
import com.example.mybatis.aop.AopTest;
import com.example.mybatis.eachother.Bean1;
import com.example.mybatis.mapper.TestMapper;
import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.mapper.UserMapper1;
import com.example.mybatis.service.PigService;
import com.example.mybatis.service.TestService;
import org.apache.ibatis.binding.MapperProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.handler.ConversionServiceExposingInterceptor;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@RequestMapping()
public class UserController {

    @Autowired
    private Bean1 bean1;



    @Autowired
    UserMapper userMapper;

    @Autowired
    TestService testService;


    @Autowired
    PigService pigService;

    @Autowired
    TestMapper testMapper;

    @Autowired
    UserMapper1 userMapper1;

    private AopTest aopTest;

    @Autowired
    private void setAopTest(AopTest aopTest){
        this.aopTest = aopTest;
    }

    ExecutorService fetchService = new ThreadPoolExecutor(10, 10, 0, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(2024), new NamedThreadFactory("sentinel-dashboard-metrics-fetchService"),
            new ThreadPoolExecutor.DiscardPolicy());


    @RequestMapping("testExcutor")
    @ResponseBody
    public Object testExcutor(){

        fetchService.execute(() -> {
            System.out.println("a1");
        });
        return "1";
    }

    @RequestMapping("user")
    @ResponseBody
    public Object findAll(){

        this.aopTest.save();
        //两个mapper二级缓存不生效
        userMapper.findAll();
        return userMapper1.findAll();
    }

    @RequestMapping("user1")
    @ResponseBody
    @Transactional(readOnly = true)

    public Object findAll1(){
        //单个mapper二级缓存才生效
        userMapper.findAll();
        return userMapper.findAll();
    }

    @RequestMapping("userid1")
    @ResponseBody
    public Object userid1(String id){
        //单个mapper二级缓存才生效
        userMapper.findidAll(id);
        return userMapper.findidAll(id);
    }

    @RequestMapping("user2")
    @ResponseBody
    @Transactional
    public Object findAll2(){
        //开启事务一级缓存才生效
        userMapper.findAll();
        //执行过增删改语句后会清空缓存
        userMapper.update();
        return userMapper.findAll();
    }

    @RequestMapping("rollback")
    @ResponseBody
    @Transactional
    public void rollback(){

        userMapper.update();
        throw new NullPointerException();
    }

    @RequestMapping("update")
    @ResponseBody
    @Transactional
    public Object update(){
        //开启失误一级缓存才生效
        userMapper.update();
        userMapper.findAll();
        userMapper.findAll();
        return "st";
    }


    @RequestMapping("testTransactional")
    @ResponseBody
    @Transactional(readOnly = true)
    public Object testTransactional(){
        System.out.println("===============================");
        System.out.println(JSON.toJSONString(userMapper.findAll()));
        //开启失误一级缓存才生效
        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userMapper.findAll();
    }

    @RequestMapping("testTransactional1")
    @ResponseBody
    @Transactional
    public Object testTransactional1(){
        //开启失误一级缓存才生效
        userMapper.insert();
        return userMapper.findAll();
    }

    @RequestMapping("test500")
    @ResponseBody
    public Object test500(){
        for (int temp = 500; temp > 0; temp--){
            List<TestInnodb> list = new ArrayList<>(5000000);

            for (int index= 10000; index > 0; index--){
                TestInnodb testInnodb = new TestInnodb();
                testInnodb.setId(UUID.randomUUID().toString());
                testInnodb.setName(UUID.randomUUID().toString());
                list.add(testInnodb);
            }
            testMapper.save(list);

        }

        System.out.println("111111111111111111111111");

        return "st";
    }

    @RequestMapping("testInIndex")
    @ResponseBody
    public Object testInIndex(){
        List<String> stringList = testMapper.queryName100();
        testMapper.queryByNames(stringList);
        return "success";
    }



    @RequestMapping("aoptest")
    @ResponseBody
    public Object aoptest(){
        aopTest.save();
        return "a";
    }


    @RequestMapping("tractiontest")
    @ResponseBody
//    @GlobalTransactional
    public Object tractiontest(){
        testService.save();
        return "a";
    }

    @RequestMapping("dsTest")
    @ResponseBody
//    @GlobalTransactional
    public Object dsTest(){
        pigService.save();
        return "a";
    }

    @RequestMapping("transactionalFail")
    @ResponseBody
    public Object transactionalFail(){
        testService.test();
        return "a";
    }

}
