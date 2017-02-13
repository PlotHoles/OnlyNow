package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
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

public class DoneFragment extends BaseFragment {

    @Bind(R.id.button_done)
    Button buttonDone;
    @Bind(R.id.textView8)
    TextView textView8;
    @Bind(R.id.textView9)
    TextView textView9;

    public DoneFragment() {
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
        View view = inflater.inflate(R.layout.fragment_done, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText("Done");
        ((BaseActivity) getActivity()).getImgToolBarBack().setVisibility(View.INVISIBLE);
        final int gone = View.GONE;
        ((BaseActivity)getActivity()).getTextNext().setVisibility(gone);
                
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.button_done)
    public void onClick() {

        ((BaseActivity)getActivity()).openHomePage();
    }
}
