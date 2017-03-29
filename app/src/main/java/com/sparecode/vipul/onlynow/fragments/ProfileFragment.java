package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.model.ChangePasswordBackendUser;
import com.sparecode.vipul.onlynow.model.LogoutWrapper;
import com.sparecode.vipul.onlynow.model.SelectCategoryWrapper;
import com.sparecode.vipul.onlynow.model.SignupWrapper;
import com.sparecode.vipul.onlynow.model.UpdateProfileWrapper;
import com.sparecode.vipul.onlynow.util.Prefs;
import com.sparecode.vipul.onlynow.view.CircleImageView;
import com.sparecode.vipul.onlynow.widgets.LatoButton;
import com.sparecode.vipul.onlynow.widgets.LatoEditText;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class ProfileFragment extends BaseFragment implements LogoutBackendUser.LogoutDataProvider, ProfileBackendUser.ProfileDataProvider, ChangePasswordBackendUser.ChangePasswordDataProvider, UpdateProfileBackendUser.UpdateProfileDataProvider, UpdateProfileBackendUser.ProfileUpdateDataProvider {


    @Bind(R.id.textUserName)
    LatoTextView textUserName;
    @Bind(R.id.textGender)
    LatoTextView textGender;
    @Bind(R.id.textUserBirthdate)
    LatoTextView textUserBirthdate;
    @Bind(R.id.textUserEmailId)
    LatoTextView textUserEmailId;
    @Bind(R.id.textEditProfile)
    LatoTextView textEditProfile;
    @Bind(R.id.imagePassword)
    ImageView imagePassword;
    @Bind(R.id.textChangePassword)
    LatoTextView textChangePassword;
    @Bind(R.id.imageUserLocation)
    ImageView imageUserLocation;
    @Bind(R.id.textUserLocation)
    LatoTextView textUserLocation;
    @Bind(R.id.ImageCategory)
    ImageView ImageCategory;
    @Bind(R.id.textUserFavCategory)
    LatoTextView textUserFavCategory;
    @Bind(R.id.emailswitch)
    Switch emailswitch;
    @Bind(R.id.textEmailSwitch)
    LatoTextView textEmailSwitch;
    @Bind(R.id.notificationswitch)
    Switch notificationswitch;
    @Bind(R.id.textNotificationSwitch)
    LatoTextView textNotificationSwitch;
    @Bind(R.id.imageQuestionAnswer)
    ImageView imageQuestionAnswer;
    @Bind(R.id.textQA)
    LatoTextView textQA;
    @Bind(R.id.imageProblem)
    ImageView imageProblem;
    @Bind(R.id.textProblem)
    LatoTextView textProblem;
    @Bind(R.id.imageAboutOnlynow)
    ImageView imageAboutOnlynow;
    @Bind(R.id.textAbout)
    LatoTextView textAbout;
    @Bind(R.id.imageBlog)
    ImageView imageBlog;
    @Bind(R.id.textBlog)
    LatoTextView textBlog;
    @Bind(R.id.imagePrivacyPolicy)
    ImageView imagePrivacyPolicy;
    @Bind(R.id.textPrivacyPolicy)
    LatoTextView textPrivacyPolicy;
    @Bind(R.id.imageTerms)
    ImageView imageTerms;
    @Bind(R.id.textTermsCondition)
    LatoTextView textTermsCondition;
    @Bind(R.id.imageLogout)
    ImageView imageLogout;
    @Bind(R.id.textLogout)
    LatoTextView textLogout;
    LogoutBackendUser logoutBackend;
    ChangePasswordBackendUser changePasswordBackend;
    Context mContext;
    @Bind(R.id.oldpassword)
    LatoEditText oldpassword;
    @Bind(R.id.newpassword)
    LatoEditText newpassword;
    @Bind(R.id.ChangePassword)
    LatoButton ChangePassword;
    @Bind(R.id.ClickChangePassword)
    LinearLayout ClickChangePassword;
    @Bind(R.id.UserImage)
    CircleImageView UserImage;
    View view;
    UpdateProfileBackendUser updateProfileBackend;
    ProfileBackendUser profileBackend;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        textUserLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).openChangeLocationPage();
                //Toast.makeText(getActivity(),"hi",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getUser();
        if (textUserName != null)
        {
            textUserName.setText(signupWrapper.getData().getFname() + " " + signupWrapper.getData().getLname());


        }
        if (textGender != null)
        {
            textGender.setText(signupWrapper.getData().getGender());

        }
        if (textUserBirthdate != null)
        {
            textUserBirthdate.setText(signupWrapper.getData().getBirthday());

        }
        if (textUserEmailId != null)
        {
            textUserEmailId.setText(signupWrapper.getData().getEmail());

        }
        if (UserImage != null)
        {
            if (signupWrapper.getData().getImageURL()!=null && !signupWrapper.getData().getImageURL().equals("")) {
                Log.e("imageprofile",signupWrapper.getData().getImageURL());
                Picasso.with(getActivity()).load(signupWrapper.getData().getImageURL()).resize(250, 250).placeholder(R.drawable.placeholder).into(UserImage);
            }
        }



        updateProfileBackend = new UpdateProfileBackendUser(getActivity(), signupWrapper.getData().getId(), this);

    }

    @OnCheckedChanged(R.id.emailswitch)
    void onEmailClick(boolean isChecked) {
        if (isChecked) {
            Toast.makeText(getActivity(), "ON", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "OFF", Toast.LENGTH_SHORT).show();
        }
    }

    @OnCheckedChanged(R.id.notificationswitch)
    void onNotificationClick(boolean isChecked) {
        if (isChecked) {
            Toast.makeText(getActivity(), "ON", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "OFF", Toast.LENGTH_SHORT).show();
        }
    }

    SignupWrapper signupWrapper;

    private void getUser() {
        signupWrapper = new Gson().fromJson(Prefs.getString("user", ""), SignupWrapper.class);
        Log.e("::", "SIGNUP WRAPPER::" + signupWrapper.getData());
    }

    @OnClick(R.id.textLogout)
    void onLogOut() {
        Prefs.clear();
        logoutBackend = new LogoutBackendUser(getActivity(), signupWrapper.getData().getId(), this);
        Toast.makeText(getActivity(), " Successfully Logged Out", Toast.LENGTH_LONG).show();
        ((BaseActivity) getActivity()).openSplashPage();
    }


    @OnClick(R.id.textUserFavCategory)
    void onClickSelectCategory() {
        profileBackend = new ProfileBackendUser(signupWrapper.getData().getImageURL(), getActivity(), this);
    }

    @OnClick(R.id.textChangePassword)
    void onClickChangePassword() {
        if (textChangePassword.isPressed())
            ClickChangePassword.setVisibility(View.VISIBLE);
    }


    @OnClick(R.id.ChangePassword)
    void onButtonClickChangePassword() {
        changePasswordBackend = new ChangePasswordBackendUser(getActivity(), signupWrapper.getData().getId(), oldpassword.getText().toString(), newpassword.getText().toString(), this);
    }

    @OnClick(R.id.textEditProfile)
    void onClickOpenUpdatePRofile() {
        ((BaseActivity) getActivity()).openUpdateProfile();
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText("Profile");
        ((BaseActivity) getActivity()).getTabLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgMap().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgShare().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgToolBarCancel().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgSettings().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgSearchMap().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgToolBarBack().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).setOptionMenuVisibility(false);
        ((BaseActivity) getActivity()).getTextNext().setVisibility(View.GONE);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onSuccess(LogoutWrapper logoutWrapper) {
        Snackbar.make(view, "Successfully Logged Out", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(ChangePasswordWrapper changePasswordWrapper) {
        Snackbar.make(view, "Password Changed Successfully", Snackbar.LENGTH_SHORT).show();
        ClickChangePassword.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(UpdateProfileWrapper updateProfileWrapper) {

    }

    @Override
    public void onSuccess(SignupWrapper profileUpdateWrapper) {
        signupWrapper = profileUpdateWrapper;
        if (textUserName != null)
        {
            textUserName.setText(signupWrapper.getData().getFname() + " " + signupWrapper.getData().getLname());
        }
        if (textGender != null)
        {
            textGender.setText(signupWrapper.getData().getGender());
        }
        if (textUserBirthdate != null)
        {
            textUserBirthdate.setText(signupWrapper.getData().getBirthday());

        }
        if (textUserEmailId != null)
        {
            textUserEmailId.setText(signupWrapper.getData().getEmail());

        }
        if (UserImage != null)
        {
            if (signupWrapper.getData().getImageURL()!=null && !signupWrapper.getData().getImageURL().equals("")) {
                Picasso.with(getActivity()).load(signupWrapper.getData().getImageURL()).resize(250, 250).into(UserImage);
            }
        }



    }

    @Override
    public void onSuccess(SelectCategoryWrapper selectCategoryWrapper) {
        Signupstep3Fragment signupstep3Fragment = ((BaseActivity) getActivity()).openSelectCategory(true);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.favourite_category));
        ((BaseActivity) getActivity()).getAppbarLayout().findViewById(R.id.imgSettings).setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getTabLayout().setVisibility(View.GONE);
//        ((BaseActivity) getActivity()).getTextNext().setVisibility(View.VISIBLE);
        signupstep3Fragment.setSelectCategoryWrapper(selectCategoryWrapper);

    }

    @Override
    public void onFailure(String msg) {

        if (getActivity() != null) {
            Snackbar.make(view, "Enter Your Password", Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(view, "Wrong Old Password Given", Snackbar.LENGTH_SHORT).show();
        }

    }
}