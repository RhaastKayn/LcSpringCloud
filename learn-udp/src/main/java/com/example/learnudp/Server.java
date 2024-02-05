package com.example.learnudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @Author: liuchen
 * @Description: udp服务端
 * @CreateTime: 2023/12/15 18:05
 */
public class Server {
    public static void main(String[] args) throws IOException {
        // 创建服务端对象
        DatagramSocket socket = new DatagramSocket(3000);

        // 接收数据包的大小设置为64kb的，因为udp和tcp通讯协议中，数据报文的理论最大长度就是64kb，
        // 但实际上，由于网络设备、路由器和中间节点的限制，UDP数据包的有效负载通常会有所减少。在实际应用中，常见的UDP数据包大小一般在几百字节到几千字节之间。
        final byte[] bytes = new byte[1024 * 64];
        // 创建数据接收对象
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length);

        // 一直接收
        while (true) {
            // 接收数据
            socket.receive(packet);

            // 接收到多少数据就拿多少数据输出
            final int length = packet.getLength();
            final String res = new String(bytes, 0, length);
            System.out.println("收到ip:" + packet.getAddress().getHostAddress() + "端口：" + packet.getPort() + res);
        }
    }
}
