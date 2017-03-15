package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.activity.MainActivity;
import com.sparecode.vipul.onlynow.model.LoginWrapper;
import com.sparecode.vipul.onlynow.widgets.LatoButton;
import com.sparecode.vipul.onlynow.widgets.LatoEditText;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClientSigninFragment extends BaseFragment implements ClientSigninBackend.ClientSigninResultProvider {


    @Bind(R.id.image_logo)
    ImageView imageLogo;
    @Bind(R.id.text_des)
    LatoTextView textDes;
    @Bind(R.id.edit_email)
    LatoEditText editEmail;
    @Bind(R.id.edit_password)
    LatoEditText editPassword;
    @Bind(R.id.text_forgot)
    LatoTextView textForgot;
    @Bind(R.id.button_signin)
    LatoButton buttonSignin;
    @Bind(R.id.text_signups)
    LatoTextView textSignups;
    private View view;


    public ClientSigninFragment() {
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
        view = inflater.inflate(R.layout.fragment_client_signin, container, false);
        ButterKnife.bind(this, view);
        textSignups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "hi", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    void validation() {
        if (editEmail.getText().toString().trim().length() == 0) {
            editEmail.setError(getString(R.string.email));
            editEmail.requestFocus();
        } else if (editPassword.getText().toString().trim().length() == 0) {
            editPassword.setError(getString(R.string.password));
            editPassword.requestFocus();
        } else {
            ClientSigninBackend clientSigninBackend = new ClientSigninBackend(getActivity(), editEmail.getText().toString(), editPassword.getText().toString(), "A", "0000", this);
        }

    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgToolBarBack().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.sign));
        ((BaseActivity) getActivity()).getTabLayoutclient().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getTextNext().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgToolBarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) getActivity()).openClientSplashPage();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.text_signups, R.id.text_forgot, R.id.button_signin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_signups:
                Toast.makeText(getActivity(), "hi", Toast.LENGTH_SHORT).show();
                ((BaseActivity) getActivity()).openClientSignupPage();
                break;
            case R.id.text_forgot:
                ForgotFragment fragment = new ForgotFragment();
                Bundle bundle = new Bundle();
                bundle.putString("key", "client");
                fragment.setArguments(bundle);
                addFragment(fragment, true);
                //((BaseActivity)getActivity()).openForgotPage();
                break;
            case R.id.button_signin:
                validation();
                break;
        }
    }

    @Override
    public void onSuccessfullLogin(LoginWrapper loginWrapper) {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).setUserData(loginWrapper.getData());
            ((BaseActivity) getActivity()).openClientCouponPage();
        }

    }

    @Override
    public void onLoginFailure(String msg) {
        if (getActivity() != null) {
            Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
        }
    }


}
