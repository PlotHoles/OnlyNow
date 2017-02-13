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
import com.sparecode.vipul.onlynow.view.StepIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignupFragment extends BaseFragment {

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

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                System.out.println("------>pos"+position);
                if (position == 0)
                {
                    ((BaseActivity)getActivity()).getTextViewToolBarTitle().setText("Sign Up");

                }
                else if (position == 1)
                {
                    ((BaseActivity)getActivity()).getTextViewToolBarTitle().setText("Select Active Area");
                    ((BaseActivity)getActivity()).getTextNext().setVisibility(View.VISIBLE);
                }
                else if (position == 2)
                {
                    ((BaseActivity)getActivity()).getTextViewToolBarTitle().setText("Select Favourite Category");
                    ((BaseActivity)getActivity()).getTextNext().setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {
                System.out.println("---->select"+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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
       // ((BaseActivity)getActivity()).getTextViewToolBarTitle().setText("Sign Up");
    }


    public class SectionPagerAdapter extends FragmentPagerAdapter
    {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0 :
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
            switch (position)
            {
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
