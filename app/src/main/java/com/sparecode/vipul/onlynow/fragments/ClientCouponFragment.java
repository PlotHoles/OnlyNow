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

public class ClientCouponFragment extends BaseFragment {

    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.pager)
    ViewPager pager;

    private SectionPagerAdapter sectionPagerAdapter;

    public ClientCouponFragment() {
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
        View view = inflater.inflate(R.layout.fragment_client_coupon, container, false);
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
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.coupon));
        ((BaseActivity) getActivity()).getImgToolBarBack().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getTabLayout().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getTabLayoutclient().setVisibility(View.VISIBLE);
        ((BaseActivity)getActivity()).getImgAdd().setVisibility(View.VISIBLE);
        ((BaseActivity)getActivity()).getImgToolBarCancel().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getImgEdit().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getFab().setVisibility(View.VISIBLE);
        ((BaseActivity)getActivity()).getImgAdd().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).openClientCouponDetailsPage();
            }
        });
        ((BaseActivity)getActivity()).getFab().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).openClientCouponDetailsPage();
            }
        });
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
                    return ClientLiveFragment.newInstance("0");
                case 1:
                    return ClientDraftFragment.newInstance("1");
                case 2:
                    return ClientPastFragment.newInstance("2");
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
            switch (position)
            {
                case 0:
                    return "Live";
                case 1:
                    return "Draft";
                case 2:
                    return "Past";
                default:
                    return null;
            }
        }
    }
}
