package com.example.learnudp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 * @Author: liuchen
 * @Description: UDP客户端
 * @CreateTime: 2023/12/15 17:31
 */
public class Client {
    public static void main(String[] args) throws Exception {
        // 创建客户端对象,这里可以指定端口，不指定的话，就会随机一个端口
        DatagramSocket socket = new DatagramSocket();
        //DatagramSocket socket = new DatagramSocket(3001);

        // 创建扫描器获取用户输入
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入要发送的内容：");
            final String msg = scanner.nextLine();
            if ("exit".equals(msg)) {
                System.out.println("结束使用");
                socket.close();
                break;
            }
            final byte[] bytes = msg.getBytes();
            // 创建数据传输对象，往本机的3000端口发送数据
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 3000);
            // 发送数据
            socket.send(packet);
            System.out.println("udp发送完成");
        }
    }
}
