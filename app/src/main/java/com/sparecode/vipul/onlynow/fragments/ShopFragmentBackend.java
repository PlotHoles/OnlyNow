package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.ShopDetailWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by hitesh on 16/3/17.
 */

public class ShopFragmentBackend {

    String userId,shopId;
    Context context;
    ShopDataProvider shopDataProvider;


    public ShopFragmentBackend(Context context, String shopId, String userId, ShopDataProvider shopDataProvider) {
        this.context = context;
        this.shopId=shopId;
        this.userId = userId;
        this.shopDataProvider = shopDataProvider;
        call();
    }

    private void call() {
        new GetRequest<ShopDetailWrapper>().toGetRequest(context, RequestApi.USERSERVICE, new ReqestParameter().toGetShop(shopId,userId), ShopDetailWrapper.class, new OnResponse<ShopDetailWrapper>() {
            @Override
            public void onSuccess(ShopDetailWrapper shopDetailWrapper) {
                if (shopDetailWrapper.getStatus() == 1) {
                    shopDataProvider.onSuccess(shopDetailWrapper);
                } else {
                    shopDataProvider.onFailure("" + shopDetailWrapper.getMessage());
                }
            }

            @Override
            public void onError() {
                shopDataProvider.onFailure("Please Try again");
            }
        });
    }

    public interface ShopDataProvider {

        void onSuccess(ShopDetailWrapper shopDetailWrapper);

        void onFailure(String msg);
    }

}
