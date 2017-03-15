package com.sparecode.vipul.onlynow.fragments;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sparecode.vipul.onlynow.Onlynow;
import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.interfaces.SignupNextListner;
import com.sparecode.vipul.onlynow.location.LocationProvider;
import com.sparecode.vipul.onlynow.model.ClientSignupWrapper;
import com.sparecode.vipul.onlynow.view.ClientStepIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ClientSignupFragment extends BaseFragment implements SignupNextListner,ClientSignupBackend.ClientSignupResultProvider,LocationProvider{

    @Bind(R.id.step_indicator)
    ClientStepIndicator stepIndicator;
    @Bind(R.id.pager)
    ViewPager pager;
    private SectionPagerAdapter sectionPagerAdapter;
    String fname,lname,cname,area,zipcode,prefecture,cityname,streetname,buildname,pnumber,emailaddress,cemailaddress,password,wurl;
    String cat_id;
    String category;
    private Context context;
    private View view;

    public ClientSignupFragment() {
        // Required empty public constructor
    }

    public ClientSignupFragment(Context context,String cat_id) {
        this.context = context;
        this.cat_id = cat_id;

        category = cat_id;
        System.out.println("cons"+category);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_client_signup, container, false);
        ButterKnife.bind(this, view);
        sectionPagerAdapter = new SectionPagerAdapter(getChildFragmentManager());
        pager.setAdapter(sectionPagerAdapter);


        System.out.println("---->cat_id"+cat_id);
        System.out.println("consdsadsasdsadsa"+category);
        stepIndicator.setupWithViewPager(pager);
        pager.setOffscreenPageLimit(2);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                System.out.println("------>pos" + position);
                if (position == 0) {
                    ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.signup));
                    ((BaseActivity) getActivity()).getAppbarLayout().findViewById(R.id.text_next).setVisibility(View.GONE);


                } else if (position == 1) {
                    ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.selectcategory));
                    ((BaseActivity) getActivity()).getTextNext().setVisibility(View.VISIBLE);
                    ((BaseActivity) getActivity()).getAppbarLayout().findViewById(R.id.imgSettings).setVisibility(View.GONE);
                    ((BaseActivity)getActivity()).getTextNext().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //((BaseActivity)getActivity()).openClientDonePage();
                            //Toast.makeText(getActivity(),"hi",Toast.LENGTH_SHORT).show();
                            Onlynow onlynow = (Onlynow)getActivity().getApplicationContext();
                            ClientSignupBackend clientSignupBackend = new ClientSignupBackend(context,onlynow.getFirst_name(),onlynow.getLname(),onlynow.getEmailaddress(),onlynow.getPassword(),"1.1","1.1",onlynow.getCname(),onlynow.getCat_id(),onlynow.getArea(),onlynow.getZipcode(),onlynow.getPrefecture(),onlynow.getCityname(),onlynow.getStreetname(),onlynow.getBuildname(),onlynow.getPnumber(),onlynow.getWurl(),"A","0000",ClientSignupFragment.this);
                        }
                    });
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

    /*public void call(Clientsignupsetter clientsignupsetter)
    {

        clientsignupsetter.getCat_id();
        clientsignupsetter.getFirst_name();
        Log.e("------>cat_id",clientsignupsetter.getCat_id());
        Log.e("------>first_name",clientsignupsetter.getFirst_name());
        Log.e("------>cat_id",clientsignupsetter.getLname());
        Log.e("------>cat_id",clientsignupsetter.getCname());
        Log.e("------>cat_id",clientsignupsetter.getArea());
        Log.e("------>cat_id",clientsignupsetter.getZipcode());
        Log.e("------>cat_id",clientsignupsetter.getPrefecture());
        Log.e("------>cat_id",clientsignupsetter.getCityname());
        Log.e("------>cat_id",clientsignupsetter.getStreetname());
        Log.e("------>cat_id",clientsignupsetter.getBuildname());
        Log.e("------>cat_id",clientsignupsetter.getPnumber());
        Log.e("------>cat_id",clientsignupsetter.getEmailaddress());
        Log.e("------>cat_id",clientsignupsetter.getCemailaddress());
        Log.e("------>cat_id",clientsignupsetter.getPassword());
        Log.e("------>cat_id",clientsignupsetter.getWurl());


    }*/
    public void performNext() {
        pager.setCurrentItem(getItem(+1));
    }

    public int getItem(int i) {
        return pager.getCurrentItem() + i;
    }
    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgToolBarBack().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.signup));
        ((BaseActivity)getActivity()).getTabLayoutclient().setVisibility(View.GONE);
        ((BaseActivity)getActivity()).getImgToolBarBack().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).openClientSplashPage();
            }
        });
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onSuccessfullLogin(ClientSignupWrapper clientSignupWrapper) {
        ((BaseActivity)getActivity()).openClientDonePage();
    }

    @Override
    public void onLoginFailure(String msg) {
        if (getActivity() != null)
        {
            Snackbar.make(view,msg,Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNewLcoationReceived(Location location) {
        Log.e("location",location+"");
    }

    public class SectionPagerAdapter extends FragmentPagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return ClientSignupstep1Fragment.newInstance("Sign Up");
                case 1:
                    return ClientSignupstep2Fragment.newInstance("Select Category");
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
            switch (position) {
                case 0:
                    return "Sign Up";
                case 1:
                    return "Select Category";
                default:
                    return null;
            }
        }
    }
}
