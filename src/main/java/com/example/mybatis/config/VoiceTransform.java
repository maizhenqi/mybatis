package com.example.mybatis.config;

import com.example.mybatis.entity.Voice;
import com.example.mybatis.mapper.VoiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class VoiceTransform implements ApplicationRunner {

    @Autowired
    private VoiceMapper voiceMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        new Thread(() -> {
            transform();
        }).start();
    }

    public void transform(){

        while (true){

            Voice voice = voiceMapper.queryOneToTransForm();
            if (voice != null){


                voice.setStatus("转化中");
                voiceMapper.updateVoiceById(voice);
                String[] fileNameArrays = voice.getVoice().split("\\.");

                try {
                    Process process = Runtime.getRuntime().exec("whisper "+ voice.getPath()
                            + voice.getVoice()
                            + " --output_format srt --model medium  --device cuda --output_dir D:/whisper-voice/srt");
                    while (true){
                        int a  = process.waitFor();
                        if ( a == 0 || !process.isAlive()){
                            break;
                        }
                        Thread.sleep(500l);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                voice.setStatus("转化完成");
                voice.setSrt(fileNameArrays[0] + ".srt");
                voiceMapper.updateVoiceById(voice);

            }

            try {
                Thread.sleep(2000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
