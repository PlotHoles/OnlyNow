package com.sparecode.vipul.onlynow.webservice;

/**
 * Created by user_1 on 10/14/2016.
 */

public interface RequestApi {

    String USERDATA = "userData";
    String LOGIN = "login";
    String MYPRODUCT = "MyProduct";
    String SEARCH_PRODUCT = "search_product";
    String FORGOT_PASSWORD = "forgotPassword";
    String PRODUCT_DETAIL = "product_detail";
    String PRODUCT_DELETE = "DeleteProduct";
    String PRODUCT_ORDER = "OrderHistory";
    String REVIEWS_RATINGS = "Reviews_ratings";
    String PRODUCT_DISLIKE = "Review_likeDislike";
    String START_CHAT = "startChat";
    String UPDATE_DETAILS = "UpdateDeviceDetails";
    String HISTORY_CHAT = "getConversation";
    String GETCLIENTSHOP = "get_my_shop";
    String GETCLIENTLIVECOUPONS = "get_my_live_coupons";
    String  GETCLIENTPASTCOUPONS = "get_my_past_coupons";
    String GETCLIENTDRAFTCOUPONS = "get_my_draft_coupons";
    String GETCLIENTREVIEW = "get_shop_reviews";
    String GETCLIENTANALYTICS = "get_analitics";
    //String BASEURL = "http://192.168.2.16/projects/wilo/api/";
    String BASEURL = "http://192.168.2.7/projects/onlynow/api/";
    String SERVICE = BASEURL + "services.php?data=";
    String CLIENTSERVICE = BASEURL + "client/?data=";
    String CLIENTDATA = BASEURL + "?data=";
    String COMMONSERVICE = BASEURL + "?data=";
    String PRODUCT_ADD = BASEURL + "product_add.php";
    String PRODUCT_EDIT = BASEURL + "product_edit.php";
    String SIGNUP_CUSTOMER = BASEURL + "signup_customer.php";
    String SIGNUP_VENDOR = BASEURL + "signup_vendor.php";
    String UPDATE_CUSTOMER = BASEURL + "edit_profile_customer.php";
    String UPDATE_VENDOR = BASEURL + "edit_profile_vendor.php";
    String PRODUCT_REVIEW = BASEURL + "services.php?data=";
    String CUSTOMER_HISTORY = BASEURL + "services.php?data=";
    String PRODUCT_LIKE = BASEURL + "services.php?data=";
    String START_NEW_CHAT = BASEURL + "services.php?data=";
    String SEND_CHAT = BASEURL + "send_chat.php";
    String UPDATE_DEVICE = BASEURL +"services.php?data=";
    String CHAT_HISTORY = BASEURL + "services.php?data=";
}
