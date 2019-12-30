package com.xiaoming.tmp;

import com.google.common.collect.Lists;
import com.xiaoming.utils.JacksonUtils;
import org.junit.Test;

import java.util.List;

/**
 * @author liangyi
 * @Date 2019/12/20
 */
public class ListCastTest {

    @Test
    public void test1() {
        List<Integer> list = Lists.newArrayList();
        list.add(1);
        List<String> list1 = (List) list;

        List list2 = (List)list1;

        System.out.println(JacksonUtils.encode2String(list2));
    }
}
