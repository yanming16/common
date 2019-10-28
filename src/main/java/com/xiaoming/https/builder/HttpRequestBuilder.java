package com.xiaoming.https.builder;

import com.google.common.collect.Lists;
import com.ning.http.client.Param;
import com.ning.http.client.Request;
import com.ning.http.client.RequestBuilder;
import com.xiaoming.enums.HttpRequestMethodEnum;
import com.xiaoming.utils.GsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author liangyi
 * @Date 2019/10/28
 */
public class HttpRequestBuilder {

    private static final String CHAR_SET_UTF = "UTF-8";
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestBuilder.class);

    private final RequestBuilder builder = new RequestBuilder();

    private final String url;

    public HttpRequestBuilder(final String url) {
        this.url = url;
        this.builder.setUrl(url);
    }

    public HttpRequestBuilder setMethod(final HttpRequestMethodEnum method){
        buildMethod(method, builder);
        return this;
    }

    public HttpRequestBuilder setParamsMap(final Map<String, Object> paramsMap) {
        buildUrl(url, paramsMap, builder);
        return this;
    }

    public HttpRequestBuilder setHeaderMap(final Map<String, Object> headerMap) {
        buildHeaders(headerMap, builder);
        return this;
    }

    public HttpRequestBuilder setFormDataParamsMap(final Map<String, Object> formDataParamsMap) {
        buildFormParams(formDataParamsMap, builder);
        return this;
    }

    public HttpRequestBuilder setJsonParam(final String jsonParam) {
        buildJsonParam(jsonParam, builder);
        return this;
    }

    public Request build() {
        return builder.build();
    }

    /**
     * 构造form表单参数，content-type:form-data
     * @param formDataParamsMap
     * @param builder
     */
    private void buildFormParams(final Map<String, Object> formDataParamsMap,
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
    private void buildJsonParam(final String jsonParam, final RequestBuilder builder) {
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
    private void buildMethod(final HttpRequestMethodEnum method,
            final com.ning.http.client.RequestBuilder builder) {
        builder.setMethod(method.getName());
    }

    /**
     * 构建url
     * @param url
     * @param paramsMap
     * @param builder
     * @return
     */
    private void buildUrl(String url, final Map<String, Object> paramsMap,
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
    private void buildHeaders(final Map<String, Object> headerMap,
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
    private String createUrl(final String url, final Map<String, Object> paramsMap) {
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
}
