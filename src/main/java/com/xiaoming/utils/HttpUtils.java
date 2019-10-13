package com.xiaoming.utils;

import com.google.common.collect.Lists;
import com.ning.http.client.*;
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

    private static final AsyncHttpClient client = new AsyncHttpClient(new AsyncHttpClientConfigBean()
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
    public static String get(String url, long timeout, TimeUnit unit){
        return execute(url, null, null, null, null, timeout, unit);
    }

    /**
     * 根据url 参数集合paramMap get请求
     * @param url
     * @param paramsMap
     * @param timeout
     * @param unit
     * @return
     */
    public static String get(String url, Map<String, Object> paramsMap, long timeout, TimeUnit unit){
        return execute(url, paramsMap, null, null, null, timeout, unit);
    }

    /**
     * 根据url， 表达参数formDataParamsMap Post请求
     * @param url
     * @param formDataParamsMap
     * @param timeout
     * @param unit
     * @return
     */
    public static String post(String url, Map<String, Object> formDataParamsMap, long timeout, TimeUnit unit){
        return execute(url, null, formDataParamsMap, null, null, timeout, unit);
    }

    /**
     * 根据url， json串参数， post请求
     * @param url
     * @param jsonParam
     * @param timeout
     * @param unit
     * @return
     */
    public static String post(String url, String jsonParam, long timeout, TimeUnit unit){
        return execute(url, null, null, null, jsonParam, timeout, unit);
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
    public static String post(String url, Map<String, Object> formDataParamsMap, Map<String, Object> headerMap, long timeout, TimeUnit unit){
        return execute(url, null, formDataParamsMap, headerMap, null, timeout, unit);
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
    public static String post(String url, String jsonParam, Map<String, Object> headerMap, long timeout, TimeUnit unit){
        return execute(url, null, null, headerMap, jsonParam, timeout, unit);
    }

    /**
     * 终极post请求， url， url参数信息，表单参数信息，header头信息，json串参数信息 post请求
     * @param url
     * @param paramsMap
     * @param formDataParamsMap
     * @param headerMap
     * @param jsonParam  json请求应该还是有些问题
     * @param timeout
     * @param unit
     * @return
     */
    public static String post(String url, Map<String, Object> paramsMap, Map<String, Object> formDataParamsMap,
                              Map<String, Object> headerMap, String jsonParam, long timeout, TimeUnit unit){
        return execute(url, paramsMap, formDataParamsMap, headerMap, jsonParam, timeout, unit);
    }

    private static String execute(String url, Map<String, Object> paramsMap, Map<String, Object> formDataParamsMap,
                                  Map<String, Object> headerMap, String jsonParam, long timeout, TimeUnit unit) {
        ListenableFuture<Response> futureTask = null;
        String result = null;
        try {

            Request request = buildRequest(url, paramsMap, formDataParamsMap, headerMap, jsonParam);
            futureTask = client.executeRequest(request);
            Response response = futureTask.get(timeout, unit);
            if (response == null) {
                return null;
            }
            result = response.getResponseBody(CHAR_SET_UTF);
            return result;
        } catch (InterruptedException e) {
            LOGGER.error("HttpClient interrupted exception", e);
        } catch (ExecutionException e) {
            LOGGER.error("HttpClient execution exception", e);
        } catch (IOException e) {
            LOGGER.error("HttpClient io exception", e);
        } catch (TimeoutException e) {
            LOGGER.error("HttpClient timeout exception", e);
        } catch (Exception e) {
            LOGGER.error("HttpClient exception", e);
        }finally {
            LOGGER.info("【url request-response】：{}-{}-{}-{}", url, GsonUtil.toJson(paramsMap), GsonUtil.toJson(headerMap), result);
        }

        shutdownExceptionFuture(futureTask);
        return null;
    }

    /**
     * 构造request请求
     * @param url  请求地址
     * @param paramsMap get参数集合
     * @param formDataParamsMap post 表单参数集合
     * @param headerMap post header参数集合
     * @param jsonParam post json数据
     * @return
     */
    private static Request buildRequest(String url, Map<String, Object> paramsMap, Map<String, Object> formDataParamsMap, Map<String, Object> headerMap, String jsonParam) {

        RequestBuilder builder = new RequestBuilder();

        if(paramsMap!=null && paramsMap.size()>0) {
            url = createUrl(url, paramsMap);
        }
        builder.setUrl(url);

        if(StringUtils.isNotEmpty(jsonParam)){
            try {
                builder.setBody(jsonParam.getBytes(CHAR_SET_UTF));
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("encoding exception", e);
            }
        }

        if(formDataParamsMap!=null && formDataParamsMap.size()>0){
            List<Param> list = Lists.newArrayList();
            for(String key : formDataParamsMap.keySet()){
                if(StringUtils.isEmpty(key) || formDataParamsMap.get(key)==null){
                    continue;
                }
                list.add(new Param(key, formDataParamsMap.get(key).toString()));
            }
            builder.setFormParams(list);
        }


        if(headerMap!=null && headerMap.size()>0){
            for(String key : headerMap.keySet()){
                if(StringUtils.isEmpty(key) || headerMap.get(key)==null){
                    continue;
                }
                builder.setHeader(key, headerMap.get(key).toString());
            }
        }

        return builder.build();
    }

    /**
     * 自动拼接url和参数值
     *
     * @param url
     * @param paramsMap
     * @return
     */
    private static String createUrl(String url, Map<String, Object> paramsMap) {
        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url is empty");
        }

        if (paramsMap == null || paramsMap.size() <= 0) {
            return url;
        }

        StringBuilder sb = new StringBuilder(url);
        sb.append("?");

        try {
            for (String key : paramsMap.keySet()) {
                Object value = paramsMap.get(key);
                if (value != null) {
                    sb.append(key).append("=").append(URLEncoder.encode(paramsMap.get(key).toString(), CHAR_SET_UTF));
                }
                sb.append("&");
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("【url encode exception ：{}-{}】", url, GsonUtil.toJson(paramsMap), e);
        }

        String fullUrl = sb.toString();
        return fullUrl.substring(0, fullUrl.length() - 1);
    }

    /**
     * 对于没有获取到结果的任务，对任务进行取消操作
     *
     * @param futureTask
     */
    private static void shutdownExceptionFuture(ListenableFuture<Response> futureTask) {
        if (futureTask != null && !futureTask.isDone()) {
            futureTask.cancel(false);
        }
    }

    public static void main(String[] args) {
        String url = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=25_RejCww4FvgBgmNPc5-rF6z75sQleNP6idIOI1KTbLa-ckQu0phMWyQb8jqpfnW6kvzfo824T1mS6tI2Ui99Igatf8xfm7MenXBtB5p_j8lONkOhtp5NDy02A5KsRXIhCDAVBM";
        String json = "{\"tag\":{\"name\":\"测试11915\"}}";
        String response = post(url, json, 2000, TimeUnit.MILLISECONDS);

        System.out.println(response);
    }


}
