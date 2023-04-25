package com.example.mybatis.controller;

import com.example.mybatis.entity.Voice;
import com.example.mybatis.mapper.VoiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;


@Controller
public class WhisperController {

    private static final String VOICE_PATH = "D:/whisper-voice/";

    @Autowired
    private VoiceMapper voiceMapper;


    @RequestMapping("whisperPage")
    public String whisper(Model model){

        List<Voice> list = this.voiceMapper.queryAll();
        model.addAttribute("voiceList", list);
        return "whisper";
    }

    @PostMapping("uploadVoice")
    public String uploadVoice(@RequestParam("voice") MultipartFile multipartFile, Model m){

        String fileName = multipartFile.getOriginalFilename();
        File file = new File(VOICE_PATH + fileName);
        String[] fileNameArrays = fileName.split("\\.");
        int i = 1;
        while (file.exists()){
            fileName = fileNameArrays[0] + "(" + i++ + ")." + fileNameArrays[1];
            file = new File(VOICE_PATH + fileName);
        }

        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            bufferedOutputStream.write(multipartFile.getBytes());
            bufferedOutputStream.flush();
            Voice voice = new Voice();
            voice.setVoice(fileName);
            voice.setStatus("未转化");
            voice.setPath(VOICE_PATH);
            voiceMapper.insert(voice);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:whisperPage";
    }

}
