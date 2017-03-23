package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.adapters.NoticeAdapter;
import com.sparecode.vipul.onlynow.model.NoticeData;
import com.sparecode.vipul.onlynow.model.NoticeWrapper;
import com.sparecode.vipul.onlynow.model.SignupWrapper;
import com.sparecode.vipul.onlynow.util.Prefs;
import com.sparecode.vipul.onlynow.view.EndlessRecyclerOnScrollListener;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NoticeFragment extends BaseFragment implements NoticeBackend.NoticeDataProvider {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    NoticeAdapter noticeAdapter;
    NoticeBackend noticeBackend;
    @Bind(R.id.nodata)
    LatoTextView nodata;
    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    private View view;
    GridLayoutManager gridLayoutManager;
    List<NoticeData> data;

    public NoticeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_notice, container, false);
        getUser();
        ButterKnife.bind(this, view);


        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerview.setLayoutManager(gridLayoutManager);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                noticeBackend = new NoticeBackend(signupWrapper.getData().getId(), getActivity(), NoticeFragment.this);
                noticeBackend.callWs(1);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        noticeBackend = new NoticeBackend(signupWrapper.getData().getId(), getActivity(), this);
        noticeBackend.callWs(1);
        data = new ArrayList<>();
        recyclerview.addOnScrollListener(new EndlessRecyclerOnScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                noticeBackend.callWs(current_page);
            }
        });
        noticeAdapter = new NoticeAdapter(data, getActivity());
        recyclerview.setAdapter(noticeAdapter);
    }

    SignupWrapper signupWrapper;

    private void getUser() {
        try {
            signupWrapper = new Gson().fromJson(Prefs.getString("user", ""), SignupWrapper.class);
        } catch (JsonSyntaxException e) {
            ((BaseActivity) getActivity()).openSplashPage();
        }
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText("Notice");
        ((BaseActivity) getActivity()).getTabLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgSettings().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).setOptionMenuVisibility(false);
        ((BaseActivity) getActivity()).getImgMap().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgSearchMap().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgShare().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgToolBarCancel().setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onSuccess(NoticeWrapper noticeWrapper) {
        if (getActivity() != null) {
            swiperefresh.setRefreshing(false);
            nodata.setVisibility(View.GONE);
            recyclerview.setVisibility(View.VISIBLE);
            data.addAll(noticeWrapper.getData());
            noticeAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(NoticeWrapper noticeWrapper) {
        if (getActivity() != null) {
            swiperefresh.setRefreshing(false);
            if (noticeWrapper.getPage() != null) {
                if (noticeWrapper.getPage() == 1) {
                    recyclerview.setVisibility(View.GONE);
                    nodata.setVisibility(View.VISIBLE);
                }
            }

            //Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
        }
    }
}
