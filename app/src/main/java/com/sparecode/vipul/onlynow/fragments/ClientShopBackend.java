package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.ClientShopWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 27/2/17.
 */

public class ClientShopBackend {

    private String userId;
    private Context context;
    private ClientShopResultProvider clientShopResultProvider;

    public ClientShopBackend(Context context, String userId, ClientShopResultProvider clientShopResultProvider) {
        this.context = context;
        this.userId = userId;
        this.clientShopResultProvider = clientShopResultProvider;
        call();
    }

    private void call()
    {
        new GetRequest<ClientShopWrapper>().toGetRequest(context, RequestApi.CLIENTSERVICE, new ReqestParameter().toMyShopDetails(userId), ClientShopWrapper.class, new OnResponse<ClientShopWrapper>() {
            @Override
            public void onSuccess(ClientShopWrapper clientShopWrapper) {

                if (clientShopWrapper.getStatus()==0)
                {
                    clientShopResultProvider.onLoginFailure(clientShopWrapper.getMessage());
                }
                else
                {
                    clientShopResultProvider.onSuccessfullLogin(clientShopWrapper);
                }
            }

            @Override
            public void onError() {
                clientShopResultProvider.onLoginFailure("Bad Response From Server");
            }
        });
    }
    public interface ClientShopResultProvider{

        void onSuccessfullLogin(ClientShopWrapper clientShopWrapper);

        void onLoginFailure(String msg);
    }
}
