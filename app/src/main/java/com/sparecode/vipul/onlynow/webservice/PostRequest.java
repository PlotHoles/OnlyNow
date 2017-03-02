package com.sparecode.vipul.onlynow.webservice;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.util.Pair;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;

import java.io.File;
import java.util.List;


/**
 * Created by user_1 on 10/13/2016.
 */

public class PostRequest<T> {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void onPostRequest(final Context context, String url, List<Pair<String, String>> pairList,
                              String keyImage, File file, final Class<T> clazz, final OnResponse<T> t) {
        new PostAsync<T>(context, url, pairList, keyImage, file, clazz, t).execute();
    }

    public void onPostRequestMessage(final Context context, String url, List<Pair<String, String>> pairList, final Class<T> clazz, final OnResponse<T> t) {

        new PostAsync<T>(context, url, pairList, clazz, t).execute();
    }
}
