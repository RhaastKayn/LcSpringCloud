package com.example.learntcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: liuchen
 * @Description: tcp服务端
 * @CreateTime: 2023/12/25 13:59
 */
public class Server {
    public static void main(String[] args) throws Exception {
        // 创建serverSocket对象，同时为服务端注册端口
        ServerSocket serverSocket = new ServerSocket(3002);

        // 多线程处理客户端连接
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println(socket.getRemoteSocketAddress() + "上线");
            ServerThread serverThread = new ServerThread(socket);
            serverThread.start();
        }

    }
}
