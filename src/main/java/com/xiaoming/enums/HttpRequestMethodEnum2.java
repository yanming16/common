package com.xiaoming.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liangyi
 * @Date 2019/10/26
 */
@AllArgsConstructor
@Getter
public enum HttpRequestMethodEnum2 {

    GET(1, "GET", "get请求"),
    POST(2, "POST", "post请求");

    /**
     * code，标识，没有含义
     */
    private final int code;


    /**
     * http的请求方式名称，RequestBuilder 使用name的方式进行识别request method
     */
    private final String name;

    /**
     * desc
     */
    private final String desc;

}
