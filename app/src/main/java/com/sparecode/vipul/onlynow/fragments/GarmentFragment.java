package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.adapters.CardAdapter;
import com.sparecode.vipul.onlynow.view.OnClickListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GarmentFragment extends Fragment {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    CardAdapter cardAdapter;

    public GarmentFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static GarmentFragment newInstance(String text) {
        GarmentFragment fragment = new GarmentFragment();
        Bundle args = new Bundle();
        args.putString("arg", text);
        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_garment, container, false);
        ButterKnife.bind(this, view);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerview.setLayoutManager(gridLayoutManager);
        cardAdapter = new CardAdapter(getActivity(),new OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                if (getActivity() != null)
                    ((BaseActivity)getActivity()).openDetailPage();
            }
        });
        recyclerview.setAdapter(cardAdapter);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.recyclerview)
    public void onClick() {
        Toast.makeText(getActivity(),"hi",Toast.LENGTH_SHORT).show();
    }
}
