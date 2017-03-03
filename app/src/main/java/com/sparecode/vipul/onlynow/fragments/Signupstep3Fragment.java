package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.adapters.LinearAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Signupstep3Fragment extends BaseFragment {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    LinearAdapter linearAdapter;
    String Title3;

    public Signupstep3Fragment() {
        // Required empty public constructor
    }

    public static Signupstep3Fragment newInstance(String text) {
        Signupstep3Fragment fragment = new Signupstep3Fragment();
        Bundle args = new Bundle();
        args.putString("arg", text);
        fragment.setArguments(args);
        System.out.println("------>"+args);
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
        View view = inflater.inflate(R.layout.fragment_signupstep3, container, false);
        ButterKnife.bind(this, view);


       // linearAdapter = new LinearAdapter();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
        recyclerview.setLayoutManager(gridLayoutManager);
        //recyclerview.setAdapter(linearAdapter);
        //linearAdapter.notifyDataSetChanged();
        int oldFocusability = recyclerview.getDescendantFocusability();
        recyclerview.setItemAnimator(null);
        recyclerview.setDescendantFocusability(recyclerview.FOCUS_BLOCK_DESCENDANTS);
        recyclerview.setDescendantFocusability(oldFocusability);
        recyclerview.setHasFixedSize(true);
       // linearAdapter.setHasStableIds(false);
        Title3 = getArguments().getString("arg");
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setToolbarForFragment() {
       // ((BaseActivity)getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
      //  ((BaseActivity)getActivity()).getTextViewToolBarTitle().setText(Title3);
    }
}
