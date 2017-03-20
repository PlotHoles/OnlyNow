package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.ReviewWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by hitesh on 16/3/17.
 */

public class ReviewFragmentBackend {
    Context context;
    String shopId;
    ShopReviewDataProvider shopReviewDataProvider;

    public ReviewFragmentBackend(Context context, String shopId, ShopReviewDataProvider shopReviewDataProvider) {
        this.context = context;
        this.shopId = shopId;
        this.shopReviewDataProvider = shopReviewDataProvider;
        call();
    }

    private void call() {
        new GetRequest<ReviewWrapper>().toGetRequest(context, RequestApi.COMMONSERVICE, new ReqestParameter().toGetReviewShop(shopId), ReviewWrapper.class, new OnResponse<ReviewWrapper>() {
            @Override
            public void onSuccess(ReviewWrapper reviewWrapper) {
                if (reviewWrapper.getStatus() == 1) {
                    shopReviewDataProvider.onSuccess(reviewWrapper);
                } else {
                    shopReviewDataProvider.onFailure("" + reviewWrapper.getMessage());
                }
            }

            @Override
            public void onError() {
                shopReviewDataProvider.onFailure("Please Try again");
            }
        });
    }

    public interface ShopReviewDataProvider {

        void onSuccess(ReviewWrapper reviewWrapper);

        void onFailure(String msg);
    }
}
