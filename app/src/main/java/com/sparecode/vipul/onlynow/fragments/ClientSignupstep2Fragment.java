package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.adapters.LinearAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ClientSignupstep2Fragment extends Fragment {


    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    LinearAdapter linearAdapter;

    public ClientSignupstep2Fragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ClientSignupstep2Fragment newInstance(String text) {
        ClientSignupstep2Fragment fragment = new ClientSignupstep2Fragment();
        Bundle args = new Bundle();
        args.putString("msg", text);
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
        View view = inflater.inflate(R.layout.fragment_client_signupstep2, container, false);
        ButterKnife.bind(this, view);

        linearAdapter = new LinearAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
        recyclerview.setLayoutManager(gridLayoutManager);
        recyclerview.setAdapter(linearAdapter);

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
