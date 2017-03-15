package com.sparecode.vipul.onlynow.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vipul on 9/3/17.
 */

public class ZipWrapper {

    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("results")
    @Expose
    private List<ZipData> results = null;
    @SerializedName("status")
    @Expose
    private Integer status;

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public List<ZipData> getResults() {
        return results;
    }

    public void setResults(List<ZipData> results) {
        this.results = results;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
