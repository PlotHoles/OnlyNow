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

public class VideoPostRequest<T> {


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void onPostRequest(final Context context, String url, List<Pair<String, String>> pairList,
                              List<File> listFiles, File fileVideo, File fileThumb, final Class<T> clazz, final OnResponse<T> t) {

        new VideoPostAsync<T>(context, url, pairList, listFiles, fileVideo, fileThumb, clazz, t).execute();
    }

}
