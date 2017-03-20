package com.sparecode.vipul.onlynow.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.model.FacebookWrapper;
import com.sparecode.vipul.onlynow.model.SigninWrapper;
import com.sparecode.vipul.onlynow.util.Prefs;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SigninFragment extends BaseFragment implements SigninBackend.SigninResultProvider {

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
    CallbackManager callbackManager;
    private View view;

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

        view = inflater.inflate(R.layout.fragment_signin, container, false);
        printKeyHash();
        ButterKnife.bind(this, view);

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Log.e(":::Token", "" + loginResult.getAccessToken());
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        Log.e("JSON OBJ", "JSON OBJ::" + object);
                                        // Application code
                                        GsonBuilder gsonBuilder = new GsonBuilder();
                                        FacebookWrapper facebookWrapper = gsonBuilder.create().fromJson(object.toString(), FacebookWrapper.class);
                                        //((BaseActivity) getActivity()).openHomePage();
                                        @SuppressLint("HardwareIds") String android_id = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                                        SigninBackend signinBackend = new SigninBackend(getActivity(), facebookWrapper.getId(), editEmail.getText().toString(), editPassword.getText().toString(), "1.1", "1.2", "A", "" + android_id, SigninFragment.this);
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,link,email,gender,birthday");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Log.e("EXCEPTION::", "" + exception.toString());
                    }
                });

        return view;
    }


    /*private boolean doValidate() {
        if (editEmail.getText().toString().trim().equals("")) {
            editEmail.setError(getString(R.string.email));
            return false;
        } else if (editPassword.getText().toString().trim().equals("")) {
            editPassword.setError(getString(R.string.password));
            return false;
        } else {
            return true;
        }
    }*/

    void validation() {
        if (editEmail.getText().toString().trim().length() == 0) {
            editEmail.setError(getString(R.string.email));
        } else if (editPassword.getText().toString().trim().length() == 0) {
            editPassword.setError(getString(R.string.password));
        } else {
            @SuppressLint("HardwareIds") String android_id = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            SigninBackend signinBackend = new SigninBackend(getActivity(), editEmail.getText().toString(), editPassword.getText().toString(), "1.1", "1.2", "A", "" + android_id, this);
        }
    }

    @OnClick(R.id.button_facebook)
    void onFacebookClick() {
        if (getActivity() != null) {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email", "user_birthday"));
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void printKeyHash() {
        try {
            PackageInfo info = getActivity().getPackageManager().getPackageInfo(
                    "com.sparecode.vipul.onlynow",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.sign));
        ((BaseActivity) getActivity()).getImgToolBarBack().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgToolBarCancel().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgShare().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getTabLayout().setVisibility(View.GONE);
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

    @OnClick({R.id.text_signup, R.id.button_facebook, R.id.text_forgot, R.id.button_signin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_signup:
                ((BaseActivity) getActivity()).openSignupfacebookPage();
                break;
            case R.id.button_facebook:
                break;
            case R.id.text_forgot:
                ForgotFragment fragment = new ForgotFragment();
                Bundle bundle = new Bundle();
                bundle.putString("key", "user");
                fragment.setArguments(bundle);
                addFragment(fragment, true);

                break;
            case R.id.button_signin:
                validation();
                break;
        }
    }

    @Override
    public void onSuccessFulLogin(SigninWrapper signinWrapper) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).openDonePage();
            Prefs.putString("user",new Gson().toJson(signinWrapper));
        }
    }

    @Override
    public void onLoginFailure(String msg) {
        if (getActivity() != null) {
            Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
        }
    }
}
