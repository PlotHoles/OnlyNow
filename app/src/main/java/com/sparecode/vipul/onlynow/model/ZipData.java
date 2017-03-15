package com.sparecode.vipul.onlynow.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vipul on 9/3/17.
 */

public class ZipData {

    @SerializedName("address1")
    @Expose
    private String address1;
    @SerializedName("address2")
    @Expose
    private String address2;
    @SerializedName("address3")
    @Expose
    private String address3;
    @SerializedName("kana1")
    @Expose
    private String kana1;
    @SerializedName("kana2")
    @Expose
    private String kana2;
    @SerializedName("kana3")
    @Expose
    private String kana3;
    @SerializedName("prefcode")
    @Expose
    private String prefcode;
    @SerializedName("zipcode")
    @Expose
    private String zipcode;

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getKana1() {
        return kana1;
    }

    public void setKana1(String kana1) {
        this.kana1 = kana1;
    }

    public String getKana2() {
        return kana2;
    }

    public void setKana2(String kana2) {
        this.kana2 = kana2;
    }

    public String getKana3() {
        return kana3;
    }

    public void setKana3(String kana3) {
        this.kana3 = kana3;
    }

    public String getPrefcode() {
        return prefcode;
    }

    public void setPrefcode(String prefcode) {
        this.prefcode = prefcode;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
