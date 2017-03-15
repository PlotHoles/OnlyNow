package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.ClientAnalyticsWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 1/3/17.
 */

public class ClientAnalyticsClientBackend {

    private Context context;
    private String userId;
    private String startDate;
    private String endDate;
    ClientAnalyticsResultProvider clientAnalyticsResultProvider;

    public ClientAnalyticsClientBackend(Context context, String userId, String startDate, String endDate, ClientAnalyticsResultProvider clientAnalyticsResultProvider) {
        this.context = context;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.clientAnalyticsResultProvider = clientAnalyticsResultProvider;
        call();
    }


    private void call()
    {
        new GetRequest<ClientAnalyticsWrapper>().toGetRequest(context, RequestApi.CLIENTSERVICE, new ReqestParameter().toClientAnalytics(userId,startDate,endDate), ClientAnalyticsWrapper.class, new OnResponse<ClientAnalyticsWrapper>() {
            @Override
            public void onSuccess(ClientAnalyticsWrapper clientAnalyticsWrapper) {

                if (clientAnalyticsWrapper.getStatus() == 0){
                    clientAnalyticsResultProvider.onLoginfailure(clientAnalyticsWrapper.getMessage());
                }
                else
                {
                    clientAnalyticsResultProvider.onSuccessfullLogin(clientAnalyticsWrapper);
                }
            }

            @Override
            public void onError() {
                clientAnalyticsResultProvider.onLoginfailure("Bad Response from Server");
            }
        });
    }


    public interface ClientAnalyticsResultProvider{

        void onSuccessfullLogin(ClientAnalyticsWrapper clientAnalyticsWrapper);

        void onLoginfailure(String msg);
    }
}
