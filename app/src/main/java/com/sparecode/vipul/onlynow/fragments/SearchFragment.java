package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.adapters.ExpandableListAdapter;
import com.sparecode.vipul.onlynow.adapters.SearchAdapter;
import com.sparecode.vipul.onlynow.adapters.SearchResultAdapter;
import com.sparecode.vipul.onlynow.model.SearchCategoryWrapper;
import com.sparecode.vipul.onlynow.model.SearchLocationData;
import com.sparecode.vipul.onlynow.model.SearchLocationWrapper;
import com.sparecode.vipul.onlynow.model.SearchPopularQueriesWrapper;
import com.sparecode.vipul.onlynow.model.SearchResultWrapper;
import com.sparecode.vipul.onlynow.model.TagManager;
import com.sparecode.vipul.onlynow.view.OnClickListener;
import com.sparecode.vipul.onlynow.view.TagsEditText;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.ArrayUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.facebook.FacebookSdk.getApplicationContext;

public class SearchFragment extends BaseFragment implements SearchBackend.SearchDataProvider, SearchCategoryBackend.SearchCategoryDataProvider, SearchPopularQueriesbackend.SearchPopularQueriesDataProvider, SearchResultBackend.SearchResultDataProvider {

    SearchAdapter searchAdapter;
    SearchResultAdapter searchResultAdapter;
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
    ExpandableListAdapter listAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    @Bind(R.id.autoCompleteTextView)
    TagsEditText autoCompleteTextView;
    List<SearchLocationData> data;
    GridLayoutManager gridLayoutManager;
    @Bind(R.id.category_linear)
    LinearLayout categoryLinear;
    @Bind(R.id.popular_linear)
    LinearLayout popularLinear;
    @Bind(R.id.searchcategoryrecycler)
    LinearLayout searchcategoryrecycler;
    String[] tags = new String[]{};
    String latitude, longitude;
    String keyword = "";

    JSONObject jsonObject;

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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerview.setLayoutManager(gridLayoutManager);

        SearchBackend searchBackend = new SearchBackend(getActivity(), this);

        SearchCategoryBackend searchCategoryBackend = new SearchCategoryBackend(getActivity(), "1", this);

        SearchPopularQueriesbackend searchPopularQueriesbackend = new SearchPopularQueriesbackend(getActivity(), this);
        gridLayoutManager = new GridLayoutManager(getActivity(), 1);

        Log.e("log", autoCompleteTextView.getText().toString());
        jsonObject = new JSONObject();
        autoCompleteTextView.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    //Toast.makeText(getActivity(),tags.length+"",Toast.LENGTH_SHORT).show();
                    String array = Arrays.toString(tags);
                    Log.e("tags", autoCompleteTextView.getText().toString());
                    keyword = autoCompleteTextView.getText().toString();

                    try {
                        jsonObject.accumulate(keyword, "keyword");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    TagManager.setKeyKeyWord(keyword);
                    SearchResultBackend searchResultBackend = new SearchResultBackend(getApplicationContext(), keyword, SearchFragment.this);
                }
                return false;
            }
        });
        autoCompleteTextView.setTagsListener(new TagsEditText.TagsEditListener() {
            @Override
            public void onTagsChanged(Collection<String> tags, Collection<String> mOldTags) {

                Log.e("TAG CHANGED::", ":::" + tags);
                Log.e("OLD TAGS::", ":::" + mOldTags);
                Log.e("JSON OBJECT::", "::" + jsonObject);
            }

            @Override
            public void onEditingFinished() {
                Log.e("TAG EDITING::", ":::");
            }
        });

        // autoCompleteTextView.setOnTouchListener(new );
        /*searchAdapter = new SearchAdapter(getActivity(), new OnClickListener() {
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
        });*/

        //recyclerview.setAdapter(searchAdapter);
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
       /* prepareListData();
        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
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
        });*/

        // Listview on child click listener
        /*searchcategoryrecycler.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

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
                ((BaseActivity) getActivity()).getImgMap().setVisibility(View.GONE);
                ((BaseActivity) getActivity()).setOptionMenuVisibility(true);
                ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.searchresult));
                return false;
            }
        });*/


        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getActivity(), 1);
        searchresultrecycler.setLayoutManager(gridLayoutManager2);


        /*searchResultAdapter = new SearchResultAdapter(getActivity(), new OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                if (getActivity() != null)
                    searchresultrecycler.setVisibility(View.VISIBLE);
            }
        });
        searchresultrecycler.setAdapter(searchResultAdapter);*/
        return view;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popular:
                Toast.makeText(getActivity(), "Popular Selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.newmenu:
                Toast.makeText(getActivity(), "New is Selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.finishsoon:
                Toast.makeText(getActivity(), "Finish Soon is Selected", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }



    @Override
    public void Successfull(final SearchLocationWrapper searchLocationWrapper) {
        if (getActivity() != null) {
            if (recyclerview != null) {
                searchAdapter = new SearchAdapter(getActivity(), searchLocationWrapper.getData(), new OnClickListener() {
                    @Override
                    public void onItemClicked(int position) {
                        //Toast.makeText(getActivity(), searchLocationWrapper.getData().get(position).getLat(), Toast.LENGTH_SHORT).show();
                        recyclerview.setVisibility(View.GONE);
                        locationlinear.setVisibility(View.GONE);
                        locationrecycler.setVisibility(View.GONE);

                        tags = new String[]{keyword, searchLocationWrapper.getData().get(position).getArea()};

                        latitude = searchLocationWrapper.getData().get(position).getLat();
                        longitude = searchLocationWrapper.getData().get(position).getLong();

                        TagManager.setKeyLat(latitude);
                        TagManager.setKeyLong(longitude);


                        try {
                            jsonObject.accumulate(latitude, "lat");
                            jsonObject.accumulate(longitude, "long");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        autoCompleteTextView.setTags(tags);
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
            }
        }
    }

    @Override
    public void Failure(String msg) {

    }

    @Override
    public void onsuccessfull(final SearchCategoryWrapper searchCategoryWrapper) {
        //Toast.makeText(getActivity(), searchCategoryWrapper.getData().size() + "", Toast.LENGTH_SHORT).show();
        if (getActivity()!=null){
        if (categoryLinear!=null){
        for (int i = 0; i < searchCategoryWrapper.getData().size(); i++) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = layoutInflater.inflate(R.layout.design_category_recycler, null);

            final TextView textcategory = (TextView) v.findViewById(R.id.text_Category);
            ImageView imagecateory = (ImageView) v.findViewById(R.id.image_Category);

            categoryLinear.addView(v, i, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            textcategory.setText(searchCategoryWrapper.getData().get(i).getName());

            textcategory.setTag(i);
            textcategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerview.setVisibility(View.GONE);
                    locationlinear.setVisibility(View.GONE);
                    locationrecycler.setVisibility(View.GONE);
                    searchcategoryrecycler.setVisibility(View.GONE);
                    searchcategoryrecycler.setVisibility(View.GONE);
                    searchresultrecycler.setVisibility(View.VISIBLE);
                    ((BaseActivity) getActivity()).getImgMap().setVisibility(View.GONE);
                    ((BaseActivity) getActivity()).setOptionMenuVisibility(true);
                    ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.searchresult));
                    searchCategoryWrapper.getData().get((Integer) textcategory.getTag()).getName();
                    //autoCompleteTextView.getText();
                    String[] tmp = tags;
                    tags = ArrayUtils.addAll(tmp, new String[]{searchCategoryWrapper.getData().get((Integer) textcategory.getTag()).getName()});
                    //tags[tags.length] = searchCategoryWrapper.getData().get((Integer) textcategory.getTag()).getName();
                    String cat_id = searchCategoryWrapper.getData().get((Integer) textcategory.getTag()).getId();
                    SearchResultBackend searchResultBackend = new SearchResultBackend(getApplicationContext(), latitude, longitude, cat_id, SearchFragment.this);
                    try {
                        jsonObject.accumulate(cat_id, "cat_id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    TagManager.setKeyCategory(cat_id);
                    autoCompleteTextView.setTags(tags);

                    //Toast.makeText(getActivity(),textcategory.getTag()+"",Toast.LENGTH_SHORT).show();
                }
            });
            if (searchCategoryWrapper.getData().get(i).getPinkIconURL() != null &&
                    (!searchCategoryWrapper.getData().get(i).getPinkIconURL().equals(""))) {

                Picasso.with(getActivity())
                        .load(searchCategoryWrapper.getData().get(i).getPinkIconURL())
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.placeholder)
                        .resize(20, 20)
                        .into(imagecateory);
            } else {
                Picasso.with(getActivity()).load(R.drawable.placeholder).fit().into(imagecateory);
            }
        }}}
    }

    @Override
    public void onSuccessfull(final SearchPopularQueriesWrapper searchPopularQueriesWrapper) {
        if (getActivity() != null) {
            if (popularLinear != null){
            for (int i = 0; i < searchPopularQueriesWrapper.getData().size(); i++) {
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = layoutInflater.inflate(R.layout.design_popular_queries, null);

                popularLinear.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                final TextView textqueries = (TextView) v.findViewById(R.id.text_popularqueries);
                textqueries.setText(searchPopularQueriesWrapper.getData().get(i).getKeyword());

                textqueries.setTag(i);
                textqueries.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerview.setVisibility(View.GONE);
                        locationlinear.setVisibility(View.GONE);
                        locationrecycler.setVisibility(View.GONE);
                        searchcategoryrecycler.setVisibility(View.GONE);
                        searchcategoryrecycler.setVisibility(View.GONE);
                        searchresultrecycler.setVisibility(View.VISIBLE);
                        ((BaseActivity) getActivity()).getImgMap().setVisibility(View.GONE);
                        ((BaseActivity) getActivity()).setOptionMenuVisibility(true);
                        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.searchresult));
                        String[] tmps = tags;
                        tags = ArrayUtils.addAll(tmps, new String[]{searchPopularQueriesWrapper.getData().get((Integer) textqueries.getTag()).getKeyword()});
                        String keyword = searchPopularQueriesWrapper.getData().get((Integer) textqueries.getTag()).getKeyword();
                        TagManager.setKeyPopularQuery(keyword);
                        try {
                            jsonObject.accumulate(keyword, "keyword1");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        SearchResultBackend searchResultBackend = new SearchResultBackend(getApplicationContext(), latitude, longitude, keyword, SearchFragment.this);
                        autoCompleteTextView.setTags(tags);
                    }
                });


            }
            }
        }
    }

    @Override
    public void onSuccessfull(SearchResultWrapper searchResultWrapper) {
        if (getActivity() != null) {
            recyclerview.setVisibility(View.GONE);
            locationlinear.setVisibility(View.GONE);
            locationrecycler.setVisibility(View.GONE);
            searchcategoryrecycler.setVisibility(View.GONE);
            searchcategoryrecycler.setVisibility(View.GONE);
            searchresultrecycler.setVisibility(View.VISIBLE);
            ((BaseActivity) getActivity()).getImgMap().setVisibility(View.GONE);
            ((BaseActivity) getActivity()).setOptionMenuVisibility(true);
            ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.searchresult));
            searchResultAdapter = new SearchResultAdapter(getActivity(), searchResultWrapper.getData(), new OnClickListener() {
                @Override
                public void onItemClicked(int position) {

                }
            });
            searchresultrecycler.setAdapter(searchResultAdapter);
        }
    }

    @Override
    public void onFailure(String msg) {

    }

}
