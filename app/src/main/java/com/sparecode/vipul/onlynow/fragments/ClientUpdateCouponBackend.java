package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.CLientUpdateCouponWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 7/3/17.
 */

public class ClientUpdateCouponBackend {

    private Context context;
    private String couponId;
    private String instruction;
    private String description;
    private ClientUpdateCouponResultProvider clientUpdateCouponResultProvider;

    public ClientUpdateCouponBackend(Context context, String couponId,String instruction, String description,ClientUpdateCouponResultProvider clientUpdateCouponResultProvider) {
        this.context = context;
        this.couponId = couponId;
        this.instruction = instruction;
        this.description = description;
        this.clientUpdateCouponResultProvider = clientUpdateCouponResultProvider;
        call();
    }

    private void call()
    {
        new GetRequest<CLientUpdateCouponWrapper>().toGetRequest(context, RequestApi.CLIENTSERVICE, new ReqestParameter().toUpdateCoupon(couponId, instruction, description), CLientUpdateCouponWrapper.class, new OnResponse<CLientUpdateCouponWrapper>() {
            @Override
            public void onSuccess(CLientUpdateCouponWrapper cLientUpdateCouponWrapper) {

                if (cLientUpdateCouponWrapper.getStatus() == 0)
                {
                    clientUpdateCouponResultProvider.onLoginFailure(cLientUpdateCouponWrapper.getMessage());
                }
                else
                {
                    clientUpdateCouponResultProvider.onSuuccessfullLogin(cLientUpdateCouponWrapper);
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    public interface ClientUpdateCouponResultProvider{

        void onSuuccessfullLogin(CLientUpdateCouponWrapper cLientUpdateCouponWrapper);

        void onLoginFailure(String msg);
    }
}
