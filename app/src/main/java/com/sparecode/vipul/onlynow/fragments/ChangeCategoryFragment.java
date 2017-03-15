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

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.adapters.LinearsAdapter;
import com.sparecode.vipul.onlynow.model.CategoryData;
import com.sparecode.vipul.onlynow.model.CategoryWrapper;
import com.sparecode.vipul.onlynow.model.UpdateLocationWrapper;
import com.sparecode.vipul.onlynow.view.EndlessRecyclerViewScrollListener;
import com.sparecode.vipul.onlynow.view.OnClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChangeCategoryFragment extends BaseFragment implements Signupstep3Backend.CategoryListProvider,UpdateCategoryBackend.UpdateCategoryResultProvider{

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    LinearsAdapter linearsAdapter;
    List<CategoryData> categoryDatas;
    private Signupstep3Backend signupstep3Backend;
    GridLayoutManager gridLayoutManager;
    String cat_id;
    private View view;
    public ChangeCategoryFragment() {
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

        view = inflater.inflate(R.layout.fragment_change_category, container, false);
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
        linearsAdapter = new LinearsAdapter(categoryDatas, getActivity(), new OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                cat_id = categoryDatas.get(position).getId();
                //Toast.makeText(getActivity(),cat_id,Toast.LENGTH_LONG).show();
                Log.e("position",cat_id);


            }
        });
        recyclerview.setAdapter(linearsAdapter);
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
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.selectcategory));
        ((BaseActivity) getActivity()).getImgToolBarBack().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextNext().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextNext().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UpdateCategoryBackend updateCategoryBackend = new UpdateCategoryBackend(getActivity(),getShopId(),cat_id,ChangeCategoryFragment.this);
               // Toast.makeText(getActivity(), cat_id, Toast.LENGTH_SHORT).show();
            }
        });
        ((BaseActivity) getActivity()).getImgToolBarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) getActivity()).openClientSettingPage();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onSuccess(CategoryWrapper categoryWrapper) {
        categoryDatas.addAll(categoryWrapper.getData());
        linearsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void onSuccesssfullLogin(UpdateLocationWrapper updateLocationWrapper) {
        Snackbar.make(view,"Change category Successfully",Snackbar.LENGTH_SHORT).show();
        ((BaseActivity)getActivity()).openClientSettingPage();
    }

    @Override
    public void onLoginFailure(String msg) {

    }
}
