package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.adapters.NoticeAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NoticeFragment extends BaseFragment {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    NoticeAdapter noticeAdapter;

    public NoticeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_notice, container, false);
        ButterKnife.bind(this, view);

        noticeAdapter = new NoticeAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
        recyclerview.setLayoutManager(gridLayoutManager);
        recyclerview.setAdapter(noticeAdapter);

        return view;
    }


    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText("Notice");
        ((BaseActivity) getActivity()).getTabLayout().setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
