package com.xiaoming.tmp;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

/**
 * @author liangyi
 * @Date 2019/12/18
 */
public class ReplaceTest {

    private static final Map<String, String> REPLACE_MAPPER = Maps.newHashMap();

    private static final Map<String, String> RESET_MAPPER = Maps.newHashMap();

    static {
        REPLACE_MAPPER.put("\\(", "__LP__");
        REPLACE_MAPPER.put("\\)", "__RP__");

        REPLACE_MAPPER.forEach((key, value) -> RESET_MAPPER.put(value, key));
    }

    @Test
    public void test1() {
        System.out.println(resetStr("冷媒管__LP__气__RP__"));
    }

    public static String resetStr(final String str) {
        if (Strings.isNullOrEmpty(str)) {
            return str;
        }
        String result = str;
        for (final Map.Entry<String, String> next : RESET_MAPPER.entrySet()) {
            result = result.replaceAll(next.getKey(), next.getValue());
        }
        return result;
    }
}
