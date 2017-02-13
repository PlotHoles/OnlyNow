package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.adapters.ListAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FashionFragment extends Fragment {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    ListAdapter listAdapter;

    public FashionFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FashionFragment newInstance(String text) {
        FashionFragment fragment = new FashionFragment();
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
        View view = inflater.inflate(R.layout.fragment_fashion, container, false);
        ButterKnife.bind(this, view);

        listAdapter = new ListAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
        recyclerview.setLayoutManager(gridLayoutManager);
        recyclerview.setAdapter(listAdapter);

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
