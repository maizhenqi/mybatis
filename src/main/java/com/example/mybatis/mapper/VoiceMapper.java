package com.example.mybatis.mapper;

import com.example.mybatis.entity.Voice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VoiceMapper {

    void  insert(@Param("voice")Voice voice);

    List<Voice> queryAll();

    Voice queryOneToTransForm();

    void updateVoiceById(@Param("voice")Voice voice);

}
