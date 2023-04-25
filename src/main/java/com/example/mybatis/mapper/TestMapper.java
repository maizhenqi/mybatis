package com.example.mybatis.mapper;

import com.example.mybatis.TestInnodb;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestMapper {
    void save(@Param("testInnodb") List<TestInnodb> testInnodb);

    List<String> queryName100();

    List<Map>queryByNames(@Param("names") List<String> names);

}
