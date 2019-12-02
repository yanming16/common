package com.xiaoming.utils;

import com.xiaoming.common.Person;
import org.junit.Test;

/**
 * @author liangyi
 * @Date 2019/11/4
 */
public class JacksonUtilsTest {

    @Test
    public void testDecode() {
        Person person = new Person();
        person.setName("liangyi");
        person.setAge(25);
        System.out.println(JacksonUtils.encode2String(person));
        System.out.println(JacksonUtils.decodeFromString("{\"name\":\"liangyi\",\"age\":25," +
                "\"sex\":\"male\"}", Person.class).toString());
    }
}
