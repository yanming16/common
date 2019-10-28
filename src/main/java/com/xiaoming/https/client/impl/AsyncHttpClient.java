package com.xiaoming.https.client.impl;

import com.xiaoming.enums.HttpRequestMethodEnum;
import com.xiaoming.https.client.HttpClient;
import com.xiaoming.https.executor.HttpExecutor;
import com.xiaoming.https.manager.HttpFutureManager;

import java.util.Map;

/**
 * @author liangyi
 * @Date 2019/10/28
 */
public class AsyncHttpClient implements HttpClient {

    private HttpExecutor executor;

    public AsyncHttpClient buildClient(final HttpExecutor executor) {
        this.executor = executor;
        return this;
    }

    public HttpFutureManager sendRequest(final String url,
            final HttpRequestMethodEnum method,
            final Map<String, Object> paramsMap,
            final Map<String, Object> formDataParamsMap,
            final Map<String, Object> headerMap, final String jsonParam) {
        return executor.asyncFuture(url, paramsMap, formDataParamsMap, headerMap,
                jsonParam, method);
    }
}
