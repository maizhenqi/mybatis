package com.example.mybatis.mapper;

import com.example.mybatis.TestInnodb;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PigMapper {
    void  insertName(@Param("id") String id);

}
