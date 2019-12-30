package com.xiaoming.tmp;

import com.google.common.collect.Lists;
import com.xiaoming.utils.JacksonUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liangyi
 * @Date 2019/12/19
 */
public class StreamMapTest {

    @Test
    public void test1() {
        List<String> basicAttr = Lists.newArrayList();
        basicAttr.add("bbb");
        basicAttr.add("ccc");
        basicAttr = basicAttr.stream().map(TableNameEscape::translateAllKey).collect(Collectors.toList());
        System.out.println(JacksonUtils.encode2String(basicAttr));
    }

    public static class TableNameEscape {
        public static String translateAllKey(final String str) {
            return "aaa";
        }
    }
}
