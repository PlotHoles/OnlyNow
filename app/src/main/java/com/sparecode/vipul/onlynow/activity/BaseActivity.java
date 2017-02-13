package com.sparecode.vipul.onlynow.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.fragments.DetailsFragment;
import com.sparecode.vipul.onlynow.fragments.DoneFragment;
import com.sparecode.vipul.onlynow.fragments.ForgotFragment;
import com.sparecode.vipul.onlynow.fragments.HomeFragment;
import com.sparecode.vipul.onlynow.fragments.MapFragment;
import com.sparecode.vipul.onlynow.fragments.MylistFragment;
import com.sparecode.vipul.onlynow.fragments.NoticeFragment;
import com.sparecode.vipul.onlynow.fragments.ProfileFragment;
import com.sparecode.vipul.onlynow.fragments.ResetFragment;
import com.sparecode.vipul.onlynow.fragments.SearchFragment;
import com.sparecode.vipul.onlynow.fragments.SigninFragment;
import com.sparecode.vipul.onlynow.fragments.SignupFragment;
import com.sparecode.vipul.onlynow.fragments.SignupfacebookFragment;
import com.sparecode.vipul.onlynow.fragments.SplashFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vipul on 30/1/17.
 */

public class BaseActivity extends AppCompatActivity {


    @Bind(R.id.imgToolBarBack)
    ImageView imgToolBarBack;
    @Bind(R.id.imgToolBarCancel)
    ImageView imgToolBarCancel;
    @Bind(R.id.textViewToolBarTitle)
    TextView textViewToolBarTitle;
    @Bind(R.id.imgSettings)
    ImageView imgSettings;
    @Bind(R.id.text_next)
    TextView textNext;
    @Bind(R.id.imgMap)
    ImageView imgMap;
    @Bind(R.id.imgSearch)
    ImageView imgSearch;
    @Bind(R.id.imgShare)
    ImageView imgShare;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbarLayout)
    AppBarLayout appbarLayout;
    @Bind(R.id.container)
    FrameLayout container;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;

    public ImageView getImgToolBarCancel() {
        return imgToolBarCancel;
    }

    public void setImgToolBarCancel(ImageView imgToolBarCancel) {
        this.imgToolBarCancel = imgToolBarCancel;
    }

    public TextView getTextNext() {
        return textNext;
    }

    public void setTextNext(TextView textNext) {
        this.textNext = textNext;
    }

    public ImageView getImgMap() {
        return imgMap;
    }

    public void setImgMap(ImageView imgMap) {
        this.imgMap = imgMap;
    }

    public ImageView getImgSearch() {
        return imgSearch;
    }

    public void setImgSearch(ImageView imgSearch) {
        this.imgSearch = imgSearch;
    }

    public ImageView getImgShare() {
        return imgShare;
    }

    public void setImgShare(ImageView imgShare) {
        this.imgShare = imgShare;
    }

    public TabLayout getTabLayout() {
        return tabLayout;
    }

    public void setTabLayout(TabLayout tabLayout) {
        this.tabLayout = tabLayout;
    }

    public ImageView getImgToolBarBack() {
        return imgToolBarBack;
    }

    public void setImgToolBarBack(ImageView imgToolBarBack) {
        this.imgToolBarBack = imgToolBarBack;
    }

    public TextView getTextViewToolBarTitle() {
        return textViewToolBarTitle;
    }

    public void setTextViewToolBarTitle(TextView textViewToolBarTitle) {
        this.textViewToolBarTitle = textViewToolBarTitle;
    }

    public ImageView getImgSettings() {
        return imgSettings;
    }

    public void setImgSettings(ImageView imgSettings) {
        this.imgSettings = imgSettings;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public AppBarLayout getAppbarLayout() {
        return appbarLayout;
    }

    public void setAppbarLayout(AppBarLayout appbarLayout) {
        this.appbarLayout = appbarLayout;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    void replaceFirstFragment(Fragment mFragment, int id, String tag) {
        FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(id, mFragment);
        //DebugLog.e("TAG::" + tag);
        //mTransaction.addToBackStack(tag);
        mTransaction.commit();
    }

    void replaceFragment(Fragment mFragment, int id, String tag) {
        FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(id, mFragment);
        //DebugLog.e("TAG::" + tag);
        mTransaction.addToBackStack(tag);
        mTransaction.commit();
    }

    public void openSplashPage() {
        replaceFirstFragment(new SplashFragment(), R.id.container, SplashFragment.class.getName());
    }

    public void openSigninPage() {
        replaceFragment(new SigninFragment(), R.id.container, SigninFragment.class.getName());
    }

    public void openSignupfacebookPage() {
        replaceFragment(new SignupfacebookFragment(), R.id.container, SignupfacebookFragment.class.getName());
    }

    public void openSignupPage() {
        replaceFragment(new SignupFragment(), R.id.container, SignupFragment.class.getName());
    }

    public void openForgotPage() {
        replaceFragment(new ForgotFragment(), R.id.container, ForgotFragment.class.getName());
    }

    public void openResetPage() {
        replaceFragment(new ResetFragment(), R.id.container, ResetFragment.class.getName());
    }

    public void openDonePage() {
        replaceFragment(new DoneFragment(), R.id.container, DoneFragment.class.getName());
    }

    public void openHomePage() {
        replaceFragment(new HomeFragment(), R.id.container, HomeFragment.class.getName());
    }

    public void openNoticePage() {
        replaceFragment(new NoticeFragment(), R.id.container, NoticeFragment.class.getName());
    }

    public void openSearchPage() {
        replaceFragment(new SearchFragment(), R.id.container, SearchFragment.class.getName());
    }

    public void openMylistPage() {
        replaceFragment(new MylistFragment(), R.id.container, MylistFragment.class.getName());
    }

    public void openProfilePage() {
        replaceFragment(new ProfileFragment(), R.id.container, ProfileFragment.class.getName());
    }

    public void openDetailPage() {
        replaceFragment(new DetailsFragment(), R.id.container, DetailsFragment.class.getName());
    }
    public void openMapPage()
    {
        replaceFragment(new MapFragment(),R.id.container,MapFragment.class.getName());
    }

}
