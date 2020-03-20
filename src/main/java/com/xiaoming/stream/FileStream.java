package com.xiaoming.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author liangyi
 * @Date 2020/3/12
 */
public class FileStream {

    public static void main(String[] args) throws IOException {
        final FileInputStream fileInputStream = new FileInputStream("F:\\liangyiOwnPlace\\common\\src\\main\\java\\com\\xiaoming\\stream\\test.txt");
        byte[] bytes = new byte[512];
        fileInputStream.read(bytes);
        System.out.println(new String(bytes));
    }
}
