package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.adapters.GridAdapter;
import com.sparecode.vipul.onlynow.model.LocationListData;
import com.sparecode.vipul.onlynow.model.LocationListWrapper;
import com.sparecode.vipul.onlynow.model.SignupWrapper;
import com.sparecode.vipul.onlynow.util.Prefs;
import com.sparecode.vipul.onlynow.view.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Signupstep2Fragment extends BaseFragment implements Signupstep2Backend.AreaListProvider, GridAdapter.GetSelectedLocation {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    GridAdapter gridAdapter;
    String Title2;

    GridLayoutManager gridLayoutManager;
    Signupstep2Backend signupstep2Backend;
    View view;
    List<LocationListData> data;

    public Signupstep2Fragment() {
        // Required empty public constructor
    }

    public static Signupstep2Fragment newInstance(String text) {
        Signupstep2Fragment fragment = new Signupstep2Fragment();
        Bundle args = new Bundle();
        args.putString("arg", text);
        fragment.setArguments(args);
        System.out.println("------>" + args);
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
        view = inflater.inflate(R.layout.fragment_signupstep2, container, false);
        ButterKnife.bind(this, view);
        getUser();
        data = new ArrayList<>();
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerview.setLayoutManager(gridLayoutManager);

        setPagination(recyclerview);
        Title2 = getArguments().getString("arg");
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        signupstep2Backend = new Signupstep2Backend(this, getActivity());
        signupstep2Backend.callPagination(1);
        gridAdapter = new GridAdapter(data, getActivity(), this);
        recyclerview.setAdapter(gridAdapter);
    }

    private void setPagination(RecyclerView recyclerview) {
        recyclerview.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.e("PAGINATION CALL::", "::" + page);
                signupstep2Backend.callPagination(page);
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
        // ((BaseActivity)getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        // ((BaseActivity)getActivity()).getTextViewToolBarTitle().setText(Title2);
    }

    @Override
    public void onSuccess(LocationListWrapper categoryWrapper) {
        if (getActivity() != null) {
            Log.e("::CATEGORY DATA::", "" + categoryWrapper.getData().size());
            data.addAll(categoryWrapper.getData());
            gridAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onError(String msg) {
        if (getActivity() != null) {
            //Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
        }
    }

    SignupWrapper signupWrapper;

    public SignupWrapper getSignupWrapper() {
        return signupWrapper;
    }

    public void setSignupWrapper(SignupWrapper signupWrapper) {
        this.signupWrapper = signupWrapper;
    }


    private void getUser() {
        signupWrapper = new Gson().fromJson(Prefs.getString("user", ""), SignupWrapper.class);
    }

    @Override
    public void getSelectedLocation(LocationListData locationListData, boolean flag) {
        Log.e("WHICH VIEW SELECTED::", "POS:" + locationListData.getId() + "FLAG:" + flag);
        if (signupWrapper != null) {
            if (flag) {
                signupstep2Backend.addFavoriteLocation(signupWrapper.getData().getId(), locationListData.getId(), "1.1", "2.2");
            } else {
                signupstep2Backend.removeFavoriteLocation(locationListData.getId());
            }
        } else {
            Snackbar.make(view, "Please finish step-1 to move ahead", Snackbar.LENGTH_SHORT).show();
        }
    }
}
