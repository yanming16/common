package com.xiaoming.common;

import lombok.Data;

/**
 * @author liangyi
 * @Date 2019/11/4
 */
@Data
public abstract class Person {
    private String name;
    private int age;

    public void init(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
