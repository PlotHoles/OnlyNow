package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.model.LoginWrapper;
import com.sparecode.vipul.onlynow.widgets.LatoButton;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClientSplashFragment extends BaseFragment {


    @Bind(R.id.image_next)
    ImageView imageNext;
    @Bind(R.id.text_user)
    LatoTextView textUser;
    @Bind(R.id.imagelogowhite)
    ImageView imagelogowhite;
    @Bind(R.id.text_des)
    LatoTextView textDes;
    @Bind(R.id.linearLayout2)
    LinearLayout linearLayout2;
    @Bind(R.id.createaccount_button)
    LatoButton createaccountButton;
    @Bind(R.id.signin_button)
    LatoButton signinButton;
    LoginWrapper loginWrapper;

    public ClientSplashFragment() {
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
        View view = inflater.inflate(R.layout.fragment_client_splash, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.image_next, R.id.text_user, R.id.createaccount_button, R.id.signin_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_next:
                break;
            case R.id.text_user:
                ((BaseActivity)getActivity()).openSplashPage();
                break;
            case R.id.createaccount_button:
                ((BaseActivity)getActivity()).openClientSignupPage();
                break;
            case R.id.signin_button:
                ((BaseActivity)getActivity()).openClientSigninPage();
                break;
        }
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity)getActivity()).getAppbarLayout().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getImgToolBarBack().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getTabLayoutclient().setVisibility(View.GONE);

    }
}
