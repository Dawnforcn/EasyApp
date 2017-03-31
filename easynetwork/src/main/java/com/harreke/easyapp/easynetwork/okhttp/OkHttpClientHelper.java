package com.harreke.easyapp.easynetwork.okhttp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.harreke.easyapp.util.JsonUtil;
import com.harreke.easyapp.util.ListUtil;
import com.harreke.easyapp.util.PreferenceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 由huoqisheng于2016/7/5创建
 */
public class OkHttpClientHelper {
    private volatile static OkHttpClientHelper mInstance = null;
    private OkHttpClient mOkHttpClient;

    private OkHttpClientHelper() {
        //        SSLContext sc = null;
        //        try {
        //            sc = SSLContext.getInstance("SSL");
        //            sc.init(null, new TrustManager[]{new X509TrustManager() {
        //                @Override
        //                public void checkClientTrusted(X509Certificate[] chain, String authType) {
        //                }
        //
        //                @Override
        //                public void checkServerTrusted(X509Certificate[] chain, String authType) {
        //                }
        //
        //                @Override
        //                public X509Certificate[] getAcceptedIssuers() {
        //                    return null;
        //                }
        //            }}, new SecureRandom());
        //        } catch (NoSuchAlgorithmException | KeyManagementException ignored) {
        //            Logger.e("cannot init ssl");
        //        }
        OkHttpClient.Builder builder = new OkHttpClient.Builder().cookieJar(new CookieJar() {
            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<String> cookieStrList = JsonUtil.toList(PreferenceUtil.readString("cookie", url.host(), null), String.class);

                if (cookieStrList == null) {
                    return Collections.emptyList();
                } else {
                    List<Cookie> cookieList = new ArrayList<Cookie>(cookieStrList.size());
                    for (String cookieStr : cookieStrList) {
                        Cookie cookie = Cookie.parse(url, cookieStr);
                        if (cookie != null) {
                            cookieList.add(cookie);
                        }
                    }

                    return cookieList;
                }
            }

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookieList) {
                if (!ListUtil.isEmpty(cookieList)) {
                    List<String> cookieStrList = new ArrayList<String>(cookieList.size());
                    for (Cookie cookie : cookieList) {
                        cookieStrList.add(cookie.toString());
                    }
                    PreferenceUtil.writeString("cookie", url.host(), JsonUtil.toString(cookieStrList));
                }
            }
        }).connectTimeout(15, TimeUnit.SECONDS).readTimeout(15, TimeUnit.SECONDS).writeTimeout(15, TimeUnit.SECONDS);
        //        if (sc != null) {
        //            builder.socketFactory(sc.getSocketFactory());
        //            builder.hostnameVerifier(new HostnameVerifier() {
        //                @Override
        //                public boolean verify(String hostname, SSLSession session) {
        //                    return true;
        //                }
        //            });
        //        }
        mOkHttpClient = builder.build();
    }

    static OkHttpClientHelper getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpClientHelper.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpClientHelper();
                }
            }
        }

        return mInstance;
    }

    Call call(@NonNull String requestUrl, @Nullable Headers headers, @Nullable RequestBody requestBody) {
        Request.Builder builder = new Request.Builder().url(requestUrl);

        if (headers != null) {
            builder.headers(headers);
        }
        if (requestBody != null) {
            builder.post(requestBody);
        }

        return mOkHttpClient.newCall(builder.build());
    }
}