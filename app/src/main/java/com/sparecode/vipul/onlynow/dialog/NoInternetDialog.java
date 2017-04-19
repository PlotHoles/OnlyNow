package com.sparecode.vipul.onlynow.dialog;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.util.NetworkUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hitesh on 22/2/17.
 */

public class NoInternetDialog extends DialogFragment {
    @Bind(R.id.imgRefresh)
    ImageView imgRefresh;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_nointernet, container, false);
        //setCancelable(false);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);

        ButterKnife.bind(this, view);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        Window window = getDialog().getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        super.onActivityCreated(savedInstanceState);

        ViewGroup.LayoutParams attributes = window.getAttributes();
        //must setBackgroundDrawable(TRANSPARENT) in onActivityCreated()
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }

    @OnClick(R.id.imgRefresh)
    void onRefreshIconClick() {

        Animation a = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);
        a.setDuration(1500);
        imgRefresh.startAnimation(a);

        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (NetworkUtil.getConnectivityBoolStatus(getActivity())) {
                    imgRefresh.setVisibility(View.GONE);
                    dismiss();
                } else {
                    imgRefresh.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (!NetworkUtil.getConnectivityBoolStatus(getActivity())) {
            getActivity().finish();
        } else {
            dismiss();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
