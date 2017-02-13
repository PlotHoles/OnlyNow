package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.vipul.onlynow.R;


public class ShopFragment extends Fragment {


    public ShopFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ShopFragment newInstance(String text) {
        ShopFragment fragment = new ShopFragment();
        Bundle args = new Bundle();
        args.putString("arg", text);
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
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        return view;
    }


}
