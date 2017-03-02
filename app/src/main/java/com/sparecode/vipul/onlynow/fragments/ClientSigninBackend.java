package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;
import android.util.Log;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.LoginWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 25/2/17.
 */

public class ClientSigninBackend {

    private String email,pwd,deviceType,deviceId;
    private Context context;
    private ClientSigninResultProvider clientSigninResultProvider;

    public ClientSigninBackend(Context context, String email,String pwd,String deviceType,String deviceId,ClientSigninResultProvider clientSigninResultProvider)
    {
        this.context = context;
        this.email = email;
        this.pwd = pwd;
        this.deviceType = deviceType;
        this.deviceId = deviceId;
        this.clientSigninResultProvider = clientSigninResultProvider;
        call();
    }

    private void call()
    {
        new GetRequest<LoginWrapper>().toGetRequest(context, RequestApi.CLIENTSERVICE, new ReqestParameter().toLogin(email, pwd, deviceType, deviceId), LoginWrapper.class, new OnResponse<LoginWrapper>() {
            @Override
            public void onSuccess(LoginWrapper loginWrapper) {
                Log.e("hi","hello");
                Log.e("---->status",loginWrapper.getStatus()+"");
                if (loginWrapper.getStatus() == 0) {
                    clientSigninResultProvider.onLoginFailure(loginWrapper.getMessage());
                } else {
                    clientSigninResultProvider.onSuccessfullLogin(loginWrapper);
                }
            }

            @Override
            public void onError() {
                clientSigninResultProvider.onLoginFailure("Login Failure ! Please try after some time");
            }
        });
    }

    public interface ClientSigninResultProvider{
        void onSuccessfullLogin(LoginWrapper loginWrapper);

        void onLoginFailure(String msg);
    }
}
