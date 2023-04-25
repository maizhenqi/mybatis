package com.example.mybatis;

import com.alibaba.fastjson.JSON;
import org.apache.tomcat.util.net.NioChannel;
import org.apache.tomcat.util.net.NioEndpoint;
import org.apache.tomcat.util.net.NioSelectorPool;
import org.apache.tomcat.util.net.SocketWrapperBase;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.Socket;
import java.nio.*;

@Component
public class Test implements TestInterface {


    @Override
    public void hello() {
        System.out.println("hello");
    }

    public static byte[] bytebuffer2ByteArray(ByteBuffer buffer) {
        //获取buffer中有效大小
        int len = buffer.limit() - buffer.position();

        byte[] bytes = new byte[len];

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = buffer.get();

        }

        return bytes;

    }

    public static ByteBuffer test(NioEndpoint.NioSocketWrapper socketWrapperBase, NioSelectorPool pool) throws IOException {
        Thread thread  = new Thread(()->{
            NioChannel socket = socketWrapperBase.getSocket();
            ByteBuffer byteBuffer = ByteBuffer.allocate(39000000);
            try {
                pool.read(byteBuffer, socket, pool.get(), 1000l );
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(JSON.toJSONString(byteBuffer));
        });
        thread.start();
        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}