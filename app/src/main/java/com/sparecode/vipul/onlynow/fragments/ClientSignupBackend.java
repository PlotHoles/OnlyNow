package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.LoginWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 9/3/17.
 */

public class ClientSignupBackend {


    private Context context;
    private String fname,lname,email,password,lat,longt,client_name,cat_id,area,zip_code,prefecture,city,street,build_name,phone,website,device_type,device_id;
    private ClientSignupResultProvider clientSignupResultProvider;


    public ClientSignupBackend(Context context, String fname, String lname, String email, String password, String lat, String longt, String client_name, String cat_id, String area, String zip_code, String prefecture, String city, String street, String build_name, String phone, String website, String device_type, String device_id, ClientSignupResultProvider clientSignupResultProvider) {
        this.context = context;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.lat = lat;
        this.longt = longt;
        this.client_name = client_name;
        this.cat_id = cat_id;
        this.area = area;
        this.zip_code = zip_code;
        this.prefecture = prefecture;
        this.city = city;
        this.street = street;
        this.build_name = build_name;
        this.phone = phone;
        this.website = website;
        this.device_type = device_type;
        this.device_id = device_id;
        this.clientSignupResultProvider = clientSignupResultProvider;
        call();
    }

    private void call()
    {
        new GetRequest<LoginWrapper>().toGetRequest(context, RequestApi.CLIENTSERVICE, new ReqestParameter().toClientSignup(fname, lname, email, password, lat, longt, client_name, cat_id, area, zip_code, prefecture, city, street, build_name, phone, website, device_type, device_id), LoginWrapper.class, new OnResponse<LoginWrapper>() {
            @Override
            public void onSuccess(LoginWrapper loginWrapper) {

                if (loginWrapper.getStatus() == 0)
                {
                    clientSignupResultProvider.onLoginFailure(loginWrapper.getMessage());
                }
                else
                {
                    clientSignupResultProvider.onSuccessfullLogin(loginWrapper);
                }
            }

            @Override
            public void onError() {
                clientSignupResultProvider.onLoginFailure("Bad response from server");
            }
        });
    }
    public interface ClientSignupResultProvider{

        void onSuccessfullLogin(LoginWrapper loginWrapper);

        void onLoginFailure(String msg);
    }
}
