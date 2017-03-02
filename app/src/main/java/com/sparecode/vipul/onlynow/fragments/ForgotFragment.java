package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotFragment extends BaseFragment {

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
        View view = inflater.inflate(R.layout.fragment_forgot, container, false);
        ButterKnife.bind(this, view);

            usertype = getArguments().getString("key");
            Log.e("-------->usertype",usertype);


        return view;
    }

    void validation()
    {
        if (editForgotemail.getText().toString().trim().length() == 0)
        {
            editForgotemail.setError(getString(R.string.email));
        }
        else
        {
            ResetFragment fragment = new ResetFragment();
            Bundle bundle = new Bundle();
            bundle.putString("key",usertype);
            fragment.setArguments(bundle);
            addFragment(fragment,true);
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
        ((BaseActivity)getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity)getActivity()).getTextViewToolBarTitle().setText(getString(R.string.forgotpassword));
        ((BaseActivity)getActivity()).getImgToolBarBack().setVisibility(View.VISIBLE);
        ((BaseActivity)getActivity()).getTextNext().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getImgToolBarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usertype.equalsIgnoreCase("client"))
                {
                    ((BaseActivity)getActivity()).openClientSigninPage();
                }
                else if (usertype.equalsIgnoreCase("user"))
                {
                    ((BaseActivity)getActivity()).openSigninPage();
                }

            }
        });
    }
}
