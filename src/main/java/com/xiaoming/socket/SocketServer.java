package com.xiaoming.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liangyi
 * @Date 2020/3/12
 */
public class SocketServer {

    public static void runServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(1122);
        System.out.println("等待客户端连接");
        final Socket socket = serverSocket.accept();
        System.out.println("客户端连接成功");
        while (true) {
            byte[] content = new byte[512];
            System.out.println("等待数据输入");
            final InputStream inputStream = socket.getInputStream();
            final int read = inputStream.read(content);
            System.out.println("数据输入完成");
            System.out.println("传输数据为：" + new String(content, 0, read));
        }
    }

    public static void main(String[] args) throws IOException {
        runServer();
    }
}
