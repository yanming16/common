package com.xiaoming.utils;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class HttpUtilsTest {

    private static Logger LOGGER = LoggerFactory.getLogger(HttpUtilsTest.class);

    private static final String URL = "http://localhost:8003/test/get";

    private static final RateLimiter rateLimiter = RateLimiter.create(10);


    @Test
    public void getTest() {

        String s = HttpUtils.get(URL, 10, TimeUnit.SECONDS);
        System.out.println(s);

    }

    @Test
    public void getTest2() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 10000,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        AtomicInteger atomicInteger = new AtomicInteger(0);

        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.submit(() -> {
                final int num = atomicInteger.incrementAndGet();
                LOGGER.info("It is num {} request start", num);
                rateLimiter.acquire();
                final HashMap<String, Object> params = Maps.newHashMap();
                params.put("num", num);
                //String s = HttpUtils.get(URL, params, 10, TimeUnit.SECONDS);
                final String s = HttpUtils.post(URL, params, 10, TimeUnit.SECONDS);
                LOGGER.info("It is num {} request end, response : {}", num, s);
            });
        }
        Thread.currentThread().join();
    }

}