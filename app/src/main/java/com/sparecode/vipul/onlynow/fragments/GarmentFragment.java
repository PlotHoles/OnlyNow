package com.sparecode.vipul.onlynow.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.adapters.CardAdapter;
import com.sparecode.vipul.onlynow.model.CouponWrapper;
import com.sparecode.vipul.onlynow.model.FavCategoryWrapper;
import com.sparecode.vipul.onlynow.model.SignupWrapper;
import com.sparecode.vipul.onlynow.util.Prefs;
import com.sparecode.vipul.onlynow.view.OnClickListener;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("ValidFragment")
public class GarmentFragment extends BaseFragment implements HomeFragmentBackend.HomePageDataProvider {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    CardAdapter cardAdapter;
    String catId = "";
    String userId = "";
    String shopId = "";
    HomeFragmentBackend homeFragmentBackend;
    String couponId;
    @Bind(R.id.nodata)
    LatoTextView nodata;
    SignupWrapper signupWrapper;
    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;

    public GarmentFragment(CardAdapter cardAdapter, String catId, String userId, String shopId) {
        this.cardAdapter = cardAdapter;
        this.catId = catId;
        this.userId = userId;
        this.shopId = shopId;
    }

    public GarmentFragment(String userId, String catId, String couponId) {
        this.catId = catId;
        this.userId = userId;
        this.couponId = couponId;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_garment, container, false);
        ButterKnife.bind(this, view);
        getUser();
        Log.e("PRINT USER ID + CAT ID", userId + ":" + catId);

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homeFragmentBackend = new HomeFragmentBackend(userId, catId, getActivity(), GarmentFragment.this);
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerview.setLayoutManager(gridLayoutManager);
        homeFragmentBackend = new HomeFragmentBackend(userId, catId, getActivity(), this);


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popular:
                homeFragmentBackend = new HomeFragmentBackend(userId, catId, "1", getActivity(), this);
                //Toast.makeText(getActivity(), "Popular Selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.newmenu:
                homeFragmentBackend = new HomeFragmentBackend(userId, catId, "2", getActivity(), this);
                //Toast.makeText(getActivity(), "New is Selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.finishsoon:
                homeFragmentBackend = new HomeFragmentBackend(userId, catId, "3", getActivity(), this);
                //Toast.makeText(getActivity(), "Finish Soon is Selected", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getUser() {
        try {
            signupWrapper = new Gson().fromJson(Prefs.getString("user", ""), SignupWrapper.class);

        } catch (JsonSyntaxException e) {
            ((BaseActivity) getActivity()).openSplashPage();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.recyclerview)
    public void onClick() {
        Toast.makeText(getActivity(), "hi", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setToolbarForFragment() {

    }

    @Override
    public void onFavCatSuccess(FavCategoryWrapper favCategoryWrapper) {
    }

    @Override
    public void onCouponSuccess(final CouponWrapper couponWrapper) {
        nodata.setVisibility(View.GONE);
        recyclerview.setVisibility(View.VISIBLE);
        swiperefresh.setRefreshing(false);
        cardAdapter = new CardAdapter(getActivity(), couponWrapper, new OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                if (getActivity() != null)

                    //couponWrapper.getData().get(position).getId();
                    ((BaseActivity) getActivity()).openDetailPage(couponWrapper.getData().get(position).getId(), couponWrapper.getData().get(position).getShopId(), signupWrapper.getData().getId(), couponWrapper.getData().get(position).getId());
            }
        });
        recyclerview.setAdapter(cardAdapter);
        Log.e(":::COUPONS::", "" + couponWrapper.getData().get(0).getName());
    }

    @Override
    public void onFailure(String msg) {
        swiperefresh.setRefreshing(false);
        nodata.setVisibility(View.VISIBLE);
        recyclerview.setVisibility(View.GONE);
    }
}
