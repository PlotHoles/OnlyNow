package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;

public class ClientSettingFragment extends BaseFragment {

    public ClientSettingFragment() {
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
        return inflater.inflate(R.layout.fragment_client_setting, container, false);
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity)getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity)getActivity()).getTextViewToolBarTitle().setText(getString(R.string.setting));
        ((BaseActivity)getActivity()).getTabLayoutclient().setVisibility(View.VISIBLE);
        ((BaseActivity)getActivity()).getImgAdd().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getImgEdit().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getImgToolBarCancel().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getFab().setVisibility(View.GONE);
    }
}
