package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.CancelDealWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 10/3/17.
 */

public class CancelDealBackend {

    private Context context;
    private String userId;
    private String comment;
    cancelDealResultProvider cancelDealResultProvider;

    public CancelDealBackend(Context context, String userId, String comment, CancelDealBackend.cancelDealResultProvider cancelDealResultProvider) {
        this.context = context;
        this.userId = userId;
        this.comment = comment;
        this.cancelDealResultProvider = cancelDealResultProvider;
        call();
    }

    private void call()
    {
        new GetRequest<CancelDealWrapper>().toGetRequest(context, RequestApi.CLIENTSERVICE, new ReqestParameter().toCancelDeal(userId, comment), CancelDealWrapper.class, new OnResponse<CancelDealWrapper>() {
            @Override
            public void onSuccess(CancelDealWrapper cancelDealWrapper) {

                if (cancelDealWrapper.getStatus() == 0)
                {
                    cancelDealResultProvider.onLoginFailure(cancelDealWrapper.getMessage());
                }
                else {
                    cancelDealResultProvider.onSuccessfullLogin(cancelDealWrapper);
                }
            }

            @Override
            public void onError() {

            }
        });
    }
    public interface cancelDealResultProvider{
        void onSuccessfullLogin(CancelDealWrapper cancelDealWrapper);

        void onLoginFailure(String msg);
    }
}
