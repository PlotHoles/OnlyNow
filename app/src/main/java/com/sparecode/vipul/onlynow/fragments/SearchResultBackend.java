package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.SearchResultWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 21/3/17.
 */

public class SearchResultBackend {

    private Context context;
    private String keyword;
    private String lat;
    private String longi;
    private String cat_id;
    private String sort_by;
    private String page;
    SearchResultDataProvider searchResultDataProvider;

    public SearchResultBackend(Context context, String keyword, String lat, String longi, String cat_id, String sort_by, String page, SearchResultDataProvider searchResultDataProvider) {
        this.context = context;
        this.keyword = keyword;
        this.lat = lat;
        this.longi = longi;
        this.cat_id = cat_id;
        this.sort_by = sort_by;
        this.page = page;
        this.searchResultDataProvider = searchResultDataProvider;
        call();
    }

    public SearchResultBackend(Context applicationContext, String lat, String aLong, SearchResultDataProvider searchResultDataProvider) {

        this.context = applicationContext;
        this.lat = lat;
        this.longi = aLong;
        this.searchResultDataProvider = searchResultDataProvider;
        call();

    }

    public SearchResultBackend(Context applicationContext, String latitude, String longitude, String cat_id, SearchResultDataProvider searchResultDataProvider) {
        this.context = applicationContext;
        this.lat = latitude;
        this.longi = longitude;
        this.cat_id = cat_id;
        this.searchResultDataProvider = searchResultDataProvider;
        call();
    }

    public SearchResultBackend(Context applicationContext, String keyword, SearchResultDataProvider searchResultDataProvider) {
        this.context = applicationContext;
        this.keyword = keyword;
        this.searchResultDataProvider = searchResultDataProvider;
        call();
    }

    private void call()
    {
        new GetRequest<SearchResultWrapper>().toGetRequest(context, RequestApi.USERSERVICE, new ReqestParameter().toSearchResult(keyword,lat,longi,cat_id,sort_by,page), SearchResultWrapper.class, new OnResponse<SearchResultWrapper>() {
            @Override
            public void onSuccess(SearchResultWrapper searchResultWrapper) {

                if (searchResultWrapper.getStatus() == 0)
                {
                    searchResultDataProvider.onFailure(searchResultWrapper.getMessage());
                }
                else
                {
                    searchResultDataProvider.onSuccessfull(searchResultWrapper);
                }
            }

            @Override
            public void onError() {

            }
        });
    }
    public interface SearchResultDataProvider{

        void onSuccessfull(SearchResultWrapper searchResultWrapper);

        void onFailure(String msg);
    }
}
