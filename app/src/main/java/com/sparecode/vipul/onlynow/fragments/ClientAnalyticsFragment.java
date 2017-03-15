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

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.util.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ClientAnalyticsFragment extends BaseFragment {


    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.pager)
    ViewPager pager;

    private SectionPagerAdapter sectionPagerAdapter;

    public ClientAnalyticsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_client_analytics, container, false);
        ButterKnife.bind(this, view);

        sectionPagerAdapter = new SectionPagerAdapter(getChildFragmentManager());
        pager.setAdapter(sectionPagerAdapter);
        tabs.setupWithViewPager(pager);
        Utils.applyFontedTab(getActivity(),pager,tabs);

        return view;
    }


    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTabLayoutclient().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.analytics));
        ((BaseActivity)getActivity()).getImgAdd().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getImgToolBarCancel().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getImgEdit().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getFab().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getTextNext().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getImgToolBarBack().setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    public class SectionPagerAdapter extends FragmentPagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    return ClentCouponAnalyticsFragment.newInstance("0");
                case 1:
                    return ClientAnalyticsClientkFragment.newInstance("1");
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            return 2;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            switch (position)
            {
                case 0:
                    return "Coupon";
                case 1:
                    return "Client";
                default:
                    return null;
            }
        }
    }
}
