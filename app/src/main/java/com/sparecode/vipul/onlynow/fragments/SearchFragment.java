package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.adapters.ExpandableListAdapter;
import com.sparecode.vipul.onlynow.adapters.SearchAdapter;
import com.sparecode.vipul.onlynow.adapters.SearchResultAdapter;
import com.sparecode.vipul.onlynow.view.OnClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchFragment extends BaseFragment {

    SearchAdapter searchAdapter;
    SearchResultAdapter searchResultAdapter;
    @Bind(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;
    @Bind(R.id.imageView22)
    ImageView imageView22;
    @Bind(R.id.currenttext)
    TextView currenttext;
    @Bind(R.id.currentlocation)
    LinearLayout currentlocation;
    @Bind(R.id.imageView23)
    ImageView imageView23;
    @Bind(R.id.ecommercetext)
    TextView ecommercetext;
    @Bind(R.id.ecommerce)
    LinearLayout ecommerce;
    @Bind(R.id.locationlinear)
    LinearLayout locationlinear;
    @Bind(R.id.text_signup_or)
    TextView textSignupOr;
    @Bind(R.id.locationrecycler)
    RelativeLayout locationrecycler;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;

    @Bind(R.id.searchresultrecycler)
    RecyclerView searchresultrecycler;
    @Bind(R.id.searchcategoryrecycler)
    ExpandableListView searchcategoryrecycler;
    ExpandableListAdapter listAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public SearchFragment() {
        // Required empty public constructor
    }

    @OnClick(R.id.currentlocation)
    void onLocationLayout() {
        if (!currentlocation.isSelected()) {
            currentlocation.setSelected(true);
            ecommerce.setSelected(false);
        }

    }

    @OnClick(R.id.ecommerce)
    void onEcommerceLayout() {
        if (!ecommerce.isSelected()) {
            ecommerce.setSelected(true);
            currentlocation.setSelected(false);
        }
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
                ((BaseActivity) getActivity()).getImgToolBarBack().setVisibility(View.VISIBLE);
                ((BaseActivity) getActivity()).getImgToolBarBack().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((BaseActivity) getActivity()).openSearchPage();
                    }
                });


            }
        });

        recyclerview.setAdapter(searchAdapter);
       // GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getActivity(), 1);
        //searchcategoryrecycler.setLayoutManager(gridLayoutManager1);


//        searchCategoryAdapter = new SearchCategoryAdapter(getActivity(), mobileOSes, new OnClickListener() {
//            @Override
//            public void onItemClicked(int position) {
//                recyclerview.setVisibility(View.GONE);
//                locationlinear.setVisibility(View.GONE);
//                locationrecycler.setVisibility(View.GONE);
//                searchcategoryrecycler.setVisibility(View.GONE);
//                searchcategoryrecycler.setVisibility(View.GONE);
//                searchresultrecycler.setVisibility(View.VISIBLE);
//                ((BaseActivity) getActivity()).getImgMap().setVisibility(View.GONE);
//                ((BaseActivity) getActivity()).setOptionMenuVisibility(true);
//                ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.searchresult));
//            }
//        });
//        searchCategoryAdapter = new SearchCategoryAdapter(getActivity(), new OnClickListener() {
//            @Override
//            public void onItemClicked(int position) {
//                recyclerview.setVisibility(View.GONE);
//                locationlinear.setVisibility(View.GONE);
//                locationrecycler.setVisibility(View.GONE);
//                searchcategoryrecycler.setVisibility(View.GONE);
//                searchcategoryrecycler.setVisibility(View.GONE);
//                searchresultrecycler.setVisibility(View.VISIBLE);
//                ((BaseActivity)getActivity()).getImgMap().setVisibility(View.GONE);
//                ((BaseActivity)getActivity()).setOptionMenuVisibility(true);
//                ((BaseActivity)getActivity()).getTextViewToolBarTitle().setText(getString(R.string.searchresult));
//            }
//        });
        prepareListData();
        listAdapter = new ExpandableListAdapter(getActivity(),listDataHeader,listDataChild);
        searchcategoryrecycler.setAdapter(listAdapter);

        searchcategoryrecycler.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        searchcategoryrecycler.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getActivity(),
//                        listDataHeader.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        searchcategoryrecycler.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getActivity(),
//                        listDataHeader.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();


            }
        });

        // Listview on child click listener
        searchcategoryrecycler.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
//                Toast.makeText(
//                        getActivity(),
//                        listDataHeader.get(groupPosition)
//                                + " : "
//                                + listDataChild.get(
//                                listDataHeader.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT)
//                        .show();
                recyclerview.setVisibility(View.GONE);
                locationlinear.setVisibility(View.GONE);
                locationrecycler.setVisibility(View.GONE);
                searchcategoryrecycler.setVisibility(View.GONE);
                searchcategoryrecycler.setVisibility(View.GONE);
                searchresultrecycler.setVisibility(View.VISIBLE);
                ((BaseActivity)getActivity()).getImgMap().setVisibility(View.GONE);
                ((BaseActivity)getActivity()).setOptionMenuVisibility(true);
                ((BaseActivity)getActivity()).getTextViewToolBarTitle().setText(getString(R.string.searchresult));
                return false;
            }
        });


        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getActivity(), 1);
        searchresultrecycler.setLayoutManager(gridLayoutManager2);

        searchResultAdapter = new SearchResultAdapter(getActivity(), new OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                if (getActivity() != null)
                    searchresultrecycler.setVisibility(View.VISIBLE);
            }
        });
        searchresultrecycler.setAdapter(searchResultAdapter);
        return view;
    }


    @OnClick(R.id.currentlocation)
    void onClickOpenMap() {
        ((BaseActivity) getActivity()).openMapPage();
    }


    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.search));
        ((BaseActivity) getActivity()).getTabLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextNext().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgSettings().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgShare().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgMap().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgToolBarCancel().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgSearchMap().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).setOptionMenuVisibility(false);
        ((BaseActivity) getActivity()).getImgToolBarBack().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgMap().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) getActivity()).openMapPage();
            }
        });
    }

    /*@OnClick(R.id.textView70)
    void onTextClick(View v) {
        if (textView70.isSelected()) {
            textView70.setTextColor(getResources().getColor(R.color.white));
            v.setSelected(false);
        } else
        {
            textView70.setTextColor(getResources().getColor(R.color.black));
            v.setSelected(true);
        }
    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Category");
        listDataHeader.add("Popular Queries");
        //listDataHeader.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("Gaurment");
        top250.add("Beauty");
        top250.add("Fashion");
        top250.add("Masssage");
        top250.add("Travel");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("Beer");
        nowShowing.add("Jeans");
        nowShowing.add("Curry");
        nowShowing.add("WinterCoat");
        nowShowing.add("lunch");


//		List<String> comingSoon = new ArrayList<String>();
//		comingSoon.add("2 Guns");
//		comingSoon.add("The Smurfs 2");
//		comingSoon.add("The Spectacular Now");
//		comingSoon.add("The Canyons");
//		comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        //listDataChild.put(listDataHeader.get(2), comingSoon);
    }
}
