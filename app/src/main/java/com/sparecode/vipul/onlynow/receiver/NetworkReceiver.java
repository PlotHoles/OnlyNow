package com.sparecode.vipul.onlynow.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sparecode.vipul.onlynow.util.NetworkUtil;

/**
 * Created by hitesh on 20/2/17.
 */

public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String status= NetworkUtil.getConnectivityStatusString(context);
       // Toast.makeText(context, status , Toast.LENGTH_LONG).show();
    }
}
