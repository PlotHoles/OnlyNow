package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.UserChangeLocationWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 24/3/17.
 */

public class UserChangeLocationBackend {

    private Context context;
    private String user_id;
    UserChangeLocationDataProvider userChangeLocationDataProvider;

    public UserChangeLocationBackend(Context context, String user_id, UserChangeLocationDataProvider userChangeLocationDataProvider) {
        this.context = context;
        this.user_id = user_id;
        this.userChangeLocationDataProvider = userChangeLocationDataProvider;
    }

    private void call(int page)
    {
        new GetRequest<UserChangeLocationWrapper>().toGetRequest(context, RequestApi.USERSERVICE, new ReqestParameter().toUserChangeLocation(user_id, String.valueOf(page)), UserChangeLocationWrapper.class, new OnResponse<UserChangeLocationWrapper>() {
            @Override
            public void onSuccess(UserChangeLocationWrapper userChangeLocationWrapper) {

                if (userChangeLocationWrapper.getStatus() == 0)
                {
                    userChangeLocationDataProvider.onFailure(userChangeLocationWrapper.getMessage());
                }
                else
                {
                    userChangeLocationDataProvider.onSuccessfull(userChangeLocationWrapper);
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    public void callPagination(int page) {
        call(page);
    }
    public interface UserChangeLocationDataProvider{

        void onSuccessfull(UserChangeLocationWrapper userChangeLocationWrapper);

        void onFailure(String msg);


    }
}
