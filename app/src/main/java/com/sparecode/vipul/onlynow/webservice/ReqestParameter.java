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

    public JSONObject toSignup(String... vars) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "signup");
            jsonObject.put("fname", "" + vars[0]);
            jsonObject.put("lname", "" + vars[1]);
            jsonObject.put("email", "" + vars[2]);
            jsonObject.put("password", "" + vars[3]);
            jsonObject.put("gender", "" + vars[4]);
            jsonObject.put("birthday", "" + vars[5]);
            jsonObject.put("lat", "" + vars[6]);
            jsonObject.put("long", "" + vars[7]);
            jsonObject.put("recieve_email", "" + vars[8]);
            jsonObject.put("device_type", "" + vars[9]);
            jsonObject.put("device_id", "" + vars[10]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toFbSignup(String... vars) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "signup");
            jsonObject.put("fname", "" + vars[0]);
            jsonObject.put("lname", "" + vars[1]);
            jsonObject.put("email", "" + vars[2]);
            jsonObject.put("password", "" + vars[3]);
            jsonObject.put("gender", "" + vars[4]);
            jsonObject.put("birthday", "" + vars[5]);
            jsonObject.put("lat", "" + vars[6]);
            jsonObject.put("long", "" + vars[7]);
            jsonObject.put("recieve_email", "" + vars[8]);
            jsonObject.put("device_type", "" + vars[9]);
            jsonObject.put("device_id", "" + vars[10]);
            jsonObject.put("fb_id", "" + vars[11]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toGetAllAreas(int page) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_all_areas");
            jsonObject.put("page", "" + page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toAddActiveArea(String userId, String areaId, String lat, String lng) {
        //add_active_area
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "add_active_area");
            jsonObject.put("user_id", "" + userId);
            jsonObject.put("area_id", "" + areaId);
            jsonObject.put("lat", "" + lat);
            jsonObject.put("long", "" + lng);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toRemoveActivrArea(String areaId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "remove_active_area");
            jsonObject.put("a_id", "" + areaId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toAddFavCategory(String userId, String catId) {
        //add_fav_category
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "add_fav_category");
            jsonObject.put("user_id", "" + userId);
            jsonObject.put("cat_id", "" + catId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toRemoveFavCategory(String catId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "remove_fav_category");
            jsonObject.put("cat_id", "" + catId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toUserLogin(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.LOGIN);
            object.put("email", vars[0]);
            object.put("password", vars[1]);
            object.put("lat", vars[2]);
            object.put("long", vars[3]);
            object.put("device_type", vars[4]);
            object.put("device_id", vars[5]);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
    //login_with_fb
    public JSONObject toFbLogin(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.FBLOGIN);
            object.put("email", vars[0]);
            //object.put("password", vars[1]);
            object.put("lat", vars[2]);
            object.put("long", vars[3]);
            object.put("device_type", vars[4]);
            object.put("device_id", vars[5]);
            object.put("fb_id", vars[6]);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
    public JSONObject toGetFavCategory(String userId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_fav_categories");
            jsonObject.put("user_id", "" + userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toGetCoupnsByCategory(String userId, String catId,String sort_by) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_coupons_by_category");
            jsonObject.put("user_id", "" + userId);
            jsonObject.put("cat_id", "" + catId);
            jsonObject.put("sort_by", "" + sort_by);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toGetCouponById(String couponId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_coupon_by_id");
            jsonObject.put("id", "" + couponId);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toGetReviewShop(String shopId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_shop_reviews");
            jsonObject.put("shop_id", "" + shopId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toGetFavShop(String shopId,String userId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "add_fav_shop");
            jsonObject.put("shop_id", "" + shopId);
            jsonObject.put("user_id", "" + userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toGetShop(String shopId,String userId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_shop");
            jsonObject.put("shop_id", "" + shopId);
            jsonObject.put("user_id", "" + userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toGetRemoveFavShop(String Id) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "remove_shop_from_list");
            jsonObject.put("id", "" + Id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toGetNotification(String userId, int page) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_my_notifications");
            jsonObject.put("user_id", "" + userId);
            jsonObject.put("page", "" + page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toGetMyListSaved(String userId, int page) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_saved_coupons");
            jsonObject.put("user_id", "" + userId);
            jsonObject.put("page", "" + page);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toGetUserShops(String userId, int page, int type) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_user_shops");
            jsonObject.put("user_id", "" + userId);
            jsonObject.put("page", "" + page);
            jsonObject.put("type", "" + type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toGetReviewed(String userId, int page) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_reviewed_shops");
            jsonObject.put("user_id", "" + userId);
            jsonObject.put("page", "" + page);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toGetLocation(String...vars) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", RequestApi.GET_LOCATION);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toSearchCategory(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.GET_ALL_CATEGORIES);
            object.put("get_all", vars[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
    public JSONObject toSearchPopularQueries(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.GET_POPULAR_SEARCHES);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
    public JSONObject toSearchResult(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.SEARCH);
            object.put("keyword", vars[0]);
            object.put("lat", vars[1]);
            object.put("long", vars[2]);
            object.put("cat_id", vars[3]);
            object.put("sort_by", vars[4]);
            object.put("page", vars[5]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
    public JSONObject toGetSelectFavCategory(String UserId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_categories");
            jsonObject.put("user_id", "" + UserId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toGetChangePassword(String UserId,String OldPassword,String NewPassword) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "change_password");
            jsonObject.put("user_id", "" + UserId);
            jsonObject.put("old_password", "" + OldPassword);
            jsonObject.put("new_password", "" + NewPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toGetUpdateProfile(String UserId,String fname,String lname) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "update_profile");
            jsonObject.put("user_id", "" + UserId);
            jsonObject.put("fname", "" + fname);
            jsonObject.put("lname", "" + lname);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toGetProfileUpdate(String UserId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "profile");
            jsonObject.put("user_id", "" + UserId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public JSONObject toUserChangeLocation(String...vars) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("method", "get_all_areas");
            jsonObject.put("user_id", vars[0]);
            jsonObject.put("page", vars[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
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
    public JSONObject toGetCoupon(String...vars){
        JSONObject object = new JSONObject();
        try {
            object.put("method",RequestApi.GETCLIENTCOUPON);
            object.put("id",vars[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
    public JSONObject toUpdateShop(String...vars){
        JSONObject object = new JSONObject();
        try {
            object.put("method",RequestApi.UPDATESHOP);
            object.put("shop_id",vars[0]);
            object.put("phone",vars[1]);
            object.put("web",vars[2]);
            object.put("details",vars[3]);
            object.put("area",vars[4]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
    public JSONObject toUpdateCoupon(String...vars){
        JSONObject object = new JSONObject();
        try {
            object.put("method",RequestApi.UPDATECOUPON);
            object.put("coupon_id",vars[0]);
            object.put("instruction",vars[1]);
            object.put("description",vars[2]);
            object.put("end_date",vars[3]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
    public JSONObject toClientSignup(String...vars){
        JSONObject object = new JSONObject();
        try {
            object.put("method",RequestApi.CLIENTSIGNUP);
            object.put("fname",vars[0]);
            object.put("lname",vars[1]);
            object.put("email",vars[2]);
            object.put("password",vars[3]);
            object.put("lat",vars[4]);
            object.put("long",vars[5]);
            object.put("client_name",vars[6]);
            object.put("cat_id",vars[7]);
            object.put("area",vars[8]);
            object.put("zip_code",vars[9]);
            object.put("prefecture",vars[10]);
            object.put("city",vars[11]);
            object.put("street",vars[12]);
            object.put("build_name",vars[13]);
            object.put("phone",vars[14]);
            object.put("website",vars[15]);
            object.put("device_type",vars[16]);
            object.put("device_id",vars[17]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toUpdateLocation(String...vars)
    {
        JSONObject object = new JSONObject();
        try {
            object.put("method",RequestApi.UPDATESHOP);
            object.put("shop_id",vars[0]);
            object.put("zip_code",vars[1]);
            object.put("prefecture",vars[2]);
            object.put("city",vars[3]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
    public JSONObject toUpdateCategory(String...vars)
    {
        JSONObject object = new JSONObject();
        try {
            object.put("method",RequestApi.UPDATESHOP);
            object.put("shop_id",vars[0]);
            object.put("cat_id",vars[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
    public JSONObject toCancelDeal(String...vars)
    {
        JSONObject object = new JSONObject();
        try {
            object.put("method",RequestApi.CANCELDEAL);
            object.put("user_id",vars[0]);
            object.put("comment",vars[1]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
    public JSONObject toLogout(String...vars)
    {
        JSONObject object = new JSONObject();
        try {
            object.put("method",RequestApi.LOGOUT);
            object.put("user_id",vars[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }
    public JSONObject toClientGetCategory()
    {
        JSONObject object = new JSONObject();
        try {
            object.put("method",RequestApi.CLIENTGETCATEGORY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public List<Pair<String, String>> toAddCLientCoupon(String... vars) {
        List<Pair<String, String>> pairList = new ArrayList<>();
        pairList.add(new Pair<>("client_id", vars[0]));
        pairList.add(new Pair<>("cat_id", vars[1]));
        pairList.add(new Pair<>("name", vars[2]));
        pairList.add(new Pair<>("instruction", vars[3]));
        pairList.add(new Pair<>("description", vars[4]));
        pairList.add(new Pair<>("start_date", vars[5]));
        pairList.add(new Pair<>("end_date", vars[5]));

        return pairList;
    }

    public List<Pair<String, String>> toUpdateProfilePicture(String... vars) {
        List<Pair<String, String>> pairList = new ArrayList<>();
        pairList.add(new Pair<>("user_id", vars[0]));
        return pairList;
    }

    public JSONObject toUpdateProfile(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.UPDATEPROFILE);
            object.put("user_id", vars[0]);
            object.put("fname", vars[1]);
            object.put("lname", vars[2]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toChangePassword(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.CHANGEPASSWORD);
            object.put("user_id", vars[0]);
            object.put("old_password", vars[1]);
            object.put("new_password", vars[2]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public JSONObject toMAPCOUPONS(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.MAP_COUPONS);
            object.put("lat", vars[0]);
            object.put("long", vars[1]);
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


   /* public JSONObject toForgotPassword(String... vars) {
        JSONObject object = new JSONObject();
        try {
            object.put("method", RequestApi.FORGOT_PASSWORD);
            object.put("email", vars[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }*/

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
