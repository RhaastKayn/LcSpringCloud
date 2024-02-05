package com.example.learntcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author: liuchen
 * @Description: tcp通信刻划断
 * @CreateTime: 2023/12/25 13:49
 */
public class Client {
    public static void main(String[] args) throws Exception {
        // 创建Socker连接对象，并且对服务端进行连接(参数是服务端的ip和端口)
        Socket socket = new Socket("localhost", 3002);

        // 创建字节输出流
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        // 创建扫描器获取用户输入
        Scanner scanner = new Scanner(System.in);

        // 让用户可以一直发消息给服务端
        while (true) {
            System.out.println("请输入：");
            String msg = scanner.nextLine();
            if ("exit".equals(msg)) {
                System.out.println("结束使用");
                dos.close();
                socket.close();
                break;
            }
            // 这个方法本身并不会将数据立即写入到目标输出流，而是将数据写入到缓冲区中
            dos.writeUTF(msg);

            // 清空缓冲区，并且将数据立即写入到目标输出流中
            dos.flush();
        }

        //// 关闭数据输出流,close()方法会隐式地调用flush()方法来确保所有数据都被写入到目标输出流
        //dos.close();
        //
        //// 关闭Socket连接管道
        //socket.close();
    }
}
