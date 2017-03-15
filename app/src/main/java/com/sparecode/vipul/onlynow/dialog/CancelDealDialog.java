package com.sparecode.vipul.onlynow.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.MainActivity;
import com.sparecode.vipul.onlynow.fragments.CancelDealBackend;
import com.sparecode.vipul.onlynow.model.CancelDealWrapper;
import com.sparecode.vipul.onlynow.model.LoginData;
import com.sparecode.vipul.onlynow.widgets.LatoButton;
import com.sparecode.vipul.onlynow.widgets.LatoEditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hitesh on 9/3/17.
 */

public class CancelDealDialog extends DialogFragment implements CancelDealBackend.cancelDealResultProvider{

    @Bind(R.id.edtReview)
    LatoEditText edtReview;
    @Bind(R.id.button_yes)
    LatoButton buttonYes;
    @Bind(R.id.button_no)
    LatoButton buttonNo;
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_canceldeal, container, false);
        ButterKnife.bind(this, view);

        getUserData();
        String userid = getUserData().getId();
        System.out.println("------->user"+userid);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Window window = getDialog().getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.button_yes, R.id.button_no})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_yes:
                CancelDealBackend cancelDealBackend = new CancelDealBackend(getActivity(),getUserData().getId(),edtReview.getText().toString(),this);


                break;
            case R.id.button_no:
                getDialog().dismiss();
                //Toast.makeText(getActivity(),"no",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    public LoginData getUserData() {
        if (getActivity() != null)
            return ((MainActivity)getActivity()).getUserData();
        return null;
    }
    @Override
    public void onSuccessfullLogin(CancelDealWrapper cancelDealWrapper) {
        Snackbar.make(view,cancelDealWrapper.getMessage(),Snackbar.LENGTH_SHORT).show();
        Toast.makeText(getActivity(),cancelDealWrapper.getMessage(),Toast.LENGTH_SHORT).show();
        getDialog().dismiss();
    }

    @Override
    public void onLoginFailure(String msg) {

    }
}
