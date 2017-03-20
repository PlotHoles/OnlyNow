package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.adapters.CouponSavedAdapter;
import com.sparecode.vipul.onlynow.model.MyListSavedWrapper;
import com.sparecode.vipul.onlynow.model.SignupWrapper;
import com.sparecode.vipul.onlynow.util.Prefs;
import com.sparecode.vipul.onlynow.view.OnClickListener;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CouponSavedFragment extends BaseFragment implements CouponSavedBackend.CouponSavedDataProvider{


    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    CouponSavedAdapter couponSavedAdapter;
    SignupWrapper signupWrapper;

    public CouponSavedFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CouponSavedFragment newInstance(String text) {
        CouponSavedFragment fragment = new CouponSavedFragment();
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
        View view = inflater.inflate(R.layout.fragment_coupon_saved, container, false);
        ButterKnife.bind(this, view);
        getUser();
        CouponSavedBackend couponSavedBackend = new CouponSavedBackend(getActivity(),signupWrapper.getData().getId(),this);
        couponSavedBackend.call(1);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerview.setLayoutManager(gridLayoutManager);



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
    public void onSavedSuccess(MyListSavedWrapper myListSavedWrapper) {
        couponSavedAdapter = new CouponSavedAdapter(getActivity(), myListSavedWrapper, new OnClickListener() {
            @Override
            public void onItemClicked(int position) {

            }
        });
      recyclerview.setAdapter(couponSavedAdapter);
    }

    @Override
    public void onFailure(String msg) {

    }
}
