package com.xiaoming.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author xiaoming
 * @date 2019-10-13
 */
public class GsonUtil {

    private static final Gson GSON = new Gson();

    private static final Gson GSON_DISABLE_HTML_ESCAPING = new GsonBuilder().disableHtmlEscaping().create();

    public static Gson getInstance(){
        return GSON;
    }

    public static Gson getGsonDisableHtmlEscaping() {
        return GSON_DISABLE_HTML_ESCAPING;
    }

}
