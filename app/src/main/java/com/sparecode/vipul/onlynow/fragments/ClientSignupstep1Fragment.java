package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.widgets.LatoButton;
import com.sparecode.vipul.onlynow.widgets.LatoCheckBox;
import com.sparecode.vipul.onlynow.widgets.LatoEditText;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClientSignupstep1Fragment extends Fragment {

    @Bind(R.id.text_already)
    LatoTextView textAlready;
    @Bind(R.id.text_signin)
    LatoTextView textSignin;
    @Bind(R.id.imageView19)
    ImageView imageView19;
    @Bind(R.id.textView73)
    TextView textView73;
    @Bind(R.id.edit_firstname)
    LatoEditText editFirstname;
    @Bind(R.id.edit_lastname)
    LatoEditText editLastname;
    @Bind(R.id.edit_clientname)
    LatoEditText editClientname;
    @Bind(R.id.edit_location)
    LatoEditText editLocation;
    @Bind(R.id.edit_zipcode)
    LatoEditText editZipcode;
    @Bind(R.id.edit_prefecture)
    LatoEditText editPrefecture;
    @Bind(R.id.edit_cityname)
    LatoEditText editCityname;
    @Bind(R.id.edit_street)
    LatoEditText editStreet;
    @Bind(R.id.edit_build)
    LatoEditText editBuild;
    @Bind(R.id.edit_phone)
    LatoEditText editPhone;
    @Bind(R.id.edit_email)
    LatoEditText editEmail;
    @Bind(R.id.edit_confirmemail)
    LatoEditText editConfirmemail;
    @Bind(R.id.edit_password)
    LatoEditText editPassword;
    @Bind(R.id.edit_website)
    LatoEditText editWebsite;
    @Bind(R.id.agreecheck)
    LatoCheckBox agreecheck;
    @Bind(R.id.buttonNext)
    LatoButton buttonNext;
    @Bind(R.id.scrollview)
    ScrollView scrollview;

    public ClientSignupstep1Fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ClientSignupstep1Fragment newInstance(String text) {
        ClientSignupstep1Fragment fragment = new ClientSignupstep1Fragment();
        Bundle args = new Bundle();
        args.putString("msg", text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_client_signupstep1, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    void validation() {
        boolean strigmatches = editEmail.getText().toString().trim().matches(editConfirmemail.getText().toString());
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        boolean emailmatches = editEmail.getText().toString().trim().matches(emailPattern);
        boolean agree = agreecheck.isChecked();

        if (editFirstname.getText().toString().trim().length() == 0) {
            editFirstname.setError(getString(R.string.first_name));
            editFirstname.requestFocus();
        } else if (editLastname.getText().toString().trim().length() == 0) {
            editLastname.setError(getString(R.string.last_name));
            editLastname.requestFocus();
        } else if (editClientname.getText().toString().trim().length() == 0) {
            editClientname.setError(getString(R.string.client_name));
            editClientname.requestFocus();
        } else if (editLocation.getText().toString().trim().length() == 0) {
            editLocation.setError(getString(R.string.locationareaclient));
            editLocation.requestFocus();
        } else if (editZipcode.getText().toString().trim().length() == 0) {
            editZipcode.setError(getString(R.string.zipcodeclient));
            editZipcode.requestFocus();
        } else if (editPrefecture.getText().toString().trim().length() == 0) {
            editPrefecture.setError(getString(R.string.prefec));
            editPrefecture.requestFocus();
        } else if (editCityname.getText().toString().toString().length() == 0) {
            editCityname.setError(getString(R.string.city));
            editCityname.requestFocus();
        } else if (editStreet.getText().toString().trim().length() == 0) {
            editStreet.setError(getString(R.string.streetnamnumber));
            editStreet.requestFocus();
        } else if (editBuild.getText().toString().trim().length() == 0) {
            editBuild.setError(getString(R.string.build));
            editBuild.requestFocus();
        } else if (editPhone.getText().toString().trim().length() == 0) {
            editPhone.setError(getString(R.string.phonenumbre));
            editPhone.requestFocus();
        } else if (editEmail.getText().toString().trim().length() == 0) {
            editEmail.setError(getString(R.string.email));
            editEmail.requestFocus();
        } else if (emailmatches != true) {
            editEmail.setError(getString(R.string.email_proper));
            editEmail.requestFocus();
        } else if (editConfirmemail.getText().toString().trim().length() == 0) {
            editConfirmemail.setError(getString(R.string.confirm_email));
            editConfirmemail.requestFocus();
        } else if (strigmatches != true) {
            editConfirmemail.setError(getString(R.string.match_email));
        } else if (editPassword.getText().toString().trim().length() == 0) {
            editPassword.setError(getString(R.string.password));
            editPassword.requestFocus();
        } else if (editWebsite.getText().toString().trim().length() == 0) {
            editWebsite.setError(getString(R.string.website));
            editWebsite.requestFocus();
        } else if (agree != true) {
            Toast.makeText(getContext(), R.string.agree, Toast.LENGTH_SHORT).show();
        } else {
            ((ClientSignupFragment) getParentFragment()).performNext();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.text_signin, R.id.buttonNext})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_signin:
                ((BaseActivity)getActivity()).openClientSigninPage();
                break;
            case R.id.buttonNext:
                validation();
                break;
        }
    }

//    @OnClick(R.id.buttonNext)
//    public void onClick() {
//
//
//    }


}
