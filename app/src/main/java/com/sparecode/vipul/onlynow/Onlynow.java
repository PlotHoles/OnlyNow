package com.sparecode.vipul.onlynow;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

import com.sparecode.vipul.onlynow.util.Prefs;
import com.sparecode.vipul.onlynow.webservice.RequestBuilder;

/**
 * Created by vipul on 25/2/17.
 */

public class Onlynow extends Application{

    private static RequestBuilder requestBuilder;

    private static Context context;

    private String first_name,lname,cname,area,zipcode,prefecture,cityname,streetname,buildname,pnumber,emailaddress,cemailaddress,password,wurl,cat_id,longitude,latitude;
    @Override
    public void onCreate() {
        super.onCreate();
        requestBuilder = new RequestBuilder();

        Onlynow.context = getApplicationContext();

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public String getBuildname() {
        return buildname;
    }

    public void setBuildname(String buildname) {
        this.buildname = buildname;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getCemailaddress() {
        return cemailaddress;
    }

    public void setCemailaddress(String cemailaddress) {
        this.cemailaddress = cemailaddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWurl() {
        return wurl;
    }

    public void setWurl(String wurl) {
        this.wurl = wurl;
    }

    public static Context getContext() {
        return context;
    }

    public static RequestBuilder getRequestBuilder() {
        return requestBuilder;
    }
}
