package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.SignupWrapper;
import com.sparecode.vipul.onlynow.model.UpdateProfileWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by hitesh on 20/3/17.
 */

public class UpdateProfileBackendUser {

    Context context;
    String UserId;
    String fname;
    String lname;
    UpdateProfileDataProvider updateProfileDataProvider;
    ProfileUpdateDataProvider profileUpdateDataProvider;

    UpdateProfileBackendUser(Context context, String UserId, String fname, String lname, UpdateProfileDataProvider updateProfileDataProvider) {
        this.context = context;
        this.UserId = UserId;
        this.fname = fname;
        this.lname = lname;
        this.updateProfileDataProvider = updateProfileDataProvider;
        call();
    }

    private void call() {
        new GetRequest<UpdateProfileWrapper>().toGetRequest(context, RequestApi.COMMONSERVICE, new ReqestParameter().toGetUpdateProfile(UserId, fname, lname), UpdateProfileWrapper.class, new OnResponse<UpdateProfileWrapper>() {
            @Override
            public void onSuccess(UpdateProfileWrapper updateProfileWrapper) {
                if (updateProfileWrapper.getStatus() == 1) {
                    updateProfileDataProvider.onSuccess(updateProfileWrapper);
                } else {
                    updateProfileDataProvider.onFailure("" + updateProfileWrapper.getMessage());

                }
            }

            @Override
            public void onError() {
                updateProfileDataProvider.onFailure("Please Try again");
            }
        });
    }

    UpdateProfileBackendUser(Context context, String userId, ProfileUpdateDataProvider profileUpdateDataProvider) {
        this.context = context;
        this.UserId = userId;
        this.profileUpdateDataProvider = profileUpdateDataProvider;
        callupdateprofile();
    }

    private void callupdateprofile() {
        new GetRequest<SignupWrapper>().toGetRequest(context, RequestApi.COMMONSERVICE, new ReqestParameter().toGetProfileUpdate(UserId), SignupWrapper.class, new OnResponse<SignupWrapper>() {
            @Override
            public void onSuccess(SignupWrapper profileUpdateWrapper) {
                if (profileUpdateWrapper.getStatus() == 1) {
                    profileUpdateDataProvider.onSuccess(profileUpdateWrapper);
                } else {
                    profileUpdateDataProvider.onFailure("" + profileUpdateWrapper.getMessage());
                }
            }
            @Override
            public void onError() {
                profileUpdateDataProvider.onFailure("Please Try again");
            }
        });
    }


    public interface UpdateProfileDataProvider {

        void onSuccess(UpdateProfileWrapper updateProfileWrapper);

        void onFailure(String msg);
    }

    public interface ProfileUpdateDataProvider {

        void onSuccess(SignupWrapper profileUpdateWrapper);

        void onFailure(String msg);
    }


}
