package com.sparecode.vipul.onlynow.webservice;

import android.support.v4.util.Pair;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user_1 on 10/14/2016.
 */

public class ReqestParameter {

    //email,password
    public JSONObject toLogin(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.LOGIN);
            object.put("email", vars[0]);
            object.put("password", vars[1]);
            object.put("device_id",vars[2]);
            object.put("device_type",vars[3]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toMyShopDetails(String...vars){
        JSONObject object = new JSONObject();

        try {
            object.put("method",RequestApi.GETCLIENTSHOP);
            object.put("user_id",vars[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }
    public JSONObject toClientLiveCoupon(String...vars){
        JSONObject object = new JSONObject();

        try {
            object.put("method",RequestApi.GETCLIENTLIVECOUPONS);
            object.put("user_id",vars[0]);
            object.put("page",vars[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toClientPastCoupon(String...vars){
        JSONObject object = new JSONObject();
        try {
            object.put("method",RequestApi.GETCLIENTPASTCOUPONS);
            object.put("user_id",vars[0]);
            object.put("page",vars[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toClientDraftCoupon(String...vars){
        JSONObject object = new JSONObject();
        try {
            object.put("method",RequestApi.GETCLIENTDRAFTCOUPONS);
            object.put("user_id",vars[0]);
            object.put("page",vars[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toClientReview(String...vars){
        JSONObject object = new JSONObject();
        try {
            object.put("method",RequestApi.GETCLIENTREVIEW);
            object.put("shop_id",vars[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toClientAnalytics(String...vars){
        JSONObject object = new JSONObject();
        try {
            object.put("method",RequestApi.GETCLIENTANALYTICS);
            object.put("user_id",vars[0]);
            object.put("start_date",vars[1]);
            object.put("end_date",vars[2]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
    public JSONObject toCategoryList(int page) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_all_categories");
            jsonObject.put("page", "" + page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toMyProduct(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.MYPRODUCT);
            object.put("vendor_id", vars[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toProductDetails(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.PRODUCT_DETAIL);
            object.put("user_id", vars[0]);
            object.put("product_id", vars[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toProductDelete(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.PRODUCT_DELETE);
            object.put("vendor_id", vars[0]);
            object.put("product_id", vars[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }


    public JSONObject toForgotPassword(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.FORGOT_PASSWORD);
            object.put("email", vars[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toSearchProduct(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.SEARCH_PRODUCT);
            object.put("user_id", vars[0]);
            object.put("page", vars[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    //fname,lname,email,password,phone_no,licence_image,licence_no
    public List<Pair<String, String>> toVendorRegistration(String... vars) {
        List<Pair<String, String>> pairList = new ArrayList<>();
        pairList.add(new Pair<>("fname", vars[0]));
        pairList.add(new Pair<>("lname", vars[1]));
        pairList.add(new Pair<>("email", vars[2]));
        pairList.add(new Pair<>("password", vars[3]));
        pairList.add(new Pair<>("phone_no", vars[4]));
        pairList.add(new Pair<>("licence_no", vars[5]));
        return pairList;
    }

    //fname,lname,email,password,phone_no
    public List<Pair<String, String>> toCustomerRegistration(String... vars) {
        List<Pair<String, String>> pairList = new ArrayList<>();
        pairList.add(new Pair<>("fname", vars[0]));
        pairList.add(new Pair<>("lname", vars[1]));
        pairList.add(new Pair<>("email", vars[2]));
        pairList.add(new Pair<>("password", vars[3]));
        pairList.add(new Pair<>("phone_no", vars[4]));
        return pairList;
    }

    //vendor_id,name,description,qty,price,shipping_charge
    public List<Pair<String, String>> toAddProduct(String... vars) {
        List<Pair<String, String>> pairList = new ArrayList<>();
        pairList.add(new Pair<>("vendor_id", vars[0]));
        pairList.add(new Pair<>("name", vars[1]));
        pairList.add(new Pair<>("description", vars[2]));
        pairList.add(new Pair<>("qty", vars[3]));
        pairList.add(new Pair<>("price", vars[4]));
        pairList.add(new Pair<>("shipping_charge", vars[5]));

        return pairList;
    }


    //vendor_id,name,description,qty,price,shipping_charge
    public List<Pair<String, String>> toUpdateProduct(String... vars) {
        List<Pair<String, String>> pairList = new ArrayList<>();
        pairList.add(new Pair<>("product_id", vars[0]));
        pairList.add(new Pair<>("vendor_id", vars[1]));
        pairList.add(new Pair<>("name", vars[2]));
        pairList.add(new Pair<>("description", vars[3]));
        pairList.add(new Pair<>("qty", vars[4]));
        pairList.add(new Pair<>("price", vars[5]));
        pairList.add(new Pair<>("shipping_charge", vars[6]));

        return pairList;
    }
    public JSONObject toReviewRatings(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.REVIEWS_RATINGS);
            object.put("user_id", vars[0]);
            object.put("product_id", vars[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
    public JSONObject toProductHistory(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.PRODUCT_ORDER);
            object.put("user_id", vars[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
    public JSONObject toProductLike(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.PRODUCT_DISLIKE);
            object.put("user_id", vars[0]);
            object.put("review_id", vars[1]);
            object.put("action", vars[2]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
    public JSONObject toStartChat(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.START_CHAT);
            object.put("user_id", vars[0]);
            object.put("vendor_id", vars[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public List<Pair<String, String>> toSendChat(String... vars) {
        List<Pair<String, String>> pairList = new ArrayList<>();
        pairList.add(new Pair<>("user_id", vars[0]));
        pairList.add(new Pair<>("chat_id", vars[1]));
        pairList.add(new Pair<>("message", vars[2]));

        return pairList;
    }
    public JSONObject toUpdataeDevice(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.UPDATE_DETAILS);
            object.put("user_id", vars[0]);
            object.put("device_id", vars[1]);
            object.put("device_type", vars[2]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toGetConversation(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.HISTORY_CHAT);
            object.put("chat_id", vars[0]);
            object.put("page",vars[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }


}
