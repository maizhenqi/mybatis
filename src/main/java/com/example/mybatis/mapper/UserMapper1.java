package com.example.mybatis.mapper;

import com.example.mybatis.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper1 {
    List<User> findAll();
}
