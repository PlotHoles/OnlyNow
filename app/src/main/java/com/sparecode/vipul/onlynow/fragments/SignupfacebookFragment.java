package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupfacebookFragment extends BaseFragment implements View.OnClickListener{

    @Bind(R.id.image_logo)
    ImageView imageLogo;
    @Bind(R.id.text_des)
    TextView textDes;
    @Bind(R.id.button_facebook)
    Button buttonFacebook;
    @Bind(R.id.text_Facebook)
    TextView textFacebook;
    @Bind(R.id.text_signup_or)
    TextView textSignupOr;
    @Bind(R.id.button_signup)
    Button buttonSignup;
    @Bind(R.id.editText3)
    EditText editText3;
    @Bind(R.id.editText4)
    EditText editText4;

    public SignupfacebookFragment() {
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
        View view = inflater.inflate(R.layout.fragment_signupfacebook, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText("Sign Up");
        ((BaseActivity)getActivity()).getImgToolBarBack().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgToolBarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) getActivity()).openSplashPage();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.button_facebook, R.id.button_signup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_facebook:
                break;
            case R.id.button_signup:
                ((BaseActivity)getActivity()).openSignupPage();
                break;
        }
    }
}
