package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.vipul.onlynow.R;

public class Signupstep1Fragment extends BaseFragment {

    String Title1;
    public Signupstep1Fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Signupstep1Fragment newInstance(String text) {
        Signupstep1Fragment fragment = new Signupstep1Fragment();
        Bundle args = new Bundle();
        args.putString("arg", text);
        fragment.setArguments(args);
        System.out.println("------>"+text);
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
        View view = inflater.inflate(R.layout.fragment_signupstep1, container, false);

        Title1 = getArguments().getString("arg");
        System.out.println("------>"+Title1);

        return view;
    }


    @Override
    public void setToolbarForFragment() {
       // ((BaseActivity)getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
       // ((BaseActivity)getActivity()).getTextViewToolBarTitle().setText(Title1);
    }
}
