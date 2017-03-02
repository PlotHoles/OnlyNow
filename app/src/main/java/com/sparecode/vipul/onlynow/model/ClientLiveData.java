package com.sparecode.vipul.onlynow.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vipul on 27/2/17.
 */

public class ClientLiveData {

    @SerializedName("id")
    private String id;
    @SerializedName("cat_id")
    private String catId;
    @SerializedName("client_id")
    private String clientId;
    @SerializedName("name")
    private String name;
    @SerializedName("instruction")
    private String instruction;
    @SerializedName("description")
    private String description;
    @SerializedName("is_active")
    private String isActive;
    @SerializedName("total_points")
    private String totalPoints;
    @SerializedName("total_views")
    private String totalViews;
    @SerializedName("total_saved")
    private String totalSaved;
    @SerializedName("is_deleted")
    private String isDeleted;
    @SerializedName("end_date")
    private String endDate;
    @SerializedName("date")
    private String date;
    @SerializedName("shop_name")
    private String shopName;
    @SerializedName("area")
    private String area;
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

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(String totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(String totalViews) {
        this.totalViews = totalViews;
    }

    public String getTotalSaved() {
        return totalSaved;
    }

    public void setTotalSaved(String totalSaved) {
        this.totalSaved = totalSaved;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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
