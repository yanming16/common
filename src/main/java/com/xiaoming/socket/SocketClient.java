package com.xiaoming.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author liangyi
 * @Date 2020/3/12
 */
public class SocketClient {

    public static void runClient() throws IOException {
        System.out.println(InetAddress.getLocalHost());
        Socket socket = new Socket(InetAddress.getLocalHost(), 1122);
        final OutputStream outputStream = socket.getOutputStream();
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("输入传输内容：");
            final String content = s.nextLine();
            outputStream.write(content.getBytes());
            outputStream.flush();
        }
    }

    public static void main(String[] args) throws IOException {
        runClient();
    }
}
