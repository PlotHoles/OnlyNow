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

public class UpdateCategoryBackend {

    private Context context;
    private String cat_id,shop_id;
    UpdateCategoryResultProvider updateCategoryResultProvider;

    public UpdateCategoryBackend(Context context, String shop_id, String cat_id,UpdateCategoryResultProvider updateCategoryResultProvider) {
        this.context = context;
        this.shop_id = shop_id;
        this.cat_id = cat_id;
        this.updateCategoryResultProvider = updateCategoryResultProvider;
        call();
    }

    private void call()
    {
        new GetRequest<UpdateLocationWrapper>().toGetRequest(context, RequestApi.CLIENTSERVICE, new ReqestParameter().toUpdateCategory(shop_id,cat_id), UpdateLocationWrapper.class, new OnResponse<UpdateLocationWrapper>() {
            @Override
            public void onSuccess(UpdateLocationWrapper updateLocationWrapper) {

                if (updateLocationWrapper.getStatus() == 0)
                {
                    updateCategoryResultProvider.onLoginFailure(updateLocationWrapper.getMessage());
                }
                else {
                    updateCategoryResultProvider.onSuccesssfullLogin(updateLocationWrapper);
                }
            }

            @Override
            public void onError() {
                updateCategoryResultProvider.onLoginFailure("Bad response from server");
            }
        });
    }
    public interface UpdateCategoryResultProvider{

        void onSuccesssfullLogin(UpdateLocationWrapper updateLocationWrapper);

        void onLoginFailure(String msg);
    }
}
