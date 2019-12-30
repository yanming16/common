package com.xiaoming.tmp;

import lombok.Data;
import org.junit.Test;

/**
 * @author liangyi
 * @Date 2019/12/30
 */
@Data
public class SyncTest {

    private volatile int num = 0;

    private int addOne() {
        synchronized (this) {
            num++;
            return num;
        }
    }

    public static void main(String[] args) {
        SyncTest syncTest = new SyncTest();
        syncTest.addOne();
    }
}
