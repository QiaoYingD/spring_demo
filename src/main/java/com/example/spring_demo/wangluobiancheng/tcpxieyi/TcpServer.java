package com.example.spring_demo.wangluobiancheng.tcpxieyi;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * tcp服务端发送消息
 * <p>
 * 功能描述（tcp协议，面向连接，需要三次握手连接，所以是可靠协议，效率稍低）
 */
public class TcpServer {

    public static void main(String[] args) throws IOException {
        System.out.println("tcp 服务端启动。。。。");
        ServerSocket serverSocket = new ServerSocket(8080);
        //等待客户端进行发送内容，如果客户端不发送内容，会一直等待，阻塞的效果
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[1024];
        int len = inputStream.read(buf);
        String content = new String(buf, 0, len);
        System.out.println("客户端发送的消息是：" + content);
        serverSocket.close();
    }

}
