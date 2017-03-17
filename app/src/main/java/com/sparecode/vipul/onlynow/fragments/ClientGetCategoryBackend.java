package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.ClientGetCategoryWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 17/3/17.
 */

public class ClientGetCategoryBackend {

    private Context context;
    GetClientCategoryDataProvider getClientCategoryDataProvider;

    public ClientGetCategoryBackend(Context context, GetClientCategoryDataProvider getClientCategoryDataProvider) {
        this.context = context;
        this.getClientCategoryDataProvider = getClientCategoryDataProvider;
        call();
    }

    private void call()
    {
        new GetRequest<ClientGetCategoryWrapper>().toGetRequest(context, RequestApi.CLIENTDATA, new ReqestParameter().toClientGetCategory(), ClientGetCategoryWrapper.class, new OnResponse<ClientGetCategoryWrapper>() {
            @Override
            public void onSuccess(ClientGetCategoryWrapper clientGetCategoryWrapper) {

                if (clientGetCategoryWrapper.getStatus() == 0)
                {
                    getClientCategoryDataProvider.onFailure(clientGetCategoryWrapper.getMessage());
                }
                else
                {
                    getClientCategoryDataProvider.onSuccess(clientGetCategoryWrapper);
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    public interface GetClientCategoryDataProvider
    {
        void onSuccess(ClientGetCategoryWrapper clientGetCategoryWrapper);

        void onFailure(String msg);
    }
}
