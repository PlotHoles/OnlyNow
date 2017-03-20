package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.MyListFavoriteWrapper;
import com.sparecode.vipul.onlynow.model.MyListReviewedWrapper;
import com.sparecode.vipul.onlynow.model.MyListSavedWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by hitesh on 9/3/17.
 */

public class MyListBackend {

    //get_saved_coupons , get_user_shops , get_reviewed_shops
    private String userId;
    private int type;
    private String serviceId;
    private Context mContext;
    private MyListDataProvider myListDataProvider;

    public MyListBackend(String userId, String serviceId, Context mContext, MyListDataProvider myListDataProvider) {
        this.userId = userId;
        this.serviceId = serviceId;
        this.mContext = mContext;
        this.myListDataProvider = myListDataProvider;


    }

    public MyListBackend(String userId, int type, String serviceId, Context mContext, MyListDataProvider myListDataProvider) {
        this.userId = userId;
        this.type = type;
        this.serviceId = serviceId;
        this.mContext = mContext;
        this.myListDataProvider = myListDataProvider;

    }


    public void call1(int page) {
        new GetRequest<MyListSavedWrapper>().toGetRequest(mContext, RequestApi.USERSERVICE, new ReqestParameter().toGetMyListSaved(userId, page), MyListSavedWrapper.class, new OnResponse<MyListSavedWrapper>() {
            @Override
            public void onSuccess(MyListSavedWrapper myListSavedWrapper) {
                if (myListSavedWrapper.getStatus() == 1) {
                    myListDataProvider.onSavedSuccess(myListSavedWrapper);
                } else {
                    myListDataProvider.onFailure("" + myListSavedWrapper.getMessage());
                }
            }

            @Override
            public void onError() {
                myListDataProvider.onFailure("Please Try again");
            }
        });
    }

    public void call2(int page) {
        new GetRequest<MyListFavoriteWrapper>().toGetRequest(mContext, RequestApi.USERSERVICE, new ReqestParameter().toGetUserShops(userId, page, type), MyListFavoriteWrapper.class, new OnResponse<MyListFavoriteWrapper>() {
            @Override
            public void onSuccess(MyListFavoriteWrapper myListFavoriteWrapper) {
                if (myListFavoriteWrapper.getStatus() == 1) {
                    myListDataProvider.onFavOrRecentViewSuccess(myListFavoriteWrapper);
                } else {
                    myListDataProvider.onFailure("" + myListFavoriteWrapper.getMessage());
                }
            }

            @Override
            public void onError() {
                myListDataProvider.onFailure("Please Try again");
            }
        });
    }

    private void call3(int page) {
        new GetRequest<MyListSavedWrapper>().toGetRequest(mContext, RequestApi.USERSERVICE, new ReqestParameter().toGetMyListSaved(userId, page), MyListSavedWrapper.class, new OnResponse<MyListSavedWrapper>() {
            @Override
            public void onSuccess(MyListSavedWrapper myListSavedWrapper) {

            }

            @Override
            public void onError() {

            }
        });
    }

    public void call4(int page) {
        new GetRequest<MyListReviewedWrapper>().toGetRequest(mContext, RequestApi.USERSERVICE, new ReqestParameter().toGetReviewed(userId, page), MyListReviewedWrapper.class, new OnResponse<MyListReviewedWrapper>() {
            @Override
            public void onSuccess(MyListReviewedWrapper myListReviewedWrapper) {
                if (myListReviewedWrapper.getStatus() == 1) {
                    myListDataProvider.onReviewedSuccess(myListReviewedWrapper);
                } else {
                    myListDataProvider.onFailure("" + myListReviewedWrapper.getMessage());
                }
            }

            @Override
            public void onError() {
                myListDataProvider.onFailure("Please Try again");
            }
        });
    }

    public interface MyListDataProvider {
        void onSavedSuccess(MyListSavedWrapper myListSavedWrapper);

        void onFavOrRecentViewSuccess(MyListFavoriteWrapper myListFavoriteWrapper);

        //void onRecentViewSuccess();

        void onReviewedSuccess(MyListReviewedWrapper myListReviewedWrapper);

        void onFailure(String msg);
    }
}
