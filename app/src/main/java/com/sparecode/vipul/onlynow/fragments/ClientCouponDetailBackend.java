package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.ClientGetCouponWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 4/3/17.
 */

public class ClientCouponDetailBackend {

    String id;
    Context context;
    ClientCouponDetailResultProvider clientCouponDetailResultProvider;

    public ClientCouponDetailBackend(String id, Context context,ClientCouponDetailResultProvider clientCouponDetailResultProvider) {
        this.id = id;
        this.context = context;
        this.clientCouponDetailResultProvider = clientCouponDetailResultProvider;
        call();
    }

    private void call()
    {
        new GetRequest<ClientGetCouponWrapper>().toGetRequest(context, RequestApi.CLIENTSERVICE, new ReqestParameter().toGetCoupon(id), ClientGetCouponWrapper.class, new OnResponse<ClientGetCouponWrapper>() {
            @Override
            public void onSuccess(ClientGetCouponWrapper clientGetCouponWrapper) {
                if (clientGetCouponWrapper.getStatus() == 0)
                {
                    clientCouponDetailResultProvider.onLoginFailure(clientGetCouponWrapper.getMessage());
                }
                else
                {
                    clientCouponDetailResultProvider.onSuccessfullLogin(clientGetCouponWrapper);
                }
            }

            @Override
            public void onError() {

            }
        });
    }
    public interface ClientCouponDetailResultProvider
    {
        void onSuccessfullLogin(ClientGetCouponWrapper clientGetCouponWrapper);

        void onLoginFailure(String msg);
    }
}
