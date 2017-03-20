package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SplashFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.image_next)
    ImageView imageNext;
    @Bind(R.id.text_client)
    TextView textClient;
    @Bind(R.id.imagelogowhite)
    ImageView imagelogowhite;
    @Bind(R.id.text_des)
    TextView textDes;
    @Bind(R.id.linearLayout2)
    LinearLayout linearLayout2;
    @Bind(R.id.createaccount_button)
    Button createaccountButton;
    @Bind(R.id.signin_button)
    Button signinButton;
    // TODO: Rename parameter arguments, choose names that match


    public SplashFragment() {
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
        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getTabLayout().setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.image_next, R.id.text_client, R.id.createaccount_button, R.id.signin_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_next:
                ((BaseActivity) getActivity()).openSigninPage();
                break;
            case R.id.text_client:
                ((BaseActivity) getActivity()).openClientSplashPage();
                break;
            case R.id.createaccount_button:
                ((BaseActivity) getActivity()).openSignupfacebookPage();
                break;
            case R.id.signin_button:
                ((BaseActivity) getActivity()).openSigninPage();
                break;
        }
    }
}
