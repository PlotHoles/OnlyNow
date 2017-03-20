package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.MyListSavedWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by hitesh on 15/3/17.
 */

public class CouponSavedBackend {

    private Context context;
    private String userId;
    CouponSavedDataProvider couponSavedDataProvider;

    public CouponSavedBackend(Context context, String userId, CouponSavedDataProvider couponSavedDataProvider) {
        this.context = context;
        this.userId = userId;
        this.couponSavedDataProvider = couponSavedDataProvider;
        //call();
    }

    public void call(int page)
    {
        new GetRequest<MyListSavedWrapper>().toGetRequest(context, RequestApi.USERSERVICE, new ReqestParameter().toGetMyListSaved(userId,page), MyListSavedWrapper.class, new OnResponse<MyListSavedWrapper>() {
            @Override
            public void onSuccess(MyListSavedWrapper myListSavedWrapper) {
                if (myListSavedWrapper.getStatus() == 1) {
                    couponSavedDataProvider.onSavedSuccess(myListSavedWrapper);
                } else {
                    couponSavedDataProvider.onFailure("" + myListSavedWrapper.getMessage());
                }
            }

            @Override
            public void onError() {
                couponSavedDataProvider.onFailure("Please Try again");
            }
        });
    }

    public interface CouponSavedDataProvider
    {
        void onSavedSuccess(MyListSavedWrapper myListSavedWrapper);

        void onFailure(String msg);
    }
}
