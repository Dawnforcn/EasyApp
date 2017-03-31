package com.harreke.easyapp.network;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 由 Harreke（harreke@live.cn） 创建于 2014/07/24

 Http请求构造器，支持GET和POST请求
 */
public class RequestBuilder {
    public final static String CHARSET_UTF8 = "UTF-8";
    private final static String TAG = "RequestBuilder";
    private Map<String, String> mBodyMap = new TreeMap<>();
    private Map<String, String> mHeaderMap = new TreeMap<>();
    private String mHost;
    private Map<String, String> mMultiPartMap = new TreeMap<>();
    private String mPath;
    private Map<String, String> mQueryMap = new TreeMap<>();
    private String mTag;

    public RequestBuilder(@NonNull String tag) {
        mTag = tag;
    }

    /**
     添加Body

     @param key Body名称
     @param value Body内容

     @return 自身
     */
    public final RequestBuilder addBody(@NonNull String key, @NonNull Object value) {
        mBodyMap.put(key, String.valueOf(value));

        return this;
    }

    /**
     添加指定编码的Body

     @param key Body名称
     @param value Body内容
     @param charsetName 编码名称

     {@link #CHARSET_UTF8}

     @return 自身
     */
    public final RequestBuilder addBody(@NonNull String key, @NonNull Object value, @NonNull String charsetName) {
        String body = String.valueOf(value);

        try {
            mBodyMap.put(key, URLEncoder.encode(body, charsetName));
        } catch (UnsupportedEncodingException e) {
            mBodyMap.put(key, body);
        }

        return this;
    }

    /**
     添加Header

     @param key Header名称
     @param value Header内容

     @return 自身
     */
    public final RequestBuilder addHeader(@NonNull String key, @NonNull Object value) {
        mHeaderMap.put(key, String.valueOf(value));

        return this;
    }

    /**
     添加Header

     @param key Header名称
     @param value Header内容
     @param charsetName 編碼名稱

     {@link #CHARSET_UTF8}

     @return 自身
     */
    public final RequestBuilder addHeader(@NonNull String key, @NonNull Object value, @NonNull String charsetName) {
        String header = String.valueOf(value);

        try {
            mHeaderMap.put(key, URLEncoder.encode(header, charsetName));
        } catch (UnsupportedEncodingException e) {
            mHeaderMap.put(key, header);
        }

        return this;
    }

    public final RequestBuilder addMultiPart(@NonNull String key, @NonNull Object value) {
        mMultiPartMap.put(key, String.valueOf(value));

        return this;
    }

    public final RequestBuilder addMultiPart(@NonNull String key, @NonNull Object value, @NonNull String charsetName) {
        String multiPart = String.valueOf(value);

        try {
            mMultiPartMap.put(key, URLEncoder.encode(multiPart, charsetName));
        } catch (UnsupportedEncodingException e) {
            mMultiPartMap.put(key, multiPart);
        }

        return this;
    }

    /**
     添加Query

     @param key Query名称
     @param value Query内容
     @param charsetName 编码名称

     {@link #CHARSET_UTF8}

     @return 自身
     */
    public final RequestBuilder addQuery(@NonNull String key, @NonNull Object value, @NonNull String charsetName) {
        String query = String.valueOf(value);

        try {
            mQueryMap.put(key, URLEncoder.encode(query, charsetName));
        } catch (UnsupportedEncodingException e) {
            mQueryMap.put(key, query);
        }

        return this;
    }

    /**
     添加Query

     @param key Query名称
     @param value Query内容

     @return 自身
     */
    public final RequestBuilder addQuery(@NonNull String key, @NonNull Object value) {
        mQueryMap.put(key, String.valueOf(value));

        return this;
    }

    private String buildString(@NonNull Map<String, String> map) {
        Iterator<String> iterator = map.keySet().iterator();
        StringBuilder result = new StringBuilder();
        String key;

        if (iterator.hasNext()) {
            key = iterator.next();
            result.append(key).append("=").append(map.get(key));
            while (iterator.hasNext()) {
                key = iterator.next();
                result.append("&").append(key).append("=").append(map.get(key));
            }
        }

        return result.toString();
    }

    public void clear() {
        clearHeader();
        clearQuery();
        clearBody();
    }

    public void clearBody() {
        mBodyMap.clear();
    }

    public void clearHeader() {
        mHeaderMap.clear();
    }

    public void clearQuery() {
        mQueryMap.clear();
    }

    public String getBaseUrl() {
        return mHost + (TextUtils.isEmpty(mPath) ? "" : mPath);
    }

    public final Map<String, String> getBody() {
        return mBodyMap;
    }

    public final int getBodyCount() {
        return mBodyMap.size();
    }

    public final String getBodyString() {
        return buildString(mBodyMap);
    }

    public final Map<String, String> getHeader() {
        return mHeaderMap;
    }

    public final int getHeaderCount() {
        return mHeaderMap.size();
    }

    public final String getHeaderString() {
        return buildString(mHeaderMap);
    }

    public String getHost() {
        return mHost;
    }

    public final Map<String, String> getMultiPart() {
        return mMultiPartMap;
    }

    public final String getMultiPartString() {
        return buildString(mMultiPartMap);
    }

    public String getPath() {
        return mPath;
    }

    public final Map<String, String> getQuery() {
        return mQueryMap;
    }

    public final int getQueryCount() {
        return mQueryMap.size();
    }

    /**
     获得请求的GET传参

     @return 请求GET传参
     */
    public final String getQueryString() {
        return buildString(mQueryMap);
    }

    /**
     获得请求的标签

     @return 请求的标签
     */
    public String getTag() {
        return mTag;
    }

    /**
     获得请求的完整Http的链接，包括GET传参，但不包括POST主体和HEADER

     @return 请求完整链接
     */
    public final String getUrl() {
        String queryString = getQueryString();

        return getBaseUrl() + (TextUtils.isEmpty(queryString) ? "" : "?" + queryString);
    }

    public boolean hasBody() {
        return mBodyMap.size() > 0;
    }

    public boolean hasHeader() {
        return mHeaderMap.size() > 0;
    }

    /**
     打印请求详细参数
     */
    public final void print() {
        StringBuilder print = new StringBuilder();

        print.append(TAG + "/").append(mTag).append("\n");
        if (hasBody()) {
            print.append("POST ")
                    .append(getUrl())
                    .append("\nHeaders:\n")
                    .append(getHeaderString())
                    .append("\nBodies:\n")
                    .append(getBodyString())
                    .append("\nMultiParts:\n")
                    .append(getMultiPartString());
        } else {
            print.append("GET ").append(getUrl()).append("\nHeaders:\n").append(getHeaderString());
        }
        Logger.e(print.toString());
    }

    public void setHost(String host) {
        mHost = host;
    }

    public void setPath(String path) {
        mPath = path;
    }

    public final RequestBuilder setUrl(String url) {
        mHost = url;
        mPath = "";

        return this;
    }

    /**
     设置请求的基准Url

     @param host 请求的主机
     @param path 请求的路径

     @return 自身
     */
    public final RequestBuilder setUrl(String host, String path) {
        mHost = host;
        mPath = path;

        return this;
    }
}