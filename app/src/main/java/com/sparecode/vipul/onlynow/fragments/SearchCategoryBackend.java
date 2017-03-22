package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.SearchCategoryWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 21/3/17.
 */

public class SearchCategoryBackend {

    private Context context;
    private String getall;
    SearchCategoryDataProvider searchCategoryDataProvider;

    public SearchCategoryBackend(Context context, String getall, SearchCategoryDataProvider searchCategoryDataProvider) {
        this.context = context;
        this.getall = getall;
        this.searchCategoryDataProvider = searchCategoryDataProvider;
        call();
    }

    private void call()
    {
        new GetRequest<SearchCategoryWrapper>().toGetRequest(context, RequestApi.COMMONSERVICE, new ReqestParameter().toSearchCategory(getall), SearchCategoryWrapper.class, new OnResponse<SearchCategoryWrapper>() {
            @Override
            public void onSuccess(SearchCategoryWrapper searchCategoryWrapper) {
                if (searchCategoryWrapper.getStatus() == 0)
                {
                    searchCategoryDataProvider.onFailure(searchCategoryWrapper.getMessage());
                }
                else
                {
                    searchCategoryDataProvider.onsuccessfull(searchCategoryWrapper);
                }
            }

            @Override
            public void onError() {

            }
        });
    }
    public interface SearchCategoryDataProvider
    {
        void onsuccessfull(SearchCategoryWrapper searchCategoryWrapper);

        void onFailure(String msg);

    }

}
