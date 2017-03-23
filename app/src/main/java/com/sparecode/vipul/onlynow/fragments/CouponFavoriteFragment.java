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
import com.sparecode.vipul.onlynow.adapters.CouponFavouriteAdapter;
import com.sparecode.vipul.onlynow.model.MyListFavoriteWrapper;
import com.sparecode.vipul.onlynow.model.SignupWrapper;
import com.sparecode.vipul.onlynow.util.Prefs;
import com.sparecode.vipul.onlynow.view.OnClickListener;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CouponFavoriteFragment extends BaseFragment implements CouponFavouritebackendBackend.CouponFavouriteDataProvider {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    CouponFavouriteAdapter couponFavouriteAdapter;
    SignupWrapper signupWrapper;
    @Bind(R.id.nodata)
    LatoTextView nodata;
    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;

    public CouponFavoriteFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CouponFavoriteFragment newInstance(String text) {
        CouponFavoriteFragment fragment = new CouponFavoriteFragment();
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
        View view = inflater.inflate(R.layout.fragment_coupon_favorite, container, false);
        ButterKnife.bind(this, view);
        getUser();
        CouponFavouritebackendBackend couponFavouritebackendBackend = new CouponFavouritebackendBackend(getActivity(), signupWrapper.getData().getId(), 1, this);
        couponFavouritebackendBackend.call(1);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerview.setLayoutManager(gridLayoutManager);
        /*couponFavouriteAdapter = new CouponFavouriteAdapter(getActivity());
        recyclerview.setAdapter(couponFavouriteAdapter);*/
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CouponFavouritebackendBackend couponFavouritebackendBackend = new CouponFavouritebackendBackend(getActivity(), signupWrapper.getData().getId(), 1, CouponFavoriteFragment.this);
                couponFavouritebackendBackend.call(1);
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
    public void onFavouriteSuccess(MyListFavoriteWrapper myListFavoriteWrapper) {
        nodata.setVisibility(View.GONE);
        recyclerview.setVisibility(View.VISIBLE);
        swiperefresh.setRefreshing(false);
        couponFavouriteAdapter = new CouponFavouriteAdapter(getActivity(), myListFavoriteWrapper, new OnClickListener() {
            @Override
            public void onItemClicked(int position) {

            }
        });
        recyclerview.setAdapter(couponFavouriteAdapter);
    }

    @Override
    public void onFailure(String msg) {
        swiperefresh.setRefreshing(false);
        recyclerview.setVisibility(View.GONE);
        nodata.setVisibility(View.VISIBLE);
    }
}
