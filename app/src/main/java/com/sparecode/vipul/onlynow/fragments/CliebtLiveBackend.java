package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.ClientLiveWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 27/2/17.
 */

public class CliebtLiveBackend {

    private String userId;
    private Context context;
    private ClientLiveResultProvider clientLiveResultProvider;

    public CliebtLiveBackend(Context context,String userId,ClientLiveResultProvider clientLiveResultProvider) {
        this.context = context;
        this.userId = userId;
        this.clientLiveResultProvider = clientLiveResultProvider;

    }

    private void call(int page)
    {

        new GetRequest<ClientLiveWrapper>().toGetRequest(context, RequestApi.CLIENTSERVICE, new ReqestParameter().toClientLiveCoupon(userId, String.valueOf(page)), ClientLiveWrapper.class, new OnResponse<ClientLiveWrapper>() {
            @Override
            public void onSuccess(ClientLiveWrapper clientLiveWrapper) {

                if (clientLiveWrapper.getStatus()==0)
                {
                    clientLiveResultProvider.onLoginFailure(clientLiveWrapper.getMessage());
                }
                else
                {
                    clientLiveResultProvider.onSuccessfullLogin(clientLiveWrapper);
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
    public interface ClientLiveResultProvider{

        void onSuccessfullLogin(ClientLiveWrapper clientLiveWrapper);

        void onLoginFailure(String msg);
    }
}
