package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.AddFavShopWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by hitesh on 18/3/17.
 */

public class ShopFavFragmentBackend {
    String userId,shopId;
    Context context;

    ShopFavDataProvider shopFavDataProvider;

    public ShopFavFragmentBackend(Context context, String shopId, String userId, ShopFavDataProvider shopFavDataProvider) {
        this.context = context;
        this.shopId = shopId;
        this.userId = userId;
        this.shopFavDataProvider = shopFavDataProvider;
        callFav();
    }


    private void callFav() {
        new GetRequest<AddFavShopWrapper>().toGetRequest(context, RequestApi.USERSERVICE, new ReqestParameter().toGetFavShop(shopId,userId), AddFavShopWrapper.class, new OnResponse<AddFavShopWrapper>() {
            @Override
            public void onSuccess(AddFavShopWrapper addFavShopWrapper) {
                if (addFavShopWrapper.getStatus() == 1) {
                    shopFavDataProvider.onSuccess(addFavShopWrapper);
                } else {
                    shopFavDataProvider.onFailure("" + addFavShopWrapper.getMessage());
                }
            }

            @Override
            public void onError() {
                shopFavDataProvider.onFailure("Please Try again");
            }
        });
    }

    public interface ShopFavDataProvider {

        void onSuccess(AddFavShopWrapper addFavShopWrapper);

        void onFailure(String msg);
    }

}
