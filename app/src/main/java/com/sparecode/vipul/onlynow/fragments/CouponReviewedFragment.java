package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
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
import com.sparecode.vipul.onlynow.adapters.CouponReviewedAdapter;
import com.sparecode.vipul.onlynow.model.MyListReviewedWrapper;
import com.sparecode.vipul.onlynow.model.SignupWrapper;
import com.sparecode.vipul.onlynow.util.Prefs;
import com.sparecode.vipul.onlynow.view.OnClickListener;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CouponReviewedFragment extends BaseFragment implements CouponReviewedBackend.CouponReviewedDataProvider {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    CouponReviewedAdapter couponReviewedAdapter;
    SignupWrapper signupWrapper;
    @Bind(R.id.nodata)
    LatoTextView nodata;
    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;

    public CouponReviewedFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CouponReviewedFragment newInstance(String text) {
        CouponReviewedFragment fragment = new CouponReviewedFragment();
        Bundle args = new Bundle();
        args.putString("msg", text);
        fragment.setArguments(args);
        return fragment;
    }

    private void getUser() {
        try {
            signupWrapper = new Gson().fromJson(Prefs.getString("user", ""), SignupWrapper.class);

        } catch (JsonSyntaxException e) {
            ((BaseActivity) getActivity()).openSplashPage();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coupon_reviewed, container, false);
        ButterKnife.bind(this, view);
        getUser();
        CouponReviewedBackend couponReviewedBackend = new CouponReviewedBackend(getActivity(), signupWrapper.getData().getId(), this);
        couponReviewedBackend.call(1);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerview.setLayoutManager(gridLayoutManager);
//        couponReviewedAdapter = new CouponReviewedAdapter(getActivity());
//        recyclerview.setAdapter(couponReviewedAdapter);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CouponReviewedBackend couponReviewedBackend = new CouponReviewedBackend(getActivity(), signupWrapper.getData().getId(), CouponReviewedFragment.this);
                couponReviewedBackend.call(1);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setToolbarForFragment() {

    }

    @Override
    public void onReviewedSuccess(MyListReviewedWrapper myListReviewedWrapper) {
        if (getActivity()!=null){
            if (nodata!=null) {
                if (swiperefresh != null) {
                    if (recyclerview!=null) {
                        nodata.setVisibility(View.GONE);
                        recyclerview.setVisibility(View.VISIBLE);
                        swiperefresh.setRefreshing(false);
                        couponReviewedAdapter = new CouponReviewedAdapter(getActivity(), myListReviewedWrapper, new OnClickListener() {
                            @Override
                            public void onItemClicked(int position) {

                            }
                        });
                        recyclerview.setAdapter(couponReviewedAdapter);
                    }}}}
    }

    @Override
    public void onFailure(String msg) {
        if (getActivity()!=null){
            if (nodata!=null) {
                if (swiperefresh != null) {
                    if (recyclerview != null) {
                        swiperefresh.setRefreshing(false);
                        nodata.setVisibility(View.VISIBLE);
                        recyclerview.setVisibility(View.GONE);
                    }
                }}}}
}
