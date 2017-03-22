package com.sparecode.vipul.onlynow.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vipul on 21/3/17.
 */

public class SearchCategoryData {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("imageURL")
    @Expose
    private String imageURL;
    @SerializedName("iconURL")
    @Expose
    private String iconURL;
    @SerializedName("pink_iconURL")
    @Expose
    private String pinkIconURL;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    public String getPinkIconURL() {
        return pinkIconURL;
    }

    public void setPinkIconURL(String pinkIconURL) {
        this.pinkIconURL = pinkIconURL;
    }
}
