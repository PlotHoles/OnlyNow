package com.sparecode.vipul.onlynow.webservice;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by user_1 on 10/13/2016.
 */

public class RequestBuilder {

    private OkHttpClient okHttpClient;

    public RequestBuilder() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
