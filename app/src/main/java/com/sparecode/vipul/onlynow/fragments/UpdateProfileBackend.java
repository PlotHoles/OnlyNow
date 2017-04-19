package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.LoginWrapper;
import com.sparecode.vipul.onlynow.model.UpdateProfileWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 17/3/17.
 */

public class UpdateProfileBackend {

    private Context context;
    private String userId,fname,lname;
    UpdateProfileDataProvider updateProfileDataProvider;
    ProfileUpdateData profileUpdateData;

    public UpdateProfileBackend(Context context, String userId, String fname, String lname, UpdateProfileDataProvider updateProfileDataProvider) {
        this.context = context;
        this.userId = userId;
        this.fname = fname;
        this.lname = lname;
        this.updateProfileDataProvider = updateProfileDataProvider;
        call();
    }

    private void call()
    {
        new GetRequest<UpdateProfileWrapper>().toGetRequest(context, RequestApi.COMMONSERVICE, new ReqestParameter().toUpdateProfile(userId, fname, lname), UpdateProfileWrapper.class, new OnResponse<UpdateProfileWrapper>() {
            @Override
            public void onSuccess(UpdateProfileWrapper updateProfileWrapper) {
                if (updateProfileWrapper.getStatus() == 0)
                {
                    updateProfileDataProvider.onFailure(updateProfileWrapper.getMessage());
                }
                else
                {
                    updateProfileDataProvider.onSuccesscull(updateProfileWrapper);
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    UpdateProfileBackend(Context context, String userId, ProfileUpdateData profileUpdateData) {
        this.context = context;
        this.userId = userId;
        this.profileUpdateData = profileUpdateData;
        callupdateprofile();
    }

    private void callupdateprofile() {
        new GetRequest<LoginWrapper>().toGetRequest(context, RequestApi.COMMONSERVICE, new ReqestParameter().toGetProfileUpdate(userId), LoginWrapper.class, new OnResponse<LoginWrapper>() {
            @Override
            public void onSuccess(LoginWrapper profileUpdateWrapper) {
                if (profileUpdateWrapper.getStatus() == 1) {
                    profileUpdateData.onSuccess(profileUpdateWrapper);
                } else {
                    profileUpdateData.onFailure("" + profileUpdateWrapper.getMessage());
                }
            }
            @Override
            public void onError() {
                profileUpdateData.onFailure("Please Try again");
            }
        });
    }
    public interface UpdateProfileDataProvider{

        void onSuccesscull(UpdateProfileWrapper updateProfileWrapper);

        void onFailure(String msg);
    }
    public interface ProfileUpdateData {

        void onSuccess(LoginWrapper profileUpdateWrapper);

        void onFailure(String msg);
    }
}
