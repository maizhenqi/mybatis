package com.example.mybatis.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.mybatis.config.DB;
import com.example.mybatis.mapper.PigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PigServiceImpl implements PigService {

    @Autowired
    PigMapper pigMapper;

    @Override
    @DS("pig")
    public void save() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        pigMapper.insertName(" save()   "+ simpleDateFormat.format(new Date()));
    }

    @Override
    public void test() {
        this.save();
    }
}
