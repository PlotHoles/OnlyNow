package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.CouponWrapper;
import com.sparecode.vipul.onlynow.model.FavCategoryWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by hitesh on 6/3/17.
 */

public class HomeFragmentBackend {
    //get_coupons_by_category
    String user_id;
    String cat_id;
    Context mContext;
    String sort_by;
    HomePageDataProvider homePageDataProvider;

    public HomeFragmentBackend(String user_id, Context mContext, HomePageDataProvider homePageDataProvider) {
        this.user_id = user_id;
        this.mContext = mContext;
        this.homePageDataProvider = homePageDataProvider;
        call();
    }

    public HomeFragmentBackend(String user_id, String cat_id, Context mContext, HomePageDataProvider homePageDataProvider) {
        this.user_id = user_id;
        this.cat_id = cat_id;
        this.mContext = mContext;
        this.homePageDataProvider = homePageDataProvider;
        callCouponWs();
    }

    public HomeFragmentBackend(String user_id, String cat_id,String sort_by, Context mContext, HomePageDataProvider homePageDataProvider) {
        this.user_id = user_id;
        this.cat_id = cat_id;
        this.mContext = mContext;
        this.sort_by = sort_by;
        this.homePageDataProvider = homePageDataProvider;
        callCouponWs();
    }

    private void call() {

        new GetRequest<FavCategoryWrapper>().toGetRequest(mContext, RequestApi.USERSERVICE, new ReqestParameter().toGetFavCategory(user_id), FavCategoryWrapper.class, new OnResponse<FavCategoryWrapper>() {
            @Override
            public void onSuccess(FavCategoryWrapper favCategoryWrapper) {
                if (favCategoryWrapper.getStatus() == 0) {
                    homePageDataProvider.onFailure(favCategoryWrapper.getMessage());
                } else {
                    homePageDataProvider.onFavCatSuccess(favCategoryWrapper);
                }
            }

            @Override
            public void onError() {
                homePageDataProvider.onFailure("Please try again !!");
            }
        });
    }

    private void callCouponWs() {
        new GetRequest<CouponWrapper>().toGetRequest(mContext, RequestApi.USERSERVICE, new ReqestParameter().toGetCoupnsByCategory(user_id, cat_id,sort_by), CouponWrapper.class, new OnResponse<CouponWrapper>() {
            @Override
            public void onSuccess(CouponWrapper couponWrapper) {
                if (couponWrapper.getStatus() == 0) {
                    homePageDataProvider.onFailure(couponWrapper.getMessage());
                } else {
                    homePageDataProvider.onCouponSuccess(couponWrapper);
                }
            }

            @Override
            public void onError() {
                homePageDataProvider.onFailure("Please try again !!");
            }
        });
    }


    public interface HomePageDataProvider {
        void onFavCatSuccess(FavCategoryWrapper favCategoryWrapper);

        void onCouponSuccess(CouponWrapper couponWrapper);

        void onFailure(String msg);
    }
}
