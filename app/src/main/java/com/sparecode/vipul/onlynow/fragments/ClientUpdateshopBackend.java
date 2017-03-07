package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.ClientUpdateshopWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 7/3/17.
 */

public class ClientUpdateshopBackend {

    private Context context;
    private String shopid,phone,web,details,area;
    private ClientUpdateShopResultProvider clientUpdateShopResultProvider;

    public ClientUpdateshopBackend(Context context,String shopid, String phone, String web, String details, String area,ClientUpdateShopResultProvider clientUpdateShopResultProvider) {
        this.context = context;
        this.shopid = shopid;
        this.phone = phone;
        this.web = web;
        this.details = details;
        this.area = area;
        this.clientUpdateShopResultProvider = clientUpdateShopResultProvider;
        call();
    }

    private void call()
    {
        new GetRequest<ClientUpdateshopWrapper>().toGetRequest(context, RequestApi.CLIENTSERVICE, new ReqestParameter().toUpdateShop(shopid,phone, web, details, area), ClientUpdateshopWrapper.class, new OnResponse<ClientUpdateshopWrapper>() {
            @Override
            public void onSuccess(ClientUpdateshopWrapper clientUpdateshopWrapper) {

                if (clientUpdateshopWrapper.getStatus() == 0)
                {
                    clientUpdateShopResultProvider.onLoginFailure(clientUpdateshopWrapper.getMessage());
                }
                else
                {
                    clientUpdateShopResultProvider.onSuccessfullLogin(clientUpdateshopWrapper);
                }
            }

            @Override
            public void onError() {
                    clientUpdateShopResultProvider.onLoginFailure("Bad response from Server");
            }
        });
    }
    public interface ClientUpdateShopResultProvider{

        void onSuccessfullLogin(ClientUpdateshopWrapper clientUpdateshopWrapper);

        void onLoginFailure(String msg);
    }
}
