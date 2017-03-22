package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.SearchLocationWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 21/3/17.
 */

public class SearchBackend {

    private Context context;
    SearchDataProvider searchDataProvider;

    public SearchBackend(Context context, SearchDataProvider searchDataProvider) {
        this.context = context;
        this.searchDataProvider = searchDataProvider;
        call();
    }

    private void call()
    {
        new GetRequest<SearchLocationWrapper>().toGetRequest(context, RequestApi.USERSERVICE, new ReqestParameter().toGetLocation(), SearchLocationWrapper.class, new OnResponse<SearchLocationWrapper>() {
            @Override
            public void onSuccess(SearchLocationWrapper searchLocationWrapper) {

                if (searchLocationWrapper.getStatus() == 0)
                {
                    searchDataProvider.Failure(searchLocationWrapper.getMessage());
                }
                else
                {
                    searchDataProvider.Successfull(searchLocationWrapper);
                }
            }

            @Override
            public void onError() {

            }
        });
    }
    public interface SearchDataProvider{

        void Successfull(SearchLocationWrapper searchLocationWrapper);

        void Failure(String msg);
    }
}
