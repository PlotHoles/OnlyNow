package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.SearchPopularQueriesWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 21/3/17.
 */

public class SearchPopularQueriesbackend {

    private Context context;
    SearchPopularQueriesDataProvider searchPopularQueriesDataProvider;

    public SearchPopularQueriesbackend(Context context, SearchPopularQueriesDataProvider searchPopularQueriesDataProvider) {
        this.context = context;
        this.searchPopularQueriesDataProvider = searchPopularQueriesDataProvider;
        call();
    }

    private void call()
    {
        new GetRequest<SearchPopularQueriesWrapper>().toGetRequest(context, RequestApi.COMMONSERVICE, new ReqestParameter().toSearchPopularQueries(), SearchPopularQueriesWrapper.class, new OnResponse<SearchPopularQueriesWrapper>() {
            @Override
            public void onSuccess(SearchPopularQueriesWrapper searchPopularQueriesWrapper) {

                if (searchPopularQueriesWrapper.getStatus() == 0)
                {
                    searchPopularQueriesDataProvider.onFailure(searchPopularQueriesWrapper.getMessage());
                }
                else
                {
                    searchPopularQueriesDataProvider.onSuccessfull(searchPopularQueriesWrapper);
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    public interface SearchPopularQueriesDataProvider
    {
        void onSuccessfull(SearchPopularQueriesWrapper searchPopularQueriesWrapper);

        void onFailure(String msg);
    }
}
