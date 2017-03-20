package com.sparecode.vipul.onlynow.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.model.CouponDetailWrapper;
import com.sparecode.vipul.onlynow.model.SignupWrapper;
import com.sparecode.vipul.onlynow.util.Prefs;
import com.sparecode.vipul.onlynow.util.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;


public class DetailsFragment extends BaseFragment implements DetailFragemenBackend.CouponDetailProvider{

    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.pager)
    ViewPager pager;
    @Bind(R.id.container)
    LinearLayout container;
    private String couponId = "";
    private String shopId=" ";
    private String userId= " ";
    private String Id= " ";

    private SectionPagerAdapter sectionPagerAdapter;
    String tabTitles[] = {"Coupon", "Shop", "Review"};

    public DetailsFragment(String couponId,String shopId,String userId,String Id) {
        this.couponId = couponId;
        this.shopId = shopId;
        this.userId = userId;
        this.Id=Id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);

        sectionPagerAdapter = new SectionPagerAdapter(getChildFragmentManager());
        pager.setAdapter(sectionPagerAdapter);
        tabs.setupWithViewPager(pager);
        Utils.applyFontedTab(getActivity(), pager, tabs);
        return view;
    }
    SignupWrapper signupWrapper;

    private void getUser() {
        signupWrapper = new Gson().fromJson(Prefs.getString("user", ""), SignupWrapper.class);
        Log.e("::", "SIGNUP WRAPPER::" + signupWrapper.getData());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getUser();

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText("Details");
        ((BaseActivity) getActivity()).getTabLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgShare().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgSettings().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgToolBarCancel().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).setOptionMenuVisibility(false);
        ((BaseActivity) getActivity()).getImgShare().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }

    @Override
    public void onCouponSuccess(CouponDetailWrapper couponDetailWrapper) {

    }

    @Override
    public void onFailure(String msg) {

    }

    public class SectionPagerAdapter extends FragmentPagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

//        @Override
//        public Fragment getItem(int position) {
//
//            for (int i = 0; i <= position; i++) {
//                return FashionFragment.newInstance(String.valueOf(position));
//            }
//            return null;
//        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new CouponFragment(couponId);
                case 1:
                    return new ShopFragment(shopId,userId,Id);
                case 2:
                    return new ReviewFragment(shopId);
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            for (int i = 0; i <= tabTitles.length; i++) {
                return tabTitles[position];

            }
            return null;
        }

//        @Override
//        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "Coupon";
//                case 1:
//                    return "Shop";
//                case 2:
//                    return "review";
//                default:
//                    return null;
//            }
        }
    }

