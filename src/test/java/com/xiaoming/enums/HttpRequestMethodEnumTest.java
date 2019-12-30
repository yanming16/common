package com.xiaoming.enums;

import com.google.common.collect.Maps;
import com.xiaoming.utils.JacksonUtils;
import org.junit.Test;

import java.util.EnumMap;

import static org.junit.Assert.*;

/**
 * @author liangyi
 * @Date 2019/12/5
 */
public class HttpRequestMethodEnumTest {

    @Test
    public void test1() {
        final HttpRequestMethodEnum post = HttpRequestMethodEnum.POST;
        final String s = JacksonUtils.encode2String(post);
        System.out.println(s);
        HttpRequestMethodEnum2 post2 = JacksonUtils.decodeFromString(s, HttpRequestMethodEnum2.class);
        System.out.println(post2);
    }

    @Test
    public void test2() {
        final HttpRequestMethodEnum head = HttpRequestMethodEnum.HEAD;
        final String s = JacksonUtils.encode2String(head);
        System.out.println(s);
        HttpRequestMethodEnum2 head2 = JacksonUtils.decodeFromString(s, HttpRequestMethodEnum2.class);
        System.out.println(head2);
    }

    @Test
    public void test3() {
        EnumMap<HttpRequestMethodEnum, String> enumMap = Maps.newEnumMap(HttpRequestMethodEnum.class);
        enumMap.put(HttpRequestMethodEnum.GET, HttpRequestMethodEnum.GET.getName());
        enumMap.put(HttpRequestMethodEnum.POST, HttpRequestMethodEnum.POST.getName());
        System.out.println(JacksonUtils.encode2String(enumMap));

    }

}