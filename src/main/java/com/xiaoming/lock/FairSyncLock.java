package com.xiaoming.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liangyi
 * @Date 2020/3/16
 */
public class FairSyncLock {

    private static final Logger LOGGER = LoggerFactory.getLogger(FairSyncLock.class);

    private ReentrantLock lock = new ReentrantLock(true);

    public void calc() {
        LOGGER.info("fair lock 即将加锁");
        lock.lock();
        try {
            LOGGER.info("fair lock 已经加锁");
            TimeUnit.SECONDS.sleep(10);
        }catch (Exception e) {
            LOGGER.error("calc exception!", e);
        }finally {
            LOGGER.info("fair lock 即将解锁");
            lock.unlock();
            LOGGER.info("fair lock 已经解锁");
        }
    }

}
