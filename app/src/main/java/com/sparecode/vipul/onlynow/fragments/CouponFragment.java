package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.vipul.onlynow.R;


public class CouponFragment extends Fragment {

    public CouponFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CouponFragment newInstance(String text) {
        CouponFragment fragment = new CouponFragment();
        Bundle args = new Bundle();
        args.putString("arg",text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_coupon, container, false);
        return view;
    }


}
