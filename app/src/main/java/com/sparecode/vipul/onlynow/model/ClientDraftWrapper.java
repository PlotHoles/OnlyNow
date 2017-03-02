package com.sparecode.vipul.onlynow.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vipul on 28/2/17.
 */

public class ClientDraftWrapper {

    @SerializedName("status")
    private Integer status;
    @SerializedName("message")
    private String message;
    @SerializedName("time")
    private String time;
    @SerializedName("page")
    private Integer page;
    @SerializedName("data")
    private List<ClientDraftData> data = null;

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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<ClientDraftData> getData() {
        return data;
    }

    public void setData(List<ClientDraftData> data) {
        this.data = data;
    }

}
