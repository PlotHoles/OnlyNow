package com.sparecode.vipul.onlynow.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vipul on 17/3/17.
 */

public class ChangeProfilePictureData {
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("imageURL")
    @Expose
    private String imageURL;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
