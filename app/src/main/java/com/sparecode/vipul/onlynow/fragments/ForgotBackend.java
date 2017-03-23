package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.ForgotWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 23/3/17.
 */

public class ForgotBackend {

    private Context context;
    private String email;
    ForgotDataProvider forgotDataProvider;

    public ForgotBackend(Context context, String email, ForgotDataProvider forgotDataProvider) {
        this.context = context;
        this.email = email;
        this.forgotDataProvider = forgotDataProvider;
        call();
    }

    private void call()
    {
        new GetRequest<ForgotWrapper>().toGetRequest(context, RequestApi.COMMONSERVICE, new ReqestParameter().toForgotPassword(email), ForgotWrapper.class, new OnResponse<ForgotWrapper>() {
            @Override
            public void onSuccess(ForgotWrapper forgotWrapper) {

                if (forgotWrapper.getStatus() == 0)
                {
                    forgotDataProvider.onFailure(forgotWrapper.getMessage());
                }
                else
                {
                    forgotDataProvider.onSuccessfull(forgotWrapper);
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    public interface ForgotDataProvider{

        void onSuccessfull(ForgotWrapper forgotWrapper);

        void onFailure(String msg);
    }
}
