package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.adapters.LinearAdapter;
import com.sparecode.vipul.onlynow.model.CategoryData;
import com.sparecode.vipul.onlynow.model.CategoryWrapper;
import com.sparecode.vipul.onlynow.view.EndlessRecyclerViewScrollListener;
import com.sparecode.vipul.onlynow.view.OnClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ClientSignupstep2Fragment extends BaseFragment implements Signupstep3Backend.CategoryListProvider{


    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    LinearAdapter linearAdapter;
    List<CategoryData> categoryDatas;
    private Signupstep3Backend signupstep3Backend;
    GridLayoutManager gridLayoutManager;

    public ClientSignupstep2Fragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ClientSignupstep2Fragment newInstance(String text) {
        ClientSignupstep2Fragment fragment = new ClientSignupstep2Fragment();
        Bundle args = new Bundle();
        args.putString("msg", text);
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
        View view = inflater.inflate(R.layout.fragment_client_signupstep2, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        categoryDatas = new ArrayList<>();
        signupstep3Backend = new Signupstep3Backend(this, getActivity());
        gridLayoutManager = new GridLayoutManager(getActivity(),1);
        recyclerview.setLayoutManager(gridLayoutManager);
        linearAdapter = new LinearAdapter(categoryDatas, getActivity(), new OnClickListener() {
            @Override
            public void onItemClicked(int position) {
               // Toast.makeText(getActivity(),"hi",Toast.LENGTH_LONG).show();
                Log.e("position",position+"");
            }
        });


        recyclerview.setAdapter(linearAdapter);
        signupstep3Backend.callPagination(1);
        setPagination(recyclerview);

    }

    private void setPagination(RecyclerView recyclerview) {
        recyclerview.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.e("PAGINATION CALL::", "::" + page);
                signupstep3Backend.callPagination(page);
            }

            @Override
            public void MaxPage(int maxpage) {
                Log.e("::MAXPAGE::", "" + maxpage);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setToolbarForFragment() {

    }

    @Override
    public void onSuccess(CategoryWrapper categoryWrapper) {
        categoryDatas.addAll(categoryWrapper.getData());
        linearAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String msg) {

    }
}
