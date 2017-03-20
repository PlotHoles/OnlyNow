package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;

import com.sparecode.vipul.onlynow.interfaces.OnResponse;
import com.sparecode.vipul.onlynow.model.NoticeWrapper;
import com.sparecode.vipul.onlynow.webservice.GetRequest;
import com.sparecode.vipul.onlynow.webservice.ReqestParameter;
import com.sparecode.vipul.onlynow.webservice.RequestApi;

/**
 * Created by hitesh on 9/3/17.
 */

public class NoticeBackend {
    private String userId;
    private Context context;
    private NoticeDataProvider noticeDataProvider;

    public NoticeBackend(String userId, Context context, NoticeDataProvider noticeDataProvider) {
        this.userId = userId;
        this.context = context;
        this.noticeDataProvider = noticeDataProvider;

    }

    public void callWs(int page) {
        new GetRequest<NoticeWrapper>().toGetRequest(context, RequestApi.USERSERVICE, new ReqestParameter().toGetNotification(userId,page), NoticeWrapper.class, new OnResponse<NoticeWrapper>() {
            @Override
            public void onSuccess(NoticeWrapper noticeWrapper) {
                if (noticeWrapper.getStatus() == 1) {
                    noticeDataProvider.onSuccess(noticeWrapper);
                } else {
                    noticeDataProvider.onFailure("" + noticeWrapper.getData());
                }
            }

            @Override
            public void onError() {
                noticeDataProvider.onFailure("Please try again !!");
            }
        });
    }


    public interface NoticeDataProvider {
        void onSuccess(NoticeWrapper noticeWrapper);

        void onFailure(String msg);
    }
}
