package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.CouponDetailWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by hitesh on 7/3/17.
 */

public class DetailFragemenBackend {

    String user_id;
    String coup_id;
    CouponDetailProvider couponDetailProvider;
    Context context;

    public DetailFragemenBackend(String coup_id, CouponDetailProvider couponDetailProvider, Context context, String user_id) {
        this.coup_id = coup_id;
        this.couponDetailProvider = couponDetailProvider;
        this.context = context;
        this.user_id = user_id;
        callCouponDetail();
    }

    private void callCouponDetail() {
        new GetRequest<CouponDetailWrapper>().toGetRequest(context, RequestApi.USERSERVICE, new ReqestParameter().toGetCouponById(coup_id), CouponDetailWrapper.class, new OnResponse<CouponDetailWrapper>() {
            @Override
            public void onSuccess(CouponDetailWrapper couponDetailWrapper) {
                if (couponDetailWrapper.getStatus()==1) {
                    couponDetailProvider.onCouponSuccess(couponDetailWrapper);
                }else{
                    couponDetailProvider.onFailure(couponDetailWrapper.getMessage());
                }
            }

            @Override
            public void onError() {
                couponDetailProvider.onFailure("Please try again..!!");
            }
        });
    }

    public interface CouponDetailProvider {

        void onCouponSuccess(CouponDetailWrapper couponDetailWrapper);

        void onFailure(String msg);
    }
}
