package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 18/3/17.
 */

public class ChangePasswordbackend {

    private Context context;
    private String userId;
    private  String oldPassword;
    private String newPassword;
    ChangePasswordDataProvider changePasswordDataProvider;

    public ChangePasswordbackend(Context context, String userId, String oldPassword, String newPassword, ChangePasswordbackend.ChangePasswordDataProvider changePasswordDataProvider) {
        this.context = context;
        this.userId = userId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.changePasswordDataProvider = changePasswordDataProvider;
        call();
    }

    private void call()
    {
        new GetRequest<ChangePasswordWrapper>().toGetRequest(context, RequestApi.COMMONSERVICE, new ReqestParameter().toChangePassword(userId, oldPassword, newPassword), ChangePasswordWrapper.class, new OnResponse<ChangePasswordWrapper>() {
            @Override
            public void onSuccess(ChangePasswordWrapper changePasswordWrapper) {
                if (changePasswordWrapper.getStatus() == 0)
                {
                    changePasswordDataProvider.onFailure(changePasswordWrapper.getMessage());
                }
                else
                {
                    changePasswordDataProvider.onSuccessfull(changePasswordWrapper);
                }
            }

            @Override
            public void onError() {

            }
        });
    }
    public interface ChangePasswordDataProvider
    {
        void onSuccessfull(ChangePasswordWrapper changePasswordWrapper);

        void onFailure(String msg);
    }
}
