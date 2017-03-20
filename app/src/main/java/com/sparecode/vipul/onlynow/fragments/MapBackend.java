package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by vipul on 20/3/17.
 */

public class MapBackend {

    private Context context;
    private String latitude,longitude;
    MapDataProvider mapDataProvider;

    public MapBackend(Context context, String latitude, String longitude, MapDataProvider mapDataProvider) {
        this.context = context;
        this.latitude = latitude;
        this.longitude = longitude;
        this.mapDataProvider = mapDataProvider;
        call();
    }

    private void call()
    {
        new GetRequest<MapWrapper>().toGetRequest(context, RequestApi.USERSERVICE, new ReqestParameter().toMAPCOUPONS(latitude, longitude), MapWrapper.class, new OnResponse<MapWrapper>() {
            @Override
            public void onSuccess(MapWrapper mapWrapper) {

                if (mapWrapper.getStatus()==0)
                {
                    mapDataProvider.onFailure(mapWrapper.getMessage());
                }
                else
                {
                    mapDataProvider.onSuccessfull(mapWrapper);
                }
            }

            @Override
            public void onError() {

            }
        });
    }
    public interface MapDataProvider{

        void onSuccessfull(MapWrapper mapWrapper);

        void onFailure(String msg);
    }
}
