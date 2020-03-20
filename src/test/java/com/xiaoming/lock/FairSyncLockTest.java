package com.xiaoming.lock;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liangyi
 * @Date 2020/3/16
 */
public class FairSyncLockTest {

    private static FairSyncLock fairSyncLock = new FairSyncLock();

    @Test
    public void test() throws InterruptedException {
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                fairSyncLock.calc();
            }
        });

        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                fairSyncLock.calc();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

}