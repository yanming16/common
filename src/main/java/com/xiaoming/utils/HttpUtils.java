package com.xiaoming.utils;

import com.google.common.collect.Lists;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfigBean;
import com.ning.http.client.ListenableFuture;
import com.ning.http.client.Param;
import com.ning.http.client.Request;
import com.ning.http.client.RequestBuilder;
import com.ning.http.client.Response;
import com.xiaoming.enums.HttpRequestMethodEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author xiaoming
 * @date 2019-10-13
 */
public class HttpUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);
    private static final String CHAR_SET_UTF = "UTF-8";

    private static final AsyncHttpClient client = new AsyncHttpClient(
            new AsyncHttpClientConfigBean()
                    .setConnectionTimeOut(2000)
                    .setMaxConnectionPerHost(50)
                    .setRequestTimeout(2000)
                    .setMaxTotalConnections(200));

    /**
     * 根据url get请求
     * @param url
     * @param timeout
     * @param unit
     * @return
     */
    public static String get(final String url, final long timeout, final TimeUnit unit) {
        return baseGet(url, null, null, timeout, unit);
    }

    /**
     * 根据paramsMap封装get请求
     * @param url
     * @param paramsMap
     * @param timeout
     * @param unit
     * @return
     */
    public static String get(final String url, final Map<String, Object> paramsMap,
            final long timeout, final TimeUnit unit) {
        return baseGet(url, paramsMap, null, timeout, unit);
    }

    /**
     * 根据paramsMap 和 headersMap封装get请求，所有的get请求方法需要在这个方法上进行封装
     * @param url
     * @param paramsMap
     * @param headersMap
     * @param timeout
     * @param unit
     * @return
     */
    public static String baseGet(final String url, final Map<String, Object> paramsMap,
            final Map<String, Object> headersMap, final long timeout, final TimeUnit unit) {
        return execute(url, paramsMap, null, headersMap, null, timeout, unit,
                HttpRequestMethodEnum.GET);
    }

    /**
     * 根据url， 表达参数formDataParamsMap Post请求
     * @param url
     * @param formDataParamsMap
     * @param timeout
     * @param unit
     * @return
     */
    public static String post(final String url, final Map<String, Object> formDataParamsMap, final long timeout,
            final TimeUnit unit) {
        return basePost(url, null, formDataParamsMap, null, null, timeout, unit);
    }

    /**
     * 根据url， json串参数， post请求
     * @param url
     * @param jsonParam
     * @param timeout
     * @param unit
     * @return
     */
    public static String post(final String url, final String jsonParam, final long timeout, final TimeUnit unit) {
        return basePost(url, null, null, null, jsonParam, timeout, unit);
    }

    /**
     * 根据url， 表单参数formDataPramsMap， header信息 post请求
     * @param url
     * @param formDataParamsMap
     * @param headerMap
     * @param timeout
     * @param unit
     * @return
     */
    public static String post(final String url, final Map<String, Object> formDataParamsMap,
            final Map<String, Object> headerMap, final long timeout, final TimeUnit unit) {
        return basePost(url, null, formDataParamsMap, headerMap, null, timeout, unit);
    }

    /**
     * 根据url，json串参数， header头信息， post请求
     * @param url
     * @param jsonParam
     * @param headerMap
     * @param timeout
     * @param unit
     * @return
     */
    public static String post(final String url, final String jsonParam, final Map<String, Object> headerMap,
            final long timeout, final TimeUnit unit) {
        return basePost(url, null, null, headerMap, jsonParam, timeout, unit);
    }

    /**
     * 终极post请求， url， url参数信息，表单参数信息，header头信息，json串参数信息 post请求的基础方法，所有的post请求在这个请求上进行封装
     * @param url
     * @param paramsMap
     * @param formDataParamsMap
     * @param headerMap
     * @param jsonParam  json请求应该还是有些问题
     * @param timeout
     * @param unit
     * @return
     */
    public static String basePost(final String url, final Map<String, Object> paramsMap,
            final Map<String, Object> formDataParamsMap,
            final Map<String, Object> headerMap, final String jsonParam, final long timeout,
            final TimeUnit unit) {
        return execute(url, paramsMap, formDataParamsMap, headerMap, jsonParam, timeout, unit,
                HttpRequestMethodEnum.POST);
    }

    /**
     * 执行方法的核心函数
     * @param url
     * @param paramsMap
     * @param formDataParamsMap
     * @param headerMap
     * @param jsonParam
     * @param timeout
     * @param unit
     * @param method
     * @return
     */
    private static String execute(final String url, final Map<String, Object> paramsMap,
            final Map<String, Object> formDataParamsMap,
            final Map<String, Object> headerMap, final String jsonParam, final long timeout,
            final TimeUnit unit, final HttpRequestMethodEnum method) {
        ListenableFuture<Response> futureTask = null;
        String result = null;
        try {

            final Request request = buildRequest(url, paramsMap, formDataParamsMap, headerMap,
                    jsonParam, method);
            futureTask = client.executeRequest(request);
            final Response response = futureTask.get(timeout, unit);
            if (response == null) {
                return null;
            }
            result = response.getResponseBody(CHAR_SET_UTF);
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
     * 构造request请求
     * @param url  请求地址
     * @param paramsMap get参数集合
     * @param formDataParamsMap post 表单参数集合
     * @param headerMap header参数集合
     * @param jsonParam post json数据
     * @param method
     * @return
     */
    private static Request buildRequest(final String url, final Map<String, Object> paramsMap,
            final Map<String, Object> formDataParamsMap, final Map<String, Object> headerMap,
            final String jsonParam,
            final HttpRequestMethodEnum method) {
        final RequestBuilder builder = new RequestBuilder();
        buildUrl(url, paramsMap, builder);
        buildHeaders(headerMap, builder);
        buildMethod(method, builder);
        buildJsonParam(jsonParam, builder);
        buildFormParams(formDataParamsMap, builder);
        return builder.build();
    }

    /**
     * 构造form表单参数，content-type:form-data
     * @param formDataParamsMap
     * @param builder
     */
    private static void buildFormParams(final Map<String, Object> formDataParamsMap,
            final RequestBuilder builder) {
        if (formDataParamsMap != null && !formDataParamsMap.isEmpty()) {
            final List<Param> list = Lists.newArrayList();
            for (final String key : formDataParamsMap.keySet()) {
                if (StringUtils.isEmpty(key) || formDataParamsMap.get(key) == null) {
                    continue;
                }
                list.add(new Param(key, formDataParamsMap.get(key).toString()));
            }
            builder.setFormParams(list);
        }
    }

    /**
     * 构造json数据参数 content-type:application/json
     * @param jsonParam
     * @param builder
     */
    private static void buildJsonParam(final String jsonParam, final RequestBuilder builder) {
        if (StringUtils.isNotEmpty(jsonParam)) {
            try {
                builder.setBody(jsonParam.getBytes(CHAR_SET_UTF));
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("encoding exception", e);
            }
        }
    }

    /**
     * 构建method
     * @param method
     * @param builder
     */
    private static void buildMethod(final HttpRequestMethodEnum method,
            final RequestBuilder builder) {
        builder.setMethod(method.getName());
    }

    /**
     * 构建url
     * @param url
     * @param paramsMap
     * @param builder
     * @return
     */
    private static void buildUrl(String url, final Map<String, Object> paramsMap,
            final RequestBuilder builder) {
        if (paramsMap != null && !paramsMap.isEmpty()) {
            url = createUrl(url, paramsMap);
        }
        builder.setUrl(url);
    }

    /**
     * 构建header
     * @param headerMap
     * @param builder
     */
    private static void buildHeaders(final Map<String, Object> headerMap,
            final RequestBuilder builder) {
        if (headerMap != null && !headerMap.isEmpty()) {
            for (final String key : headerMap.keySet()) {
                if (StringUtils.isEmpty(key) || headerMap.get(key) == null) {
                    continue;
                }
                builder.setHeader(key, headerMap.get(key).toString());
            }
        }
    }

    /**
     * 自动拼接url和参数值
     *
     * @param url
     * @param paramsMap
     * @return
     */
    private static String createUrl(final String url, final Map<String, Object> paramsMap) {
        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url is empty");
        }

        if (paramsMap == null || paramsMap.size() <= 0) {
            return url;
        }

        final StringBuilder sb = new StringBuilder(url);
        sb.append("?");

        try {
            for (final String key : paramsMap.keySet()) {
                final Object value = paramsMap.get(key);
                if (value != null) {
                    sb.append(key).append("=").append(
                            URLEncoder.encode(paramsMap.get(key).toString(), CHAR_SET_UTF));
                }
                sb.append("&");
            }
        } catch (final UnsupportedEncodingException e) {
            LOGGER.error("【url encode exception ：{}-{}】", url,
                    GsonUtils.getGson().toJson(paramsMap), e);
        }

        final String fullUrl = sb.toString();
        return fullUrl.substring(0, fullUrl.length() - 1);
    }

    /**
     * 对于没有获取到结果的任务，对任务进行取消操作
     *
     * @param futureTask
     */
    private static void shutdownExceptionFuture(final ListenableFuture<Response> futureTask) {
        if (futureTask != null && !futureTask.isDone()) {
            futureTask.cancel(false);
        }
    }

    public static void main(String[] args) {
        String url =
                "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=25_RejCww4FvgBgmNPc5-rF6z75sQleNP6idIOI1KTbLa-ckQu0phMWyQb8jqpfnW6kvzfo824T1mS6tI2Ui99Igatf8xfm7MenXBtB5p_j8lONkOhtp5NDy02A5KsRXIhCDAVBM";
        String json = "{\"tag\":{\"name\":\"测试11915\"}}";
        String response = post(url, json, 2000, TimeUnit.MILLISECONDS);

        System.out.println(response);
    }


}
