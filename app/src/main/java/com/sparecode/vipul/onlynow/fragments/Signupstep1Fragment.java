package com.sparecode.vipul.onlynow.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.widgets.LatoCheckBox;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sparecode.vipul.onlynow.R.id.text_signin;

public class Signupstep1Fragment extends BaseFragment {

    String Title1;
    @Bind(R.id.text_already)
    TextView textAlready;
    @Bind(text_signin)
    TextView textSignin;
    @Bind(R.id.edit_firstname)
    EditText editFirstname;
    @Bind(R.id.edit_lastname)
    EditText editLastname;
    @Bind(R.id.edit_email)
    EditText editEmail;
    @Bind(R.id.edit_confirmemail)
    EditText editConfirmemail;
    @Bind(R.id.edit_password)
    EditText editPassword;
    @Bind(R.id.edit_birthday)
    EditText editBirthday;
    @Bind(R.id.radioButton)
    RadioButton radioButton;
    @Bind(R.id.radioButton2)
    RadioButton radioButton2;
    @Bind(R.id.buttonNext)
    Button buttonNext;
    @Bind(R.id.scrollview)
    ScrollView scrollview;
    @Bind(R.id.agreecheck)
    LatoCheckBox agreecheck;
    @Bind(R.id.receivecheck)
    LatoCheckBox receivecheck;
    @Bind(R.id.genderradiogroup)
    RadioGroup genderradiogroup;
    private int mYear, mMonth, mDay;

    public Signupstep1Fragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Signupstep1Fragment newInstance(String text) {
        Signupstep1Fragment fragment = new Signupstep1Fragment();
        Bundle args = new Bundle();
        args.putString("arg", text);
        fragment.setArguments(args);
        System.out.println("------>" + text);
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
        View view = inflater.inflate(R.layout.fragment_signupstep1, container, false);

        Title1 = getArguments().getString("arg");
        System.out.println("------>" + Title1);
        ButterKnife.bind(this, view);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        return view;
    }

    void validation() {
        boolean strigmatches = editEmail.getText().toString().trim().matches(editConfirmemail.getText().toString());

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        System.out.println("------>string" + strigmatches);
        System.out.println("-------first>" + editFirstname.getText().toString());

        boolean emailmatches = editEmail.getText().toString().trim().matches(emailPattern);
        boolean agree = agreecheck.isChecked();
        boolean receive = receivecheck.isChecked();

        String gender = String.valueOf(genderradiogroup.getCheckedRadioButtonId());
        System.out.println("----->gender" + gender);
        boolean gender1 = gender.matches("-1");
        System.out.println("----->gender1"+gender1);

        if (editFirstname.getText().toString().trim().length() == 0) {
            editFirstname.setError(getString(R.string.first_name));
            editFirstname.requestFocus();
        } else if (editLastname.getText().toString().trim().length() == 0) {
            editLastname.setError(getString(R.string.last_name));
            editLastname.requestFocus();
        } else if (editEmail.getText().toString().trim().length() == 0) {
            editEmail.setError(getString(R.string.email));
            editEmail.requestFocus();
        } else if (emailmatches != true) {
            editEmail.setError(getString(R.string.email_proper));
        } else if (editConfirmemail.getText().toString().trim().length() == 0) {
            editConfirmemail.setError(getString(R.string.confirm_email));
            editConfirmemail.requestFocus();
        } else if (strigmatches != true) {
            editConfirmemail.setError(getString(R.string.match_email));
        } else if (editPassword.getText().toString().trim().length() == 0) {
            editPassword.setError(getString(R.string.password));
            editPassword.requestFocus();
        } else if ((editBirthday.getText().toString().trim().length() == 0)) {
            editBirthday.setError(getString(R.string.birthdate));
            editBirthday.requestFocus();
        } else if (gender1 == true){
            Toast.makeText(getContext(), R.string.gender,Toast.LENGTH_SHORT).show();
        } else if (agree != true){
            Toast.makeText(getContext(),R.string.agree,Toast.LENGTH_SHORT).show();
        }
        else {
            ((SignupFragment) getParentFragment()).performNext();
        }
    }

    @OnClick(R.id.text_signin)
    void onSignInClick() {
        ((BaseActivity) getActivity()).openSigninPage();
    }

    @OnClick(R.id.buttonNext)
    void onNextButtonClick() {
        validation();

    }

    @Override
    public void setToolbarForFragment() {
        // ((BaseActivity)getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        // ((BaseActivity)getActivity()).getTextViewToolBarTitle().setText(Title1);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.edit_birthday)
    public void onClick() {
       DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        editBirthday.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
