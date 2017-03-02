package com.sparecode.vipul.onlynow.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vipul on 27/2/17.
 */

public class ClientShopWrapper {

    @SerializedName("status")
    private Integer status;
    @SerializedName("message")
    private String message;
    @SerializedName("time")
    private String time;
    @SerializedName("data")
    private ClientShopData data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ClientShopData getData() {
        return data;
    }

    public void setData(ClientShopData data) {
        this.data = data;
    }

}

