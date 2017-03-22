package com.sparecode.vipul.onlynow.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vipul on 21/3/17.
 */

public class SearchResultData {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("instruction")
    @Expose
    private String instruction;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("is_active")
    @Expose
    private String isActive;
    @SerializedName("total_points")
    @Expose
    private String totalPoints;
    @SerializedName("total_views")
    @Expose
    private String totalViews;
    @SerializedName("total_saved")
    @Expose
    private String totalSaved;
    @SerializedName("is_deleted")
    @Expose
    private String isDeleted;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("shop_name")
    @Expose
    private String shopName;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("shop_id")
    @Expose
    private String shopId;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("imageURL")
    @Expose
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
