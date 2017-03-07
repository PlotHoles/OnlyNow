package com.sparecode.vipul.onlynow.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vipul on 4/3/17.
 */

public class ClientGetCouponData {

    @SerializedName("coupon")
    @Expose
    private ClientGetCoupon coupon;
    @SerializedName("images")
    @Expose
    private List<ClientGetCouponImage> images = null;

    public ClientGetCoupon getCoupon() {
        return coupon;
    }

    public void setCoupon(ClientGetCoupon coupon) {
        this.coupon = coupon;
    }

    public List<ClientGetCouponImage> getImages() {
        return images;
    }

    public void setImages(List<ClientGetCouponImage> images) {
        this.images = images;
    }
}
