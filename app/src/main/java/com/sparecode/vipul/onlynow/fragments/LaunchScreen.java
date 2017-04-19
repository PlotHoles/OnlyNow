package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.model.LoginWrapper;
import com.sparecode.vipul.onlynow.model.SignupWrapper;
import com.sparecode.vipul.onlynow.util.Prefs;

/**
 * Created by hitesh on 6/3/17.
 */

public class LaunchScreen extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_launchscreen, container, false);
        getUser();
        getClient();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (signupWrapper!=null) {
                    if (signupWrapper.getData() != null) {
                        if (getActivity()!=null)
                            ((BaseActivity) getActivity()).openHomePage();
                   } else {
                       ((BaseActivity) getActivity()).openSplashPage();
                   }
                }else if (loginWrapper != null){
                    if (loginWrapper.getData() != null){
                        ((BaseActivity)getActivity()).openClientCouponPage();
                    }
                    else {
                        ((BaseActivity)getActivity()).openClientSplashPage();
                    }
                }
                else {
                    ((BaseActivity) getActivity()).openSplashPage();
                }

            }
        }, 3000);
        return view;
    }

    SignupWrapper signupWrapper;
    LoginWrapper loginWrapper;
    private void getUser() {
        try {
            signupWrapper = new Gson().fromJson(Prefs.getString("user", ""), SignupWrapper.class);

        } catch (JsonSyntaxException e) {
            ((BaseActivity) getActivity()).openSplashPage();
        }
    }

    private void getClient(){
        try {
            loginWrapper = new Gson().fromJson(Prefs.getString("user", ""), LoginWrapper.class);

        } catch (JsonSyntaxException e) {
            ((BaseActivity) getActivity()).openSplashPage();
        }
    }
    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getTabLayout().setVisibility(View.GONE);
    }
}
