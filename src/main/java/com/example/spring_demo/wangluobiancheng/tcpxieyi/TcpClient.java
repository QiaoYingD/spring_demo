package com.example.spring_demo.wangluobiancheng.tcpxieyi;


import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * tcp客户端发送消息
 * <p>
 * 功能描述（tcp协议，面向连接，需要三次握手连接，所以是可靠协议，效率稍低）
 */
public class TcpClient {

    public static void main(String[] args) throws IOException {
        System.out.println("tcp 客户端启动。。。。");
        Socket socket = new Socket("192.168.0.108", 8080);
        OutputStream outputStream = socket.getOutputStream();
        String content = "我是客户端";
        byte[] buf = content.getBytes();
        outputStream.write(buf);
        socket.close();
    }

}
