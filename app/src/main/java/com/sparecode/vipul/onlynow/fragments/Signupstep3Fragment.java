package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.adapters.LinearAdapter;
import com.sparecode.vipul.onlynow.model.CategoryData;
import com.sparecode.vipul.onlynow.model.CategoryWrapper;
import com.sparecode.vipul.onlynow.model.SelectCategoryWrapper;
import com.sparecode.vipul.onlynow.model.SignupWrapper;
import com.sparecode.vipul.onlynow.util.Prefs;
import com.sparecode.vipul.onlynow.view.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Signupstep3Fragment extends BaseFragment implements Signupstep3Backend.CategoryListProvider, LinearAdapter.GetSelectedCategory {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    LinearAdapter linearAdapter;
    String Title3;

    List<CategoryData> categoryDatas;
    GridLayoutManager gridLayoutManager;
    SelectCategoryWrapper  selectCategoryWrapper;
    private boolean isChangeCategory;

    public Signupstep3Fragment() {
        // Required empty public constructor
    }
    public Signupstep3Fragment(boolean isChangeCategory) {
        this.isChangeCategory = isChangeCategory;
    }
    public static Signupstep3Fragment newInstance(String text) {
        Signupstep3Fragment fragment = new Signupstep3Fragment();
        Bundle args = new Bundle();
        args.putString("arg", text);
        fragment.setArguments(args);
        System.out.println("------>" + args);
        return fragment;
    }
    public void setSelectCategoryWrapper(SelectCategoryWrapper selectCategoryWrapper)
    {
        this.selectCategoryWrapper=selectCategoryWrapper;
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


        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerview.setLayoutManager(gridLayoutManager);
        int oldFocusability = recyclerview.getDescendantFocusability();
        recyclerview.setItemAnimator(null);
        recyclerview.setDescendantFocusability(recyclerview.FOCUS_BLOCK_DESCENDANTS);
        recyclerview.setDescendantFocusability(oldFocusability);
        recyclerview.setHasFixedSize(true);
        // linearAdapter.setHasStableIds(false);
        if (getArguments() != null) {
            if (getArguments().getString("arg") != null)
                Title3 = getArguments().getString("arg");
        }
        return view;
    }

    private Signupstep3Backend signupstep3Backend;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        signupstep3Backend = new Signupstep3Backend(this, getActivity());
        categoryDatas = new ArrayList<>();
        linearAdapter = new LinearAdapter(categoryDatas, getActivity(), this);
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
        // ((BaseActivity)getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        //  ((BaseActivity)getActivity()).getTextViewToolBarTitle().setText(Title3);
    }

    @Override
    public void onSuccess(CategoryWrapper categoryWrapper) {
        categoryDatas.addAll(categoryWrapper.getData());
        linearAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String msg) {
    }

    SignupWrapper signupWrapper;

    private void getUser() {
        signupWrapper = new Gson().fromJson(Prefs.getString("user", ""), SignupWrapper.class);
        Log.e("::", "SIGNUP WRAPPER::" + signupWrapper.getData());
    }

    @Override
    public void getSelectedCategory(CategoryData categoryData, boolean flag) {
        Log.e("WHICH VIEW SELECTED::", "POS:" + categoryData.getId() + "FLAG:" + flag);
        getUser();
        if (flag) {
            signupstep3Backend.addFavoriteCategory(signupWrapper.getData().getId(), categoryData.getId());
        } else {
            signupstep3Backend.removeFavoriteCategory(categoryData.toString());
        }
    }
}
