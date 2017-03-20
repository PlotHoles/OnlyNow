package com.sparecode.vipul.onlynow.fragments;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.gson.GsonBuilder;
import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.model.FacebookWrapper;

import org.json.JSONObject;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupfacebookFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.image_logo)
    ImageView imageLogo;
    @Bind(R.id.text_des)
    TextView textDes;
    @Bind(R.id.button_signup_facebook)
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
    CallbackManager callbackManager;

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
        //FacebookSdk.sdkInitialize(getActivity());
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
                                        Log.e("FIRST NAME", "" + facebookWrapper.getFirstName());
                                        Log.e("LAST NAME", "" + facebookWrapper.getLastName());
                                        ((BaseActivity) getActivity()).openSignupPage(facebookWrapper);
                                        //((BaseActivity) getActivity()).openHomePage();

                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,first_name,last_name,name,link,email,gender,birthday");
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
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.signup));
        ((BaseActivity) getActivity()).getImgToolBarBack().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextNext().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).setOptionMenuVisibility(false);
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

    @OnClick({R.id.button_signup_facebook, R.id.button_signup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_signup_facebook:
                if (getActivity() != null) {
                    LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email", "user_birthday"));
                }
                break;
            case R.id.button_signup:
                ((BaseActivity) getActivity()).openSignupPage();
                break;
        }
    }
}
