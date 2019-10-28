package com.xiaoming.https.register;

import com.google.common.collect.Maps;
import com.xiaoming.https.client.HttpClient;
import com.xiaoming.https.client.impl.AsyncHttpClient;
import com.xiaoming.https.client.impl.SyncHttpClient;

import java.util.concurrent.ConcurrentMap;

/**
 * @author liangyi
 * @Date 2019/10/28
 */
public class HttpClientRegister {

    private static final ConcurrentMap<String, SyncHttpClient> syncHttpClientMap =
            Maps.newConcurrentMap();

    private static final ConcurrentMap<String, AsyncHttpClient> asyncHttpClientMap =
            Maps.newConcurrentMap();

    public void register(final String name, final HttpClient httpClient) {
        if (httpClient instanceof SyncHttpClient) {
            syncHttpClientMap.put(name, (SyncHttpClient)httpClient);
        }else {
            asyncHttpClientMap.put(name, (AsyncHttpClient)httpClient);
        }
    }

    public SyncHttpClient getSyncHttpClient(final String name) {
        return syncHttpClientMap.get(name);
    }

    public AsyncHttpClient getAsyncHttpClient(final String name) {
        return asyncHttpClientMap.get(name);
    }

}
