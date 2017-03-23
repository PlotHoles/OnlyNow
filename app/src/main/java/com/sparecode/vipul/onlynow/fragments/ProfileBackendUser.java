package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.SelectCategoryWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by hitesh on 21/3/17.
 */

public class ProfileBackendUser
{
    String UserId;
    Context  context;
    ProfileDataProvider profileDataProvider;

    public ProfileBackendUser(String userId, Context context, ProfileDataProvider profileDataProvider) {
        UserId = userId;
        this.context = context;
        this.profileDataProvider = profileDataProvider;
        callWs();
    }


    public void callWs() {
        new GetRequest<SelectCategoryWrapper>().toGetRequest(context, RequestApi.COMMONSERVICE, new ReqestParameter().toGetSelectFavCategory(UserId), SelectCategoryWrapper.class, new OnResponse<SelectCategoryWrapper>() {
            @Override
            public void onSuccess(SelectCategoryWrapper selectCategoryWrapper) {
                if (selectCategoryWrapper.getStatus() == 1) {
                    profileDataProvider.onSuccess(selectCategoryWrapper);
                } else {
                    profileDataProvider.onFailure("" + selectCategoryWrapper.getData());
                }
            }

            @Override
            public void onError() {
                profileDataProvider.onFailure("Please try again !!");
            }
        });
    }

    public interface ProfileDataProvider {
        void onSuccess(SelectCategoryWrapper selectCategoryWrapper);

        void onFailure(String msg);
    }
}
