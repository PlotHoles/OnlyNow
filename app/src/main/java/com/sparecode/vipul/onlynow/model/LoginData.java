package com.sparecode.vipul.onlynow.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vipul on 25/2/17.
 */

public class LoginData {

    @SerializedName("id")
    private String id;
    @SerializedName("fb_id")
    private Object fbId;
    @SerializedName("fname")
    private String fname;
    @SerializedName("lname")
    private String lname;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("image")
    private String image;
    @SerializedName("gender")
    private String gender;
    @SerializedName("user_type")
    private String userType;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("lat")
    private String lat;
    @SerializedName("long")
    private String _long;
    @SerializedName("device_type")
    private String deviceType;
    @SerializedName("device_id")
    private String deviceId;
    @SerializedName("is_confirmed")
    private String isConfirmed;
    @SerializedName("recieve_email")
    private String recieveEmail;
    @SerializedName("recieve_push")
    private String recievePush;
    @SerializedName("is_deleted")
    private String isDeleted;
    @SerializedName("is_active")
    private String isActive;
    @SerializedName("confirm_code")
    private String confirmCode;
    @SerializedName("date")
    private String date;
    @SerializedName("shop_id")
    private String shopId;
    @SerializedName("imageURL")
    private String imageURL;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getFbId() {
        return fbId;
    }

    public void setFbId(Object fbId) {
        this.fbId = fbId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLong() {
        return _long;
    }

    public void setLong(String _long) {
        this._long = _long;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(String isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public String getRecieveEmail() {
        return recieveEmail;
    }

    public void setRecieveEmail(String recieveEmail) {
        this.recieveEmail = recieveEmail;
    }

    public String getRecievePush() {
        return recievePush;
    }

    public void setRecievePush(String recievePush) {
        this.recievePush = recievePush;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getConfirmCode() {
        return confirmCode;
    }

    public void setConfirmCode(String confirmCode) {
        this.confirmCode = confirmCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
