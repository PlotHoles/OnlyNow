package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.adapters.UserChangeLocationAdapter;
import com.sparecode.vipul.onlynow.model.LocationListWrapper;
import com.sparecode.vipul.onlynow.model.SignupWrapper;
import com.sparecode.vipul.onlynow.model.UserChangeLocationData;
import com.sparecode.vipul.onlynow.model.UserChangeLocationWrapper;
import com.sparecode.vipul.onlynow.util.Prefs;
import com.sparecode.vipul.onlynow.view.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChangeLocationFragment extends BaseFragment implements UserChangeLocationBackend.UserChangeLocationDataProvider,UserChangeLocationAdapter.GetSelectedLocation,Signupstep2Backend.AreaListProvider{

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    List<UserChangeLocationData> userChangeLocationDatas;
   UserChangeLocationAdapter userChangeLocationAdapter;
    GridLayoutManager gridLayoutManager;
    UserChangeLocationBackend userChangeLocationBackend;
    Signupstep2Backend signupstep2Backend;

    public ChangeLocationFragment() {
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
        View view = inflater.inflate(R.layout.fragment_change_location, container, false);
        ButterKnife.bind(this, view);
        getUser();
        signupstep2Backend = new Signupstep2Backend(this,getActivity());
        userChangeLocationBackend = new UserChangeLocationBackend(getActivity(),signupWrapper.getData().getId(),this);
        userChangeLocationBackend.callPagination(1);

        userChangeLocationDatas = new ArrayList<>();
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerview.setLayoutManager(gridLayoutManager);
        userChangeLocationAdapter = new UserChangeLocationAdapter(getActivity(),userChangeLocationDatas,this);
        recyclerview.setAdapter(userChangeLocationAdapter);
        setPagination(recyclerview);
        return view;
    }

    private void setPagination(RecyclerView recyclerview) {
        recyclerview.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.e("PAGINATION CALL::", "::" + page);
                userChangeLocationBackend.callPagination(page);
            }

            @Override
            public void MaxPage(int maxpage) {
                Log.e("::MAXPAGE::", "" + maxpage);
            }
        });
    }
    SignupWrapper signupWrapper;

    private void getUser() {
        signupWrapper = new Gson().fromJson(Prefs.getString("user", ""), SignupWrapper.class);
        Log.e("::", "SIGNUP WRAPPER::" + signupWrapper.getData());
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity)getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity)getActivity()).getTextViewToolBarTitle().setText("Select Active Area");
        ((BaseActivity)getActivity()).getImgToolBarBack().setVisibility(View.VISIBLE);
        ((BaseActivity)getActivity()).getImgToolBarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).openProfilePage();
            }
        });
    }

    @Override
    public void onSuccessfull(UserChangeLocationWrapper userChangeLocationWrapper) {
        userChangeLocationDatas.clear();
        userChangeLocationDatas.addAll(userChangeLocationWrapper.getData());
        userChangeLocationAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(String msg) {

    }


    @Override
    public void getSelectedLocation(UserChangeLocationData userChangeLocationData, boolean flag) {
        Log.e("WHICH VIEW SELECTED::", "POS:" +userChangeLocationData.getAId() + "FLAG:" + flag);
        getUser();
        if (flag) {
            signupstep2Backend.addFavoriteLocation(signupWrapper.getData().getId(), userChangeLocationData.getId(), "1.1", "2.2");
            userChangeLocationBackend = new UserChangeLocationBackend(getActivity(),signupWrapper.getData().getId(),this);
            userChangeLocationBackend.callPagination(1);
        } else {
            signupstep2Backend.removeFavoriteLocation(String.valueOf(userChangeLocationData.getAId()));
        }
    }

    @Override
    public void onSuccess(LocationListWrapper locationListWrapper) {

    }

    @Override
    public void onError(String msg) {

    }
}
