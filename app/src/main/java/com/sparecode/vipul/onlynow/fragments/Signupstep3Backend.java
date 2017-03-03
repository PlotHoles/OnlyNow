package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.CategoryWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by hitesh on 25/2/17.
 */

public class Signupstep3Backend {
    CategoryListProvider categoryListProvider;
    Context context;

    public Signupstep3Backend(CategoryListProvider categoryListProvider, Context context) {
        this.categoryListProvider = categoryListProvider;
        this.context = context;
    }

    private void callWs(int page) {

        new GetRequest<CategoryWrapper>().toGetRequest(context, RequestApi.COMMONSERVICE, new ReqestParameter().toCategoryList(page), CategoryWrapper.class, new OnResponse<CategoryWrapper>() {
            @Override
            public void onSuccess(CategoryWrapper categoryWrapper) {
                if (categoryWrapper.getStatus() == 0) {
                    categoryListProvider.onError(categoryWrapper.getMessage());
                } else {
                    categoryListProvider.onSuccess(categoryWrapper);
                }
            }
            @Override
            public void onError() {
                categoryListProvider.onError("Please try again !!");
            }
        });
    }


    /*public void addFavoriteLocation(String userId, String areaId, String lat, String lng) {

        new GetRequest<AddActiveAreaWrapper>().toGetRequest(context, RequestApi.USERSERVICE, new ReqestParameter().toAddActiveArea(userId, areaId, lat, lng), AddActiveAreaWrapper.class, new OnResponse<AddActiveAreaWrapper>() {
            @Override
            public void onSuccess(AddActiveAreaWrapper addFavWrapper) {
                if (addFavWrapper.getStatus() == 0) {
                    //areaListProvider.onError(addFavWrapper);
                    Log.e("RESPONSE", "" + addFavWrapper.getMessage());
                } else {
                    Log.e("RESPONSE", "" + addFavWrapper.getData().getAreaId());
                }
            }

            @Override
            public void onError() {
                Log.e("AN ERROR OCCURED", ":::T1:::");
            }
        });
    }

    public void removeFavoriteLocation(String areaId) {
        new GetRequest<RemoveActiveAreaWrapper>().toGetRequest(context, RequestApi.USERSERVICE, new ReqestParameter().toRemoveActivrArea(areaId), RemoveActiveAreaWrapper.class, new OnResponse<RemoveActiveAreaWrapper>() {
            @Override
            public void onSuccess(RemoveActiveAreaWrapper removeActiveAreaWrapper) {
            }
            @Override
            public void onError() {

            }
        });
    }*/

    public void callPagination(int page) {
        callWs(page);
    }

    interface CategoryListProvider {
        void onSuccess(CategoryWrapper categoryWrapper);

        void onError(String msg);
    }
}
