package com.xiaoming.https.executor;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfigBean;
import com.ning.http.client.ListenableFuture;
import com.ning.http.client.Request;
import com.ning.http.client.Response;
import com.xiaoming.enums.HttpRequestMethodEnum;
import com.xiaoming.https.builder.HttpRequestBuilder;
import com.xiaoming.https.constant.EncodeConstant;
import com.xiaoming.https.manager.HttpFutureManager;
import com.xiaoming.utils.GsonUtils;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * http执行器，管理client线程池和数据传输
 * @author liangyi
 * @Date 2019/10/28
 */
public final class HttpExecutor {

    private final Logger LOGGER = LoggerFactory.getLogger(HttpExecutor.class);

    private final AsyncHttpClient client;

    private HttpExecutor(final AsyncHttpClient client) {
        this.client = client;
    }

    public static HttpExecutor getInstance(
            @NonNull final AsyncHttpClientConfigBean asyncHttpClientConfigBean) {
        return new HttpExecutor(new AsyncHttpClient(asyncHttpClientConfigBean));
    }

    /**
     * 同步执行http请求
     * @param url
     * @param paramsMap
     * @param formDataParamsMap
     * @param headerMap
     * @param jsonParam
     * @param method
     * @param timeout
     * @param unit
     * @return
     */
    public String syncExecute(final String url,
            final Map<String, Object> paramsMap,
            final Map<String, Object> formDataParamsMap,
            final Map<String, Object> headerMap,
            final String jsonParam,
            final HttpRequestMethodEnum method,
            final long timeout,
            final TimeUnit unit) {
        final ListenableFuture<Response> futureTask = getFuture(url, paramsMap, formDataParamsMap,
                headerMap, jsonParam, method);

        String result = null;
        try {
            result = getResponse(futureTask, timeout, unit);
            return result;
        } catch (final InterruptedException e) {
            LOGGER.error("HttpClient interrupted exception", e);
        } catch (final ExecutionException e) {
            LOGGER.error("HttpClient execution exception", e);
        } catch (final IOException e) {
            LOGGER.error("HttpClient io exception", e);
        } catch (final TimeoutException e) {
            LOGGER.error("HttpClient timeout exception", e);
        } catch (final Exception e) {
            LOGGER.error("HttpClient exception", e);
        } finally {
            LOGGER.info("【url request-response】：{}-{}-{}-{}", url,
                    GsonUtils.getGson().toJson(paramsMap),
                    GsonUtils.getGson().toJson(headerMap), result);
        }
        shutdownExceptionFuture(futureTask);
        return null;
    }

    /**
     * 获取异步future
     * @param url
     * @param paramsMap
     * @param formDataParamsMap
     * @param headerMap
     * @param jsonParam
     * @param method
     * @return
     */
    public HttpFutureManager asyncFuture(final String url,
            final Map<String, Object> paramsMap,
            final Map<String, Object> formDataParamsMap,
            final Map<String, Object> headerMap,
            final String jsonParam,
            final HttpRequestMethodEnum method) {
        return new HttpFutureManager(this, getFuture(url, paramsMap, formDataParamsMap,
                headerMap,
                jsonParam, method));
    }

    public String futureResponse(final ListenableFuture<Response> futureTask, final long timeout,
        final TimeUnit unit) {
            try {
                return getResponse(futureTask, timeout, unit);
            } catch (final InterruptedException e) {
                LOGGER.error("HttpClient interrupted exception", e);
            } catch (final ExecutionException e) {
                LOGGER.error("HttpClient execution exception", e);
            } catch (final IOException e) {
                LOGGER.error("HttpClient io exception", e);
            } catch (final TimeoutException e) {
                LOGGER.error("HttpClient timeout exception", e);
            } catch (final Exception e) {
                LOGGER.error("HttpClient exception", e);
            }
            shutdownExceptionFuture(futureTask);
            return null;
    }


    private ListenableFuture<Response> getFuture(final String url,
            final Map<String, Object> paramsMap,
            final Map<String, Object> formDataParamsMap,
            final Map<String, Object> headerMap,
            final String jsonParam,
            final HttpRequestMethodEnum method) {
        final HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder(url);
        final Request request = httpRequestBuilder.setMethod(method).setFormDataParamsMap(
                formDataParamsMap)
                .setHeaderMap(headerMap).setJsonParam(jsonParam).setParamsMap(paramsMap).build();
        return client.executeRequest(request);
    }

    private String getResponse(final ListenableFuture<Response> futureTask, final long timeout,
            final TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException, IOException {
        final Response response = futureTask.get(timeout, unit);
        if (response == null) {
            return null;
        }
        return response.getResponseBody(EncodeConstant.CHAR_DEFAULT_ENCODE);
    }

    /**
     * 对于没有获取到结果的任务，对任务进行取消操作
     *
     * @param futureTask
     */
    private void shutdownExceptionFuture(final ListenableFuture<Response> futureTask) {
        if (futureTask != null && !futureTask.isDone()) {
            futureTask.cancel(false);
        }
    }

}
