package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.UpdateLocationWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 10/3/17.
 */

public class UpdateLocationBackend {

    private Context context;
    private String shopId,zip_code,prefecture,city;
    UpdateLocationResultProvider updateLocationResultProvider;

    public UpdateLocationBackend(Context context,String shopId, String zip_code, String prefecture, String city, UpdateLocationResultProvider updateLocationResultProvider) {
        this.context = context;
        this.shopId = shopId;
        this.zip_code = zip_code;
        this.prefecture = prefecture;
        this.city = city;
        this.updateLocationResultProvider = updateLocationResultProvider;
        call();
    }

    private void call()
    {
        new GetRequest<UpdateLocationWrapper>().toGetRequest(context, RequestApi.CLIENTSERVICE, new ReqestParameter().toUpdateLocation(shopId, zip_code, prefecture, city), UpdateLocationWrapper.class, new OnResponse<UpdateLocationWrapper>() {
            @Override
            public void onSuccess(UpdateLocationWrapper updateLocationWrapper) {

                if (updateLocationWrapper.getStatus() == 0)
                {
                    updateLocationResultProvider.onLoginFailure(updateLocationWrapper.getMessage());
                }
                else {
                    updateLocationResultProvider.onSuccesssfullLogin(updateLocationWrapper);
                }
            }

            @Override
            public void onError() {
                updateLocationResultProvider.onLoginFailure("Bad response from server");
            }
        });
    }
    public interface UpdateLocationResultProvider{

        void onSuccesssfullLogin(UpdateLocationWrapper updateLocationWrapper);

        void onLoginFailure(String msg);
    }
}
