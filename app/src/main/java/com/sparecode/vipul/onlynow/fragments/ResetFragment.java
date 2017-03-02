package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetFragment extends BaseFragment {

    @Bind(R.id.button_signin)
    Button buttonSignin;
    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.textView7)
    TextView textView7;
    String usertype;

    public ResetFragment() {
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
        View view = inflater.inflate(R.layout.fragment_reset, container, false);
        ButterKnife.bind(this, view);

            usertype = getArguments().getString("key");
            Log.e("-------->usertype",usertype);



        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.button_signin)
    public void onClick() {

            if (usertype.equalsIgnoreCase("client"))
            {
                ((BaseActivity)getActivity()).openClientSigninPage();
            }
            else if (usertype.equalsIgnoreCase("user"))
            {
                ((BaseActivity)getActivity()).openSigninPage();
            }
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity)getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity)getActivity()).getTextViewToolBarTitle().setText(getString(R.string.resetpassword));
        ((BaseActivity)getActivity()).getImgToolBarBack().setVisibility(View.VISIBLE);
        ((BaseActivity)getActivity()).getTextNext().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getImgToolBarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgotFragment fragment = new ForgotFragment();
                Bundle bundle = new Bundle();
                bundle.putString("key",usertype);
                fragment.setArguments(bundle);
                addFragment(fragment,true);
                //((BaseActivity)getActivity()).openForgotPage();
            }
        });
    }
}
