package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.SaveReviewWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by hitesh on 19/4/17.
 */

public class SaveReviewBackend {
    Context context;
    String shop_id,user_id,review,star;
    ReviewDataProvider reviewDataProvider;

    public SaveReviewBackend(Context context, String shop_id, String user_id, String review, String star, ReviewDataProvider reviewDataProvider) {
        this.context = context;
        this.shop_id = shop_id;
        this.user_id = user_id;
        this.review = review;
        this.star = star;
        this.reviewDataProvider = reviewDataProvider;
        call();
    }



    private void call() {
        new GetRequest<SaveReviewWrapper>().toGetRequest(context, RequestApi.USERSERVICE, new ReqestParameter().toAddReviewToShop(shop_id,user_id,review,star), SaveReviewWrapper.class, new OnResponse<SaveReviewWrapper>() {
            @Override
            public void onSuccess(SaveReviewWrapper saveReviewWrapper) {
                if (saveReviewWrapper.getStatus() == 1) {
                    reviewDataProvider.Successfull(saveReviewWrapper);
                } else {
                    reviewDataProvider.Failure("" + saveReviewWrapper.getMessage());
                }
            }

            @Override
            public void onError() {
                reviewDataProvider.Failure("Please Try again");
            }
        });
    }

    public interface ReviewDataProvider{

        void Successfull(SaveReviewWrapper saveReviewWrapper);

        void Failure(String msg);
    }
}
