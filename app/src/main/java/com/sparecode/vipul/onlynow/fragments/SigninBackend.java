package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.SigninWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by hitesh on 23/2/17.
 */

public class SigninBackend {

    private String email, pwd, lat, lng, deviceType, deviceId, fbId;
    private Context context;
    private SigninResultProvider signinResultProvider;

    public SigninBackend(Context context, String email, String pwd, String lat, String lng, String deviceType, String deviceId, SigninResultProvider signinResultProvider) {
        this.email = email;
        this.pwd = pwd;
        this.lat = lat;
        this.lng = lng;
        this.deviceType = deviceType;
        this.deviceId = deviceId;
        this.context = context;
        this.signinResultProvider = signinResultProvider;
        call();
    }

    public SigninBackend(Context context, String fbId, String email, String pwd, String lat, String lng, String deviceType, String deviceId, SigninResultProvider signinResultProvider) {
        this.email = email;
        this.pwd = pwd;
        this.lat = lat;
        this.lng = lng;
        this.deviceType = deviceType;
        this.deviceId = deviceId;
        this.context = context;
        this.signinResultProvider = signinResultProvider;
        this.fbId = fbId;
        callFbWs();
    }

    private void call() {
        new GetRequest<SigninWrapper>().toGetRequest(context, RequestApi.USERSERVICE, new ReqestParameter().toUserLogin(email, pwd, lat, lng, deviceType, deviceId), SigninWrapper.class, new OnResponse<SigninWrapper>() {
            @Override
            public void onSuccess(SigninWrapper signinWrapper) {

                if (signinWrapper.getStatus() == 0) {
                    signinResultProvider.onLoginFailure(signinWrapper.getMessage());
                } else {
                    signinResultProvider.onSuccessFulLogin(signinWrapper);
                }
            }

            @Override
            public void onError() {
                signinResultProvider.onLoginFailure("Login Failure ! Please try after some time");
            }
        });
    }

    private void callFbWs() {
        new GetRequest<SigninWrapper>().toGetRequest(context, RequestApi.USERSERVICE, new ReqestParameter().toFbLogin(email, pwd, lat, lng, deviceType, deviceId, fbId), SigninWrapper.class, new OnResponse<SigninWrapper>() {
            @Override
            public void onSuccess(SigninWrapper signinWrapper) {
                if (signinWrapper.getStatus() == 0) {
                    signinResultProvider.onLoginFailure(signinWrapper.getMessage());
                } else {
                    signinResultProvider.onSuccessFulLogin(signinWrapper);
                }
            }

            @Override
            public void onError() {
                signinResultProvider.onLoginFailure("Login Failure ! Please try after some time");
            }
        });
    }

    public interface SigninResultProvider {
        void onSuccessFulLogin(SigninWrapper signinWrapper);

        void onLoginFailure(String msg);
    }
}
