package com.xiaoming.utils;

import com.google.gson.Gson;

/**
 * @author xiaoming
 * @date 2019-10-13
 */
public class GsonUtil {

    private static final Gson GSON = new Gson();

    static String toJson(Object object){
        return GSON.toJson(object);
    }

}
