package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.ClientDraftWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 28/2/17.
 */

public class ClientDraftBackend {

    private Context context;
    private String userId;
    ClientDraftResultProvider clientDraftResultProvider;

    public ClientDraftBackend(Context context,String userId,ClientDraftResultProvider clientDraftResultProvider) {
        this.context = context;
        this.userId = userId;
        this.clientDraftResultProvider = clientDraftResultProvider;

    }

    private void call(int page)
    {
        new GetRequest<ClientDraftWrapper>().toGetRequest(context, RequestApi.CLIENTSERVICE, new ReqestParameter().toClientDraftCoupon(userId, String.valueOf(page)), ClientDraftWrapper.class, new OnResponse<ClientDraftWrapper>() {
            @Override
            public void onSuccess(ClientDraftWrapper clientDraftWrapper) {

                if (clientDraftWrapper.getStatus() == 0)
                {
                    clientDraftResultProvider.onLoginFailure(clientDraftWrapper.getMessage());
                }
                else
                {
                    clientDraftResultProvider.onSuccessfullLogin(clientDraftWrapper);
                }
            }

            @Override
            public void onError() {
                clientDraftResultProvider.onLoginFailure("Bad response from Server");
            }
        });
    }
    public void callPagination(int page) {
        call(page);
    }
    public interface ClientDraftResultProvider{

        void onSuccessfullLogin(ClientDraftWrapper clientDraftWrapper);

        void onLoginFailure(String msg);
    }
}
