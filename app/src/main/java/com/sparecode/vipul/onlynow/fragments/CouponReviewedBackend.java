package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.MyListReviewedWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by hitesh on 15/3/17.
 */

public class CouponReviewedBackend {

    private Context context;
    private String userId;
    CouponReviewedDataProvider couponReviewedDataProvider;

    public CouponReviewedBackend(Context context, String userId, CouponReviewedDataProvider couponReviewedDataProvider) {
        this.context = context;
        this.userId = userId;
        this.couponReviewedDataProvider = couponReviewedDataProvider;
        //call();
    }

    public void call(int page)
    {
        new GetRequest<MyListReviewedWrapper>().toGetRequest(context, RequestApi.USERSERVICE, new ReqestParameter().toGetReviewed(userId, page), MyListReviewedWrapper.class, new OnResponse<MyListReviewedWrapper>() {
            @Override
            public void onSuccess(MyListReviewedWrapper myListReviewedWrapper) {
                if (myListReviewedWrapper.getStatus() == 1) {
                    couponReviewedDataProvider.onReviewedSuccess(myListReviewedWrapper);
                } else {
                    couponReviewedDataProvider.onFailure("" + myListReviewedWrapper.getMessage());
                }
            }

            @Override
            public void onError() {
                couponReviewedDataProvider.onFailure("Please Try again");
            }
        });
    }

    public interface CouponReviewedDataProvider
    {
        void onReviewedSuccess(MyListReviewedWrapper myListReviewedWrapper);

        void onFailure(String msg);
    }
}
