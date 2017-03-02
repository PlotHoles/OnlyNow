package com.sparecode.vipul.onlynow.webservice;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;

import org.json.JSONObject;



/**
 * Created by user_1 on 10/13/2016.
 */

public class GetRequest<T>  {
    public void toGetRequest(final Context context, String url, JSONObject jsonObject, final Class<T> clazz, final OnResponse<T> t) {
        new GetAsync<T>(context, url, jsonObject,clazz, t).execute();
    }
}
