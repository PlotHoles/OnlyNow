package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.SaveCouponWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by hitesh on 19/4/17.
 */

public class SaveCouponBackend {
    Context context;
    SaveCouponData saveCouponData;
    String user_id,coupon_id,name,instruction,shop_name,shop_details,description,imageURL,end_date,cat_name,area,rating;

    public SaveCouponBackend(Context context, SaveCouponData saveCouponData, String user_id, String coupon_id, String name, String instruction, String shop_name, String shop_details, String description, String imageURL, String end_date, String cat_name, String area, String rating) {
        this.context = context;
        this.saveCouponData = saveCouponData;
        this.user_id = user_id;
        this.coupon_id = coupon_id;
        this.name = name;
        this.instruction = instruction;
        this.shop_name = shop_name;
        this.shop_details = shop_details;
        this.description = description;
        this.imageURL = imageURL;
        this.end_date = end_date;
        this.cat_name = cat_name;
        this.area = area;
        this.rating = rating;
        call();
    }

    private void call() {
        new GetRequest<SaveCouponWrapper>().toGetRequest(context, RequestApi.USERSERVICE, new ReqestParameter().toSaveCoupon(user_id,coupon_id,name,instruction,shop_name,shop_details,description,imageURL,end_date,cat_name,area,rating), SaveCouponWrapper.class, new OnResponse<SaveCouponWrapper>() {
            @Override
            public void onSuccess(SaveCouponWrapper saveCouponWrapper) {
                if (saveCouponWrapper.getStatus() == 1) {
                    saveCouponData.onSuccess(saveCouponWrapper);
                } else {
                    saveCouponData.onFailure("" + saveCouponWrapper.getMessage());
                }
            }

            @Override
            public void onError() {
                saveCouponData.onFailure("Please Try again");
            }
        });
    }


    public interface SaveCouponData{
        void onSuccess(SaveCouponWrapper saveCouponWrapper);

        void onFailure(String msg);
    }
}
