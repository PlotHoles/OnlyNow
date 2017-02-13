package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.adapters.SearchAdapter;
import com.sparecode.vipul.onlynow.adapters.SearchCategoryAdapter;
import com.sparecode.vipul.onlynow.adapters.SearchResultAdapter;
import com.sparecode.vipul.onlynow.view.OnClickListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchFragment extends BaseFragment {

    SearchAdapter searchAdapter;
    SearchCategoryAdapter searchCategoryAdapter;
    SearchResultAdapter searchResultAdapter;
    @Bind(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;
    @Bind(R.id.imageView22)
    ImageView imageView22;
    @Bind(R.id.textView70)
    TextView textView70;
    @Bind(R.id.imageView23)
    ImageView imageView23;
    @Bind(R.id.textView71)
    TextView textView71;
    @Bind(R.id.locationlinear)
    LinearLayout locationlinear;
    @Bind(R.id.text_signup_or)
    TextView textSignupOr;
    @Bind(R.id.locationrecycler)
    RelativeLayout locationrecycler;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.searchcategoryrecycler)
    RecyclerView searchcategoryrecycler;
    @Bind(R.id.searchresultrecycler)
    RecyclerView searchresultrecycler;


    public SearchFragment() {
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerview.setLayoutManager(gridLayoutManager);


        searchAdapter = new SearchAdapter(getActivity(), new OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                recyclerview.setVisibility(View.GONE);
                locationlinear.setVisibility(View.GONE);
                locationrecycler.setVisibility(View.GONE);
                searchcategoryrecycler.setVisibility(View.VISIBLE);


            }
        });
        recyclerview.setAdapter(searchAdapter);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(), 1);
        searchcategoryrecycler.setLayoutManager(gridLayoutManager1);
        searchCategoryAdapter = new SearchCategoryAdapter(getActivity(), new OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                recyclerview.setVisibility(View.GONE);
                locationlinear.setVisibility(View.GONE);
                locationrecycler.setVisibility(View.GONE);
                searchcategoryrecycler.setVisibility(View.GONE);
                searchcategoryrecycler.setVisibility(View.GONE);
                searchresultrecycler.setVisibility(View.VISIBLE);
                ((BaseActivity)getActivity()).getTextViewToolBarTitle().setText("Search Result");
                ((BaseActivity)getActivity()).getImgSettings().setVisibility(View.VISIBLE);
                ((BaseActivity)getActivity()).getImgMap().setVisibility(View.GONE);

            }
        });
        searchcategoryrecycler.setAdapter(searchCategoryAdapter);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getActivity(),1);
        searchresultrecycler.setLayoutManager(gridLayoutManager2);

        searchResultAdapter = new SearchResultAdapter(getActivity(),new OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                if (getActivity() != null)
                    searchresultrecycler.setVisibility(View.VISIBLE);
            }
        });
        searchresultrecycler.setAdapter(searchResultAdapter);
        return view;
    }


    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText("Search");
        ((BaseActivity) getActivity()).getTabLayout().setVisibility(View.VISIBLE);
        ((BaseActivity)getActivity()).getImgMap().setVisibility(View.VISIBLE);
        ((BaseActivity)getActivity()).getImgMap().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).openMapPage();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
