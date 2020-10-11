package com.example.spring_demo.wangluobiancheng.udpxieyi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * udp服务端接受消息
 *
 * 功能描述（udp协议，面向无连接，将数据与源封装在包中，不需要建立连接，协议不可靠，但效率较快）
 */
public class UdpServer {


    public static void main(String[] args) throws IOException {
        System.out.println("udp 服务端启动....");
        int port = 8080;
        // 监听端口号接受消息
        DatagramSocket ds = new DatagramSocket(port);
        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, buf.length);
        //等待客户端进行发送内容，如果客户端不发送内容，会一直等待，阻塞的效果
        ds.receive(dp);
        String content = new String(dp.getData(), dp.getLength());
        System.out.println("客户端发送的数据是：" + content);
        ds.close();
    }

}
