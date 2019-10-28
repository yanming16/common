package com.xiaoming.https.client.impl;

import com.xiaoming.enums.HttpRequestMethodEnum;
import com.xiaoming.https.client.HttpClient;
import com.xiaoming.https.executor.HttpExecutor;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author liangyi
 * @Date 2019/10/28
 */
public class SyncHttpClient implements HttpClient {

    private HttpExecutor executor;

    public SyncHttpClient buildClient(final HttpExecutor executor) {
        this.executor = executor;
        return this;
    }

    public String sendRequest(final String url, final HttpRequestMethodEnum method,
            final Map<String, Object> paramsMap,
            final Map<String, Object> formDataParamsMap,
            final Map<String, Object> headerMap, final String jsonParam,
            final long timeout, final TimeUnit unit) {
        return executor.syncExecute(url, paramsMap, formDataParamsMap, headerMap,
                jsonParam, method,
                timeout, unit);
    }
}
