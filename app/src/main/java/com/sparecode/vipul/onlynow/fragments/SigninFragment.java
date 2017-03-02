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

public class SigninFragment extends BaseFragment {

    @Bind(R.id.text_signup)
    TextView textSignup;
    @Bind(R.id.image_logo)
    ImageView imageLogo;
    @Bind(R.id.text_des)
    TextView textDes;
    @Bind(R.id.button_facebook)
    Button buttonFacebook;
    @Bind(R.id.text_facebook)
    TextView textFacebook;
    @Bind(R.id.text_signup_or)
    TextView textSignupOr;
    @Bind(R.id.edit_email)
    EditText editEmail;
    @Bind(R.id.edit_password)
    EditText editPassword;
    @Bind(R.id.text_forgot)
    TextView textForgot;
    @Bind(R.id.button_signin)
    Button buttonSignin;

    public SigninFragment() {
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
        View view = inflater.inflate(R.layout.fragment_signin, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    void validation()
    {
        if (editEmail.getText().toString().trim().length() == 0)
        {
            editEmail.setError(getString(R.string.email));
            editEmail.requestFocus();
        }
        else if (editPassword.getText().toString().trim().length() == 0)
        {
            editPassword.setError(getString(R.string.password));
            editPassword.requestFocus();
        }
        else
        {
            ((BaseActivity)getActivity()).openHomePage();
        }

    }
    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.sign));
        ((BaseActivity)getActivity()).getImgToolBarBack().setVisibility(View.VISIBLE);
        ((BaseActivity)getActivity()).getImgToolBarCancel().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getImgShare().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getTabLayout().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getTextNext().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getImgToolBarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).openSplashPage();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.text_signup, R.id.button_facebook, R.id.text_forgot, R.id.button_signin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_signup:
                ((BaseActivity)getActivity()).openSignupfacebookPage();
                break;
            case R.id.button_facebook:
                break;
            case R.id.text_forgot:
                ForgotFragment fragment = new ForgotFragment();
                Bundle bundle = new Bundle();
                bundle.putString("key","user");
                fragment.setArguments(bundle);
                addFragment(fragment,true);

                break;
            case R.id.button_signin:
                validation();
                break;
        }
    }
}
