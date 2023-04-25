package com.example.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<String> findAll();
    List<String> findAll(String a);
    List<String> findidAll(@Param("id") String id);
    void  update();
    void  insert();
    void  insertName(@Param("id") String id);
}
