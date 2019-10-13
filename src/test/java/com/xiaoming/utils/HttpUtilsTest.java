package com.xiaoming.utils;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class HttpUtilsTest {

    private static final String URL = "http://www.baidu.com";

    @Test
    public void getTest(){

        String s = HttpUtils.get(URL, 1000, TimeUnit.MILLISECONDS);
        System.out.println(s);

    }

}