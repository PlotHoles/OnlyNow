package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.ClientReviewWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 1/3/17.
 */

public class ClientReviewBackend {

    private String shopId;
    private Context context;
    private ClientReviewResultProvider clientReviewResultProvider;

    public ClientReviewBackend(String shopId, Context context,ClientReviewResultProvider clientReviewResultProvider) {
        this.shopId = shopId;
        this.context = context;
        this.clientReviewResultProvider = clientReviewResultProvider;
        call();;
    }

    private void call()
    {

        new GetRequest<ClientReviewWrapper>().toGetRequest(context, RequestApi.CLIENTDATA, new ReqestParameter().toClientReview(shopId), ClientReviewWrapper.class, new OnResponse<ClientReviewWrapper>() {
            @Override
            public void onSuccess(ClientReviewWrapper clientReviewWrapper) {
                if (clientReviewWrapper.getStatus() == 0)
                {
                    clientReviewResultProvider.onLoginfailure(clientReviewWrapper.getMessage());
                }
                else
                {
                    clientReviewResultProvider.onSuccessfullLogin(clientReviewWrapper);
                }
            }

            @Override
            public void onError() {
                clientReviewResultProvider.onLoginfailure("Bad Response from Server");
            }
        });
    }

    public interface ClientReviewResultProvider{

        void onSuccessfullLogin(ClientReviewWrapper clientReviewWrapper);

        void onLoginfailure(String msg);
    }
}
