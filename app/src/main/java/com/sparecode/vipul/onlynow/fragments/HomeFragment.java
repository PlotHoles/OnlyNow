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

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment {

    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.pager)
    ViewPager pager;

    private SectionPagerAdapter sectionPagerAdapter;
    String tabTitles[] = {"Garment", "Fashion", "Beauty", "Learning"};


    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        sectionPagerAdapter = new SectionPagerAdapter(getChildFragmentManager());
        pager.setAdapter(sectionPagerAdapter);
        tabs.setupWithViewPager(pager);

        return view;
    }



    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText("OnlyNow");
        ((BaseActivity) getActivity()).getImgToolBarBack().setVisibility(View.INVISIBLE);
        ((BaseActivity)getActivity()).getTabLayout().setVisibility(View.VISIBLE);
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

            for (int i = 0; i <= position; i++) {
                return GarmentFragment.newInstance(String.valueOf(position));
            }
            return null;
        }

//        @Override
//        public Fragment getItem(int position) {
//            switch (position)
//            {
//                case 0:
//                    return GarmentFragment.newInstance("0");
//                case 1:
//                    return BeautyFragment.newInstance("1");
//                case 2:
//                    return FashionFragment.newInstance("2");
//                case 3:
//                    return LearningFragment.newInstance("3");
//                default:
//                    return null;
//            }
//
//        }

        @Override
        public int getCount() {
            return 4;
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
//            switch (position)
//            {
//                case 0:
//                    return "Garment";
//                case 1:
//                    return "Beauty";
//                case 2:
//                    return "Fashion";
//                case 3:
//                    return "Learning";
//                default:
//                    return null;
//            }
//        }
    }
}
