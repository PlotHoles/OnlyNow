package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.SignupWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by hitesh on 28/2/17.
 */

public class Signupstep1Backend {

    private String fname, lname, email, pwd, bdate, gender, lat, lng, device_id;
    private String fbid;
    private String recieve_email;
    private Context mContext;
    private SignupWrapperProvider signupWrapperProvider;

    public Signupstep1Backend(Context mContext, String fname, String lname, String email, String pwd, String bdate, String gender, String lat, String lng, String recieve_email, String device_id, SignupWrapperProvider signupWrapperProvider) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.pwd = pwd;
        this.bdate = bdate;
        this.gender = gender;
        this.lat = lat;
        this.lng = lng;
        this.device_id = device_id;
        this.recieve_email = recieve_email;
        this.mContext = mContext;
        this.signupWrapperProvider = signupWrapperProvider;
        register();
    }

    public Signupstep1Backend(Context mContext, String fname, String lname, String email, String pwd, String bdate, String gender, String lat, String lng, String device_id, String recieve_email, String fbid, SignupWrapperProvider signupWrapperProvider) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.pwd = pwd;
        this.bdate = bdate;
        this.gender = gender;
        this.lat = lat;
        this.lng = lng;
        this.device_id = device_id;
        this.fbid = fbid;
        this.recieve_email = recieve_email;
        this.mContext = mContext;
        this.signupWrapperProvider = signupWrapperProvider;
        fbRegister();
    }

    private void register() {
        new GetRequest<SignupWrapper>().toGetRequest(mContext, RequestApi.USERSERVICE, new ReqestParameter().toSignup(fname, lname, email, pwd, bdate, gender, lat, lng, recieve_email, "A", device_id), SignupWrapper.class, new OnResponse<SignupWrapper>() {
            @Override
            public void onSuccess(SignupWrapper signupWrapper) {
                if (signupWrapper.getStatus() == 1) {
                    signupWrapperProvider.onSuccess(signupWrapper);
                } else {
                    signupWrapperProvider.onFailure(signupWrapper.getMessage());
                }
            }

            @Override
            public void onError() {
                signupWrapperProvider.onFailure("Please try after sometime !");
            }
        });
    }

    private void fbRegister() {
        new GetRequest<SignupWrapper>().toGetRequest(mContext, RequestApi.USERSERVICE, new ReqestParameter().toFbSignup(fname, lname, email, pwd, bdate, gender, lat, lng, recieve_email, "A", device_id, fbid), SignupWrapper.class, new OnResponse<SignupWrapper>() {
            @Override
            public void onSuccess(SignupWrapper signupWrapper) {
                if (signupWrapper.getStatus() == 1) {
                    signupWrapperProvider.onSuccess(signupWrapper);
                } else {
                    signupWrapperProvider.onFailure(signupWrapper.getMessage());
                }
            }

            @Override
            public void onError() {
                signupWrapperProvider.onFailure("Please try after sometime !");
            }
        });
    }
    interface SignupWrapperProvider {
        void onSuccess(SignupWrapper signupWrapper);

        void onFailure(String msg);
    }
}
