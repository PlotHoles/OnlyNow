package com.sparecode.vipul.onlynow.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.model.ForgotWrapper;
import com.sparecode.vipul.onlynow.widgets.LatoEditText;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotFragment extends BaseFragment implements ForgotBackend.ForgotDataProvider {

    @Bind(R.id.edit_forgotemail)
    EditText editForgotemail;
    @Bind(R.id.button_forgot)
    Button buttonForgot;
    @Bind(R.id.linearLayout3)
    LinearLayout linearLayout3;
    @Bind(R.id.textView4)
    TextView textView4;
    @Bind(R.id.textView5)
    TextView textView5;
    String usertype;
    View view;
    @Bind(R.id.edit_forgotbirthday)
    LatoTextView editForgotbirthday;
    @Bind(R.id.edit_forgotpassword)
    LatoEditText editForgotpassword;
    @Bind(R.id.edit_forgotconfirmpassword)
    LatoEditText editForgotconfirmpassword;
    private int mYear, mMonth, mDay;

    public ForgotFragment() {
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

        view = inflater.inflate(R.layout.fragment_forgot, container, false);
        ButterKnife.bind(this, view);

        usertype = getArguments().getString("key");
        Log.e("-------->usertype", usertype);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        editForgotbirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                editForgotbirthday.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        return view;
    }

    void validation() {

        boolean strigmatches = editForgotpassword.getText().toString().trim().matches(editForgotconfirmpassword.getText().toString());
        if (editForgotemail.getText().toString().trim().length() == 0) {
            editForgotemail.setError(getString(R.string.email));
        } else if (editForgotpassword.getText().toString().trim().length() == 0){
            editForgotpassword.setError(getString(R.string.passsword));
        } else if (editForgotconfirmpassword.getText().toString().trim().length() == 0){
            editForgotconfirmpassword.setError(getString(R.string.confirmpassword));
        } else if (strigmatches != true){
            editForgotconfirmpassword.setError(getString(R.string.match_password));
        }
        else {
            ForgotBackend forgotBackend = new ForgotBackend(getActivity(), editForgotemail.getText().toString().trim(),editForgotpassword.getText().toString().trim(),editForgotbirthday.getText().toString().trim(), this);
            //((BaseActivity)getActivity()).openResetPage();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.button_forgot)
    public void onClick() {

        validation();
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.forgotpassword));
        ((BaseActivity) getActivity()).getImgToolBarBack().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextNext().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgToolBarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usertype.equalsIgnoreCase("client")) {
                    ((BaseActivity) getActivity()).openClientSigninPage();
                } else if (usertype.equalsIgnoreCase("user")) {
                    ((BaseActivity) getActivity()).openSigninPage();
                }

            }
        });
    }

    @Override
    public void onSuccessfull(ForgotWrapper forgotWrapper) {

        Toast.makeText(getActivity(), forgotWrapper.getMessage(), Toast.LENGTH_SHORT).show();
        ResetFragment fragment = new ResetFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", usertype);
        fragment.setArguments(bundle);
        addFragment(fragment, true);

    }

    @Override
    public void onFailure(String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }
}
