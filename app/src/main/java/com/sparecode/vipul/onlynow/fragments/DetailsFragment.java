package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsFragment extends BaseFragment {

    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.pager)
    ViewPager pager;
    @Bind(R.id.container)
    LinearLayout container;

    private SectionPagerAdapter sectionPagerAdapter;
    String tabTitles[] = {"Coupon", "Shop", "Review"};

    public DetailsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);

        sectionPagerAdapter = new SectionPagerAdapter(getChildFragmentManager());
        pager.setAdapter(sectionPagerAdapter);
        tabs.setupWithViewPager(pager);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity)getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity)getActivity()).getTextViewToolBarTitle().setText("Details");
        ((BaseActivity)getActivity()).getTabLayout().setVisibility(View.VISIBLE);
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
            switch (position)
            {
                case 0:
                    return CouponFragment.newInstance("0");
                case 1:
                    return ShopFragment.newInstance("1");
                case 2:
                    return ReviewFragment.newInstance("2");
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            return 3;
        }

//        @Override
//        public CharSequence getPageTitle(int position) {
//
//            for (int i = 0; i <= tabTitles.length; i++) {
//                return tabTitles[position];
//
//            }
//            return null;
//        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position)
            {
                case 0:
                    return "Coupon";
                case 1:
                    return "Shop";
                case 2:
                    return "review";
                default:
                    return null;
            }
        }
    }
}
