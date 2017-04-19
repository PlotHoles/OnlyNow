package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;


public class userPrivacyFragment extends BaseFragment {


    @Bind(R.id.webview)
    WebView webview;

    public userPrivacyFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_privacy, container, false);
        ButterKnife.bind(this, view);
        webview.loadUrl("http://sparecodesolutions.com/onlynow/Pages/privacy");
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity)getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity)getActivity()).getImgToolBarBack().setVisibility(View.VISIBLE);
        ((BaseActivity)getActivity()).getTextViewToolBarTitle().setText("Privacy Policy");
        ((BaseActivity)getActivity()).getImgToolBarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).openProfilePage();
            }
        });
    }
}
