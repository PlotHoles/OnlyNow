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
import com.sparecode.vipul.onlynow.adapters.CouponViewedAdapter;
import com.sparecode.vipul.onlynow.model.MyListFavoriteWrapper;
import com.sparecode.vipul.onlynow.model.SignupWrapper;
import com.sparecode.vipul.onlynow.util.Prefs;
import com.sparecode.vipul.onlynow.view.OnClickListener;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CouponViewedFragment extends BaseFragment implements CouponFavouritebackendBackend.CouponFavouriteDataProvider {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    CouponViewedAdapter couponViewedAdapter;
    SignupWrapper signupWrapper;
    @Bind(R.id.nodata)
    LatoTextView nodata;
    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;

    public CouponViewedFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CouponViewedFragment newInstance(String text) {
        CouponViewedFragment fragment = new CouponViewedFragment();
        Bundle args = new Bundle();
        args.putString("msg", text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void getUser() {
        try {
            signupWrapper = new Gson().fromJson(Prefs.getString("user", ""), SignupWrapper.class);

        } catch (JsonSyntaxException e) {
            ((BaseActivity) getActivity()).openSplashPage();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coupon_viewed, container, false);
        ButterKnife.bind(this, view);
        getUser();
        CouponFavouritebackendBackend couponFavouritebackendBackend = new CouponFavouritebackendBackend(getActivity(), signupWrapper.getData().getId(), 2, this);
        couponFavouritebackendBackend.call(1);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerview.setLayoutManager(gridLayoutManager);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CouponFavouritebackendBackend couponFavouritebackendBackend = new CouponFavouritebackendBackend(getActivity(), signupWrapper.getData().getId(), 2, CouponViewedFragment.this);
                couponFavouritebackendBackend.call(1);
            }
        });
      /*  couponViewedAdapter = new CouponViewedAdapter(getActivity());
        recyclerview.setAdapter(couponViewedAdapter);*/
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
    public void onFavouriteSuccess(MyListFavoriteWrapper myListFavoriteWrapper) {
        if (getActivity()!=null){
            if (nodata!=null) {
                if (swiperefresh != null) {
                    if (recyclerview!=null) {
                        nodata.setVisibility(View.GONE);
                        recyclerview.setVisibility(View.VISIBLE);
                        swiperefresh.setRefreshing(false);
                        couponViewedAdapter = new CouponViewedAdapter(getActivity(), myListFavoriteWrapper, new OnClickListener() {
                            @Override
                            public void onItemClicked(int position) {

                            }
                        });
                        recyclerview.setAdapter(couponViewedAdapter);
                    }}}}
    }

    @Override
    public void onFailure(String msg) {
        if (getActivity()!=null){
            if (nodata!=null) {
                if (swiperefresh != null) {
                    if (recyclerview != null) {
                        swiperefresh.setRefreshing(false);
                        recyclerview.setVisibility(View.GONE);
                        nodata.setVisibility(View.VISIBLE);
                    }
                }}}}
}
