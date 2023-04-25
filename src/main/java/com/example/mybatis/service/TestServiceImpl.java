package com.example.mybatis.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.mybatis.config.DB;
import com.example.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public  class TestServiceImpl implements TestService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PigService pigService;

    @Autowired
    private TestService testService;

    @Override
    @Transactional
    public List<String> findAll() {
        return userMapper.findAll();
    }

    @Override
    @DS("master")
    public void save() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        userMapper.insertName( " save()   "+ simpleDateFormat.format(new Date()));
        pigService.save();
        String s = null;
        s.length();

    }

    @Override
    @Transactional
    public void saveUser() {
        userMapper.insert();
        int a = 5/ 0;

    }

    @Override
    public void test() {
        this.saveUser();
    }


}
