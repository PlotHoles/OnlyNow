package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.model.CouponWrapper;
import com.sparecode.vipul.onlynow.model.FavCategoryWrapper;
import com.sparecode.vipul.onlynow.model.SignupWrapper;
import com.sparecode.vipul.onlynow.util.Prefs;
import com.sparecode.vipul.onlynow.util.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment implements HomeFragmentBackend.HomePageDataProvider {

    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.pager)
    ViewPager pager;
    HomeFragmentBackend homeFragmentBackend;
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
        return view;
    }

    SignupWrapper signupWrapper;

    private void getUser() {
        signupWrapper = new Gson().fromJson(Prefs.getString("user", ""), SignupWrapper.class);
        //Log.e("::", "SIGNUP WRAPPER::" + signupWrapper.getData());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getUser();
        if (signupWrapper != null)
        {
            homeFragmentBackend = new HomeFragmentBackend(signupWrapper.getData().getId(), getActivity(), this);
        }

    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.onlynow));
        ((BaseActivity) getActivity()).getImgToolBarBack().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getTextNext().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getTabLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgSettings().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgMap().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgShare().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgToolBarCancel().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).setOptionMenuVisibility(true);
        ((BaseActivity) getActivity()).getImgSearchMap().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getTabLayoutclient().setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onFavCatSuccess(FavCategoryWrapper favCategoryWrapper) {
        if (getActivity() != null) {
        if (pager!=null){
            tabTitles = new String[favCategoryWrapper.getData().size()];
            for (int i = 0; i < favCategoryWrapper.getData().size(); i++) {
                tabTitles[i] = favCategoryWrapper.getData().get(i).getName();
            }
            sectionPagerAdapter = new SectionPagerAdapter(getChildFragmentManager(), favCategoryWrapper);
            pager.setAdapter(sectionPagerAdapter);
            tabs.setupWithViewPager(pager);
            Utils.applyFontedTab(getActivity(), pager, tabs);
        }}
    }
    @Override
    public void onCouponSuccess(CouponWrapper couponWrapper) {

    }

    @Override
    public void onFailure(String msg) {

    }

    public class SectionPagerAdapter extends FragmentPagerAdapter {
        FavCategoryWrapper favCategoryWrapper;

        public SectionPagerAdapter(FragmentManager fm, FavCategoryWrapper favCategoryWrapper) {
            super(fm);
            this.favCategoryWrapper = favCategoryWrapper;
        }

        @Override
        public Fragment getItem(int position) {
            return new GarmentFragment(signupWrapper.getData().getId(), favCategoryWrapper.getData().get(position).getCatId(), favCategoryWrapper.getData().get(position).getId());

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
            return favCategoryWrapper.getData().size();
        }

        @Override
        public CharSequence getPageTitle(int position) {


            return tabTitles[position];


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
