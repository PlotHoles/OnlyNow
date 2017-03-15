package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.LogoutWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 10/3/17.
 */

public class LogoutBackend {

    private Context context;
    private String userId;
    LogoutBackendResultProvider logoutBackendResultProvider;

    public LogoutBackend(Context context, String userId, LogoutBackendResultProvider logoutBackendResultProvider) {
        this.context = context;
        this.userId = userId;
        this.logoutBackendResultProvider = logoutBackendResultProvider;
        call();
    }

    private void call()
    {
        new GetRequest<LogoutWrapper>().toGetRequest(context, RequestApi.COMMONSERVICE, new ReqestParameter().toLogout(userId), LogoutWrapper.class, new OnResponse<LogoutWrapper>() {
            @Override
            public void onSuccess(LogoutWrapper logoutWrapper) {

                if (logoutWrapper.getStatus() == 0)
                {
                    logoutBackendResultProvider.onLogoutFailure(logoutWrapper.getMessage());
                }
                else
                {
                    logoutBackendResultProvider.onSuccessfullLogout(logoutWrapper);
                }
            }

            @Override
            public void onError() {

            }
        });
    }
    public interface LogoutBackendResultProvider{

        void onSuccessfullLogout(LogoutWrapper logoutWrapper);

        void onLogoutFailure(String msg);
    }
}
