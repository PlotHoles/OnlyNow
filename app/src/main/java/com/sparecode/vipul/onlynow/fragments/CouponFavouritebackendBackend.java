package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.MyListFavoriteWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by hitesh on 15/3/17.
 */

public class CouponFavouritebackendBackend {

    private Context context;
    private String userId;
    int type;
    CouponFavouriteDataProvider couponFavouriteDataProvider;

    public CouponFavouritebackendBackend(Context context, String userId,int type, CouponFavouriteDataProvider couponFavouriteDataProvider) {
        this.context = context;
        this.userId = userId;
        this.couponFavouriteDataProvider = couponFavouriteDataProvider;
        this.type = type;
        //call();
    }

    public void call(int page) {
        new GetRequest<MyListFavoriteWrapper>().toGetRequest(context, RequestApi.USERSERVICE, new ReqestParameter().toGetUserShops(userId, page, type), MyListFavoriteWrapper.class, new OnResponse<MyListFavoriteWrapper>() {
            @Override
            public void onSuccess(MyListFavoriteWrapper myListFavoriteWrapper) {
                if (myListFavoriteWrapper.getStatus() == 1) {
                    couponFavouriteDataProvider.onFavouriteSuccess(myListFavoriteWrapper);
                } else {
                    couponFavouriteDataProvider.onFailure("" + myListFavoriteWrapper.getMessage());
                }
            }

            @Override
            public void onError() {
                couponFavouriteDataProvider.onFailure("Please Try again");
            }
        });
    }

    public interface CouponFavouriteDataProvider
    {
        void onFavouriteSuccess(MyListFavoriteWrapper myListFavoriteWrapper);

        void onFailure(String msg);
    }
}
