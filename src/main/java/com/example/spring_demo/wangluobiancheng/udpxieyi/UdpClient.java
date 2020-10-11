package com.example.spring_demo.wangluobiancheng.udpxieyi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * udp客户端发送消息
 * <p>
 * 功能描述（udp协议，面向无连接，将数据与源封装在包中，不需要建立连接，协议不可靠，但效率较快）
 */
public class UdpClient {

    public static void main(String[] args) throws IOException {
        System.out.println("upd 客户端发送消息。。。");
        DatagramSocket ds = new DatagramSocket();
        String content = "客户端发送消息...";
        byte[] buf = content.getBytes();
        DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.0.108"), 8080);
        ds.send(dp);
        ds.close();
    }


}
