package com.sparecode.vipul.onlynow.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vipul on 1/3/17.
 */

public class ClientAnalyticsData {

    @SerializedName("coupons")
    @Expose
    private List<ClientsAnalyticsCoupon> coupons = null;
    @SerializedName("client")
    @Expose
    private ClientAnalyticsClient client;

    public List<ClientsAnalyticsCoupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<ClientsAnalyticsCoupon> coupons) {
        this.coupons = coupons;
    }

    public ClientAnalyticsClient getClient() {
        return client;
    }

    public void setClient(ClientAnalyticsClient client) {
        this.client = client;
    }
}
