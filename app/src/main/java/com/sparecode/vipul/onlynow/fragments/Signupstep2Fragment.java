package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.adapters.GridAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Signupstep2Fragment extends BaseFragment {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    GridAdapter gridAdapter;
    String Title2;


    public Signupstep2Fragment() {
        // Required empty public constructor
    }

    public static Signupstep2Fragment newInstance(String text) {
        Signupstep2Fragment fragment = new Signupstep2Fragment();
        Bundle args = new Bundle();
        args.putString("arg", text);
        fragment.setArguments(args);
        System.out.println("------>"+args);
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
        View view = inflater.inflate(R.layout.fragment_signupstep2, container, false);
        ButterKnife.bind(this, view);

        gridAdapter = new GridAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerview.setLayoutManager(gridLayoutManager);
        recyclerview.setAdapter(gridAdapter);

        Title2 = getArguments().getString("arg");
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setToolbarForFragment() {
       // ((BaseActivity)getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
       // ((BaseActivity)getActivity()).getTextViewToolBarTitle().setText(Title2);
    }
}
