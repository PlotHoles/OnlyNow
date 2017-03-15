package com.sparecode.vipul.onlynow.webservice;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.sparecode.vipul.onlynow.Onlynow;
import com.sparecode.vipul.onlynow.interfaces.OnResponse;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by user_1 on 10/17/2016.
 */

public class SimpleAsync<T> extends AsyncTask<String, Void, String> {

    private Context context;
    private String url;
    private Class<T> clazz;
    private OnResponse<T> callback;



    public SimpleAsync(Context context, String url, Class<T> clazz, OnResponse<T> callback) {
        this.context = context;
        this.url = url;
        this.clazz = clazz;
        this.callback = callback;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String... params) {

        Request request = new Request.Builder()
                .url(url)
                .build();
        Log.e(this.getClass().getName(), ":::: GET REQUEST URL IS :::: "
                + url);

        try (Response response = Onlynow.getRequestBuilder().getOkHttpClient().newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseString = response.body().string();
                Log.e(this.getClass().getName(), " ::: DATA IS ::: " + responseString);
                return responseString;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        translate(response);
    }

    public void translate(String response) {
        if (response != null) {
            GsonBuilder gson = new GsonBuilder();
            T typeClass = null;
            try {
                typeClass = gson.create().fromJson(response, clazz);
                callback.onSuccess(typeClass);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
                callback.onError();
            }
        }
    }
}
