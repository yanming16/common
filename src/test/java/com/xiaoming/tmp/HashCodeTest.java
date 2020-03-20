package com.xiaoming.tmp;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author liangyi
 * @Date 2020/3/12
 */
public class HashCodeTest {

    @Test
    public void test1() {
        final HashMap<String, String> hashMap = Maps.newHashMap();
        hashMap.put("aaa", "aaa");
        hashMap.put("bbb", "bbb");
        hashMap.put("ccc", "ccc");
        hashMap.keySet().forEach(key -> System.out.println(key.hashCode()));
        hashMap.entrySet().forEach(entry -> System.out.println(entry.hashCode()));
        System.out.println(hashMap.hashCode());
    }
}
