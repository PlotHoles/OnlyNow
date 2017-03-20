package com.sparecode.vipul.onlynow.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hitesh on 14/3/17.
 */

public class ReviewData {

    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("stars_5")
    @Expose
    private String stars5;
    @SerializedName("stars_4")
    @Expose
    private String stars4;
    @SerializedName("stars_3")
    @Expose
    private String stars3;
    @SerializedName("stars_2")
    @Expose
    private String stars2;
    @SerializedName("stars_1")
    @Expose
    private String stars1;
    @SerializedName("reviews")
    @Expose
    private List<UserReview> reviews = null;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStars5() {
        return stars5;
    }

    public void setStars5(String stars5) {
        this.stars5 = stars5;
    }

    public String getStars4() {
        return stars4;
    }

    public void setStars4(String stars4) {
        this.stars4 = stars4;
    }

    public String getStars3() {
        return stars3;
    }

    public void setStars3(String stars3) {
        this.stars3 = stars3;
    }

    public String getStars2() {
        return stars2;
    }

    public void setStars2(String stars2) {
        this.stars2 = stars2;
    }

    public String getStars1() {
        return stars1;
    }

    public void setStars1(String stars1) {
        this.stars1 = stars1;
    }

    public List<UserReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<UserReview> reviews) {
        this.reviews = reviews;
    }
}
