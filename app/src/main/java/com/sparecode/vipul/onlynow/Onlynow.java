package com.sparecode.vipul.onlynow;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

import com.sparecode.vipul.onlynow.util.Prefs;
import com.sparecode.vipul.onlynow.webservice.RequestBuilder;

/**
 * Created by vipul on 25/2/17.
 */

public class Onlynow extends Application{

    private static RequestBuilder requestBuilder;

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        requestBuilder = new RequestBuilder();

        Onlynow.context = getApplicationContext();

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

    }


    public static Context getContext() {
        return context;
    }

    public static RequestBuilder getRequestBuilder() {
        return requestBuilder;
    }
}
