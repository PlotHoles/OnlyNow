package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class ProfileFragment extends BaseFragment {


    @Bind(R.id.textView13)
    TextView textView13;
    @Bind(R.id.textView14)
    TextView textView14;
    @Bind(R.id.textView16)
    TextView textView16;
    @Bind(R.id.textView17)
    TextView textView17;
    @Bind(R.id.textView18)
    TextView textView18;
    @Bind(R.id.imageView7)
    ImageView imageView7;
    @Bind(R.id.textView19)
    TextView textView19;
    @Bind(R.id.imageView8)
    ImageView imageView8;
    @Bind(R.id.textView20)
    TextView textView20;
    @Bind(R.id.imageView10)
    ImageView imageView10;
    @Bind(R.id.textView22)
    TextView textView22;
    @Bind(R.id.emailswitch)
    Switch emailswitch;
    @Bind(R.id.textView23)
    TextView textView23;
    @Bind(R.id.textView21)
    TextView textView21;
    @Bind(R.id.imageView12)
    ImageView imageView12;
    @Bind(R.id.textView24)
    TextView textView24;
    @Bind(R.id.imageView13)
    ImageView imageView13;
    @Bind(R.id.textView25)
    TextView textView25;
    @Bind(R.id.imageView14)
    ImageView imageView14;
    @Bind(R.id.textView26)
    TextView textView26;
    @Bind(R.id.imageView15)
    ImageView imageView15;
    @Bind(R.id.textView27)
    TextView textView27;
    @Bind(R.id.imageView16)
    ImageView imageView16;
    @Bind(R.id.textView28)
    TextView textView28;
    @Bind(R.id.imageView17)
    ImageView imageView17;
    @Bind(R.id.textView29)
    TextView textView29;
    @Bind(R.id.imageView18)
    ImageView imageView18;
    @Bind(R.id.textView30)
    TextView textView30;
    @Bind(R.id.notificationswitch)
    Switch notificationswitch;

    public ProfileFragment() {
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
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
    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText("Profile");
        ((BaseActivity) getActivity()).getTabLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgMap().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgShare().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgToolBarCancel().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgSettings().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getImgSearchMap().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getImgToolBarBack().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).setOptionMenuVisibility(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
