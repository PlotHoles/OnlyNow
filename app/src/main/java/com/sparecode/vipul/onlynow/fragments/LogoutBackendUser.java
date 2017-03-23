package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.LogoutWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by hitesh on 18/3/17.
 */

public class LogoutBackendUser {

    Context context;
    String UserId;
    LogoutDataProvider logoutDataProvider;

    public LogoutBackendUser(Context context, String userId, LogoutDataProvider logoutDataProvider) {
        this.context = context;
        this.UserId = userId;
        this.logoutDataProvider = logoutDataProvider;
        call();
    }

    private void call() {

        new GetRequest<LogoutWrapper>().toGetRequest(context, RequestApi.COMMONSERVICE, new ReqestParameter().toLogout(UserId), LogoutWrapper.class, new OnResponse<LogoutWrapper>() {
            @Override
            public void onSuccess(LogoutWrapper logoutWrapper) {
                if (logoutWrapper.getStatus() == 0) {
                    logoutDataProvider.onFailure(logoutWrapper.getMessage());
                } else {
                    logoutDataProvider.onSuccess(logoutWrapper);
                }
            }

            @Override
            public void onError() {
                logoutDataProvider.onFailure("Please try again !!");
            }
        });
    }


    public interface LogoutDataProvider {
        void onSuccess(LogoutWrapper logoutWrapper);

        void onFailure(String msg);
    }
}
