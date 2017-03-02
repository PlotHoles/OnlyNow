package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.ClientPastWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 28/2/17.
 */

public class ClientPastBackend {

    private Context context;
    private String userId;
    ClientPastResultProvider clientPastResultProvider;

    public ClientPastBackend(Context context,String userId,ClientPastResultProvider clientPastResultProvider) {
        this.context = context;
        this.userId = userId;
        this.clientPastResultProvider = clientPastResultProvider;

    }

    private void call(int page)
    {
        new GetRequest<ClientPastWrapper>().toGetRequest(context, RequestApi.CLIENTSERVICE, new ReqestParameter().toClientPastCoupon(userId, String.valueOf(page)), ClientPastWrapper.class, new OnResponse<ClientPastWrapper>() {
            @Override
            public void onSuccess(ClientPastWrapper clientPastWrapper) {

                if (clientPastWrapper.getStatus()==0)
                {
                    clientPastResultProvider.onLoginFailure(clientPastWrapper.getMessage());
                }
                else
                {
                    clientPastResultProvider.onSuccessfullLogin(clientPastWrapper);
                }
            }

            @Override
            public void onError() {

            }
        });
    }
    public void callPagination(int page) {
        call(page);
    }

    public interface ClientPastResultProvider{

        void onSuccessfullLogin(ClientPastWrapper clientPastWrapper);

        void onLoginFailure(String msg);
    }
}
