package com.sparecode.vipul.onlynow.fragments;

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
import com.sparecode.vipul.onlynow.model.MyListFavoriteWrapper;
import com.sparecode.vipul.onlynow.model.MyListReviewedWrapper;
import com.sparecode.vipul.onlynow.model.MyListSavedWrapper;
import com.sparecode.vipul.onlynow.model.SignupWrapper;
import com.sparecode.vipul.onlynow.util.Prefs;
import com.sparecode.vipul.onlynow.util.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MylistFragment extends BaseFragment implements MyListBackend.MyListDataProvider {

    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.pager)
    ViewPager pager;
    @Bind(R.id.container)
    LinearLayout container;


    private SectionPagerAdapter sectionPagerAdapter;
    String tabTitles[] = {"Saved", "Favourite", "Reviewed", "Viewed"};

    public MylistFragment() {
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
        View view = inflater.inflate(R.layout.fragment_mylist, container, false);
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
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.list));
        ((BaseActivity) getActivity()).getTabLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgSearchMap().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).setOptionMenuVisibility(false);
        ((BaseActivity) getActivity()).getImgMap().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgToolBarBack().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getImgToolBarCancel().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getImgShare().setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onSavedSuccess(MyListSavedWrapper myListSavedWrapper) {


    }

    @Override
    public void onFavOrRecentViewSuccess(MyListFavoriteWrapper myListFavoriteWrapper) {

    }

    @Override
    public void onReviewedSuccess(MyListReviewedWrapper myListReviewedWrapper) {

    }

    @Override
    public void onFailure(String msg) {

    }

    public class SectionPagerAdapter extends FragmentPagerAdapter {
        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);

        }


        /*@Override
        public Fragment getItem(int position) {
            Log.e("POS OF I","::"+position);
            return new FashionFragment(signupWrapper.getData().getId(),myListSavedWrapper.getData().get(position).getId());
        }*/

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    return CouponSavedFragment.newInstance("0");
                case 1:
                    return CouponFavoriteFragment.newInstance("1");
                case 2:
                    return CouponReviewedFragment.newInstance("2");
                case 3:
                    return CouponViewedFragment.newInstance("3");
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            return 4;
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
