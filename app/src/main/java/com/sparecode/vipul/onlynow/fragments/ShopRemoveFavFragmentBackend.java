package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.RemoveFavShopWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by hitesh on 20/3/17.
 */

public class ShopRemoveFavFragmentBackend {

    String id;
    Context context;
    ShopRemoveFavDataProvider shopRemoveFavDataProvider;

    public ShopRemoveFavFragmentBackend(String id, Context context, ShopRemoveFavDataProvider shopRemoveFavDataProvider) {
        this.id = id;
        this.context = context;
        this.shopRemoveFavDataProvider = shopRemoveFavDataProvider;
        callRemoveFav();
    }

    private void callRemoveFav() {
        new GetRequest<RemoveFavShopWrapper>().toGetRequest(context, RequestApi.USERSERVICE, new ReqestParameter().toGetRemoveFavShop(id), RemoveFavShopWrapper.class, new OnResponse<RemoveFavShopWrapper>() {
            @Override
            public void onSuccess(RemoveFavShopWrapper removeFavShopWrapper) {
                if (removeFavShopWrapper.getStatus() == 1) {
                    shopRemoveFavDataProvider.onSuccess(removeFavShopWrapper);
                } else {
                    shopRemoveFavDataProvider.onFailure("" + removeFavShopWrapper.getMessage());
                }
            }
            @Override
            public void onError() {
                shopRemoveFavDataProvider.onFailure("Please Try again");
            }
        });
    }

    public interface ShopRemoveFavDataProvider {

        void onSuccess(RemoveFavShopWrapper removeFavShopWrapper);

        void onFailure(String msg);
    }
}
