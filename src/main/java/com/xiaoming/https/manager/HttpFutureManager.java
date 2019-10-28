package com.xiaoming.https.manager;

import com.ning.http.client.ListenableFuture;
import com.ning.http.client.Response;
import com.xiaoming.https.executor.HttpExecutor;

import java.util.concurrent.TimeUnit;

/**
 * @author liangyi
 * @Date 2019/10/28
 */
public class HttpFutureManager {

    private final HttpExecutor executor;

    private final ListenableFuture<Response> future;

    private boolean isTriggered = false;

    private String response;

    public HttpFutureManager(final HttpExecutor executor, final ListenableFuture<Response> future) {
        this.future = future;
        this.executor = executor;
    }

    public String get(final long timeout, final TimeUnit unit) {
        if (isTriggered) {
            return response;
        }
        isTriggered = true;
        this.response = executor.futureResponse(future, timeout, unit);
        return response;
    }
}
