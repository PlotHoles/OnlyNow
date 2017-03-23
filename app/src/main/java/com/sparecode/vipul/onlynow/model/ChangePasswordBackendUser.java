package com.sparecode.vipul.onlynow.model;

import android.content.Context;

import com.sparecode.vipul.onlynow.fragments.ChangePasswordWrapper;
import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by hitesh on 20/3/17.
 */

public class ChangePasswordBackendUser {

    Context context;
    String UserId;
    String OldPassword;
    String NewPassword;
    ChangePasswordDataProvider changePasswordDataProvider;
    public ChangePasswordBackendUser(Context context, String userId, String oldPassword, String newPassword,ChangePasswordDataProvider changePasswordDataProvider) {
        this.context = context;
        this.UserId = userId;
        this.OldPassword = oldPassword;
        this.NewPassword = newPassword;
        this.changePasswordDataProvider=changePasswordDataProvider;
        call();
    }

    public void call(){
    new GetRequest<ChangePasswordWrapper>().toGetRequest(context, RequestApi.COMMONSERVICE, new ReqestParameter().toGetChangePassword(UserId, OldPassword, NewPassword), ChangePasswordWrapper.class, new OnResponse<ChangePasswordWrapper>() {
        @Override
        public void onSuccess(ChangePasswordWrapper changePasswordWrapper) {
            if (changePasswordWrapper.getStatus() == 0) {
                changePasswordDataProvider.onFailure(changePasswordWrapper.getMessage());
            } else {
                changePasswordDataProvider.onSuccess(changePasswordWrapper);
            }
        }

        @Override
        public void onError() {

        }
    });
    }
    public interface ChangePasswordDataProvider {
        void onSuccess(ChangePasswordWrapper changePasswordWrapper);

        void onFailure(String msg);
    }
    }
