package com.example.learntcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @Author: liuchen
 * @Description: 多线程读取多个客户端的消息
 * @CreateTime: 2023/12/25 16:06
 */
public class ServerThread extends Thread {

    private Socket socket;

    /**
     * 获取到客户端的Socket
     */
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    /**
     * 重写run方法
     */
    @Override
    public void run() {
        try {
            // 获取数据输入流
            final DataInputStream dis = new DataInputStream(socket.getInputStream());
            // 一直读取数据
            while (true) {
                try {
                    // 读取数据
                    final String res = dis.readUTF();
                    System.out.println(res);
                } catch (Exception e) {
                    // 抛出异常说明客户端下线
                    System.out.println(socket.getInetAddress() + ":" + socket.getPort() + "下线");
                    dis.close();
                    socket.close();
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
