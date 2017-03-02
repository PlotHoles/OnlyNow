package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.interfaces.SignupNextListner;
import com.sparecode.vipul.onlynow.view.StepIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignupFragment extends BaseFragment implements SignupNextListner {

    @Bind(R.id.step_indicator)
    StepIndicator stepIndicator;
    @Bind(R.id.pager)
    ViewPager pager;
    private SectionPagerAdapter sectionPagerAdapter;

    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity) getActivity()).setSignupNextListner(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        ButterKnife.bind(this, view);

        sectionPagerAdapter = new SectionPagerAdapter(getChildFragmentManager());
        pager.setAdapter(sectionPagerAdapter);
        stepIndicator.setupWithViewPager(pager);
        pager.setOffscreenPageLimit(3);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                System.out.println("------>pos" + position);
                if (position == 0) {
                    ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.signup));
                    ((BaseActivity) getActivity()).getAppbarLayout().findViewById(R.id.text_next).setVisibility(View.GONE);


                } else if (position == 1) {
                    ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.active_area));
                    ((BaseActivity) getActivity()).getTextNext().setVisibility(View.VISIBLE);
                    ((BaseActivity) getActivity()).getAppbarLayout().findViewById(R.id.imgSettings).setVisibility(View.GONE);
                } else if (position == 2) {
                    ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.favourite_category));
                    ((BaseActivity) getActivity()).getTextNext().setVisibility(View.VISIBLE);
                    ((BaseActivity) getActivity()).getAppbarLayout().findViewById(R.id.imgToolBarBack).setVisibility(View.VISIBLE);
                    ((BaseActivity) getActivity()).getAppbarLayout().findViewById(R.id.imgSettings).setVisibility(View.GONE);
                    ((BaseActivity) getActivity()).getTabLayout().setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {
                System.out.println("---->select" + position);
                BaseActivity.currentSignupPageNumber = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        return view;
    }

    public void performNext() {
        pager.setCurrentItem(getItem(+1));
    }

    public int getItem(int i) {
        return pager.getCurrentItem() + i;
    }

    public void performPrevious() {
        pager.setCurrentItem(getItems(-1));
    }

    public int getItems(int i) {
        return pager.getCurrentItem() - i;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgToolBarCancel().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgShare().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getImgMap().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getImgToolBarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).openSignupfacebookPage();
            }
        });
        // ((BaseActivity)getActivity()).getTextViewToolBarTitle().setText("Sign Up");
    }

    @Override
    public void onSignupNextClick(boolean flag) {
        if(!flag){
            ((BaseActivity) getActivity()).onSignupNextClickPerform();
        }else{
            //performNext();
            performNext();
        }

    }


    public class SectionPagerAdapter extends FragmentPagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return Signupstep1Fragment.newInstance("Sign Up");
                case 1:
                    return Signupstep2Fragment.newInstance("Select Active Area");
                case 2:
                    return Signupstep3Fragment.newInstance("Select Favourite Category");
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
            switch (position) {
                case 0:
                    return "Sign Up";
                case 1:
                    return "Select Active Area";
                case 2:
                    return "Select Favourite Category";
                default:
                    return null;
            }
        }
    }
}
