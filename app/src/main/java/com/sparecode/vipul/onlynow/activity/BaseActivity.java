package com.sparecode.vipul.onlynow.activity;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.fragments.BaseFragment;
import com.sparecode.vipul.onlynow.fragments.ChangeCategoryFragment;
import com.sparecode.vipul.onlynow.fragments.ClientAddCouponFragment;
import com.sparecode.vipul.onlynow.fragments.ClientAnalyticsFragment;
import com.sparecode.vipul.onlynow.fragments.ClientCouponDetailsFragment;
import com.sparecode.vipul.onlynow.fragments.ClientCouponFragment;
import com.sparecode.vipul.onlynow.fragments.ClientDoneFragment;
import com.sparecode.vipul.onlynow.fragments.ClientEditprofileFragment;
import com.sparecode.vipul.onlynow.fragments.ClientReviewFragment;
import com.sparecode.vipul.onlynow.fragments.ClientSettingFragment;
import com.sparecode.vipul.onlynow.fragments.ClientShopFragment;
import com.sparecode.vipul.onlynow.fragments.ClientSigninFragment;
import com.sparecode.vipul.onlynow.fragments.ClientSignupFragment;
import com.sparecode.vipul.onlynow.fragments.ClientSignupstep2Fragment;
import com.sparecode.vipul.onlynow.fragments.ClientSplashFragment;
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
import com.sparecode.vipul.onlynow.interfaces.NetworkChangeListener;
import com.sparecode.vipul.onlynow.interfaces.SignupNextListner;
import com.sparecode.vipul.onlynow.model.FacebookWrapper;
import com.sparecode.vipul.onlynow.receiver.LocationReceiver;
import com.sparecode.vipul.onlynow.receiver.NetworkReceiver;

import java.lang.reflect.Method;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.sparecode.vipul.onlynow.R.id.text_next;

/**
 * Created by vipul on 30/1/17.
 */

public class BaseActivity extends AppCompatActivity implements NetworkChangeListener {


    @Bind(R.id.imgToolBarBack)
    ImageView imgToolBarBack;
    @Bind(R.id.imgToolBarCancel)
    ImageView imgToolBarCancel;
    @Bind(R.id.textViewToolBarTitle)
    TextView textViewToolBarTitle;
    @Bind(R.id.imgSettings)
    ImageView imgSetting;
    @Bind(R.id.imgMap)
    ImageView imgMap;
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
    public static int currentSignupPageNumber = 0;
    SignupNextListner signupNextListner;
    @Bind(R.id.text_next)
    TextView textNext;
    @Bind(R.id.imgSearchMap)
    ImageView imgSearchMap;
    @Bind(R.id.tabLayoutclient)
    TabLayout tabLayoutclient;
    @Bind(R.id.imgAdd)
    ImageView imgAdd;
    @Bind(R.id.imgEdit)
    ImageView imgEdit;
    NetworkReceiver receiver;
    IntentFilter filter;
    LocationReceiver locationReceiver;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    public SignupNextListner getSignupNextListner() {
        return signupNextListner;
    }

    public void setSignupNextListner(SignupNextListner signupNextListner) {
        this.signupNextListner = signupNextListner;
    }

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
        return imgSetting;
    }

    public void setImgSettings(ImageView imgSettings) {
        this.imgSetting = imgSettings;
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

    public ImageView getImgSearchMap() {
        return imgSearchMap;
    }

    public void setImgSearchMap(ImageView imgSearchMap) {
        this.imgSearchMap = imgSearchMap;
    }

    public TabLayout getTabLayoutclient() {
        return tabLayoutclient;
    }

    public void setTabLayoutclient(TabLayout tabLayoutclient) {
        this.tabLayoutclient = tabLayoutclient;
    }

    public ImageView getImgAdd() {
        return imgAdd;
    }

    public void setImgAdd(ImageView imgAdd) {
        this.imgAdd = imgAdd;
    }

    public ImageView getImgEdit() {
        return imgEdit;
    }

    public void setImgEdit(ImageView imgEdit) {
        this.imgEdit = imgEdit;
    }

    public FloatingActionButton getFab() {
        return fab;
    }

    public void setFab(FloatingActionButton fab) {
        this.fab = fab;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    void onSettingClick() {
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.home_menu_icon);
        toolbar.setOverflowIcon(drawable);
        setSupportActionBar(toolbar);
    }

    Menu menu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.settingmenu, menu);
        this.menu = menu;
        if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
            try {
                Method m = menu.getClass().getDeclaredMethod(
                        "setOptionalIconsVisible", Boolean.TYPE);
                m.setAccessible(true);
                m.invoke(menu, true);
            } catch (NoSuchMethodException e) {
                Log.e(" ", "onMenuOpened", e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return menuVisibilityFlag;
    }

    boolean menuVisibilityFlag = false;

    public void setOptionMenuVisibility(boolean isVisible) {
        menuVisibilityFlag = isVisible;
        onPrepareOptionsMenu(menu);
        invalidateOptionsMenu();
    }

    public boolean onPrepareOptionsMenu(final Menu menu) {
        return menuVisibilityFlag;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.sortby:

                Toast.makeText(BaseActivity.this, "Sort By is Selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.popular:
                Toast.makeText(BaseActivity.this, "Popular Selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.newmenu:
                Toast.makeText(BaseActivity.this, "New is Selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.finishsoon:
                Toast.makeText(BaseActivity.this, "Finish Soon is Selected", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    @OnClick(R.id.imgSettings)
//    void onSettingClick() {
//        PopupMenu popup = new PopupMenu(BaseActivity.this,imgSetting);
//        popup.getMenuInflater().inflate(R.menu.settingmenu, popup.getMenu());
//        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                Toast.makeText(BaseActivity.this,"You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
//                int id = item.getItemId();
//
//                return true;
//            }
//        });
//        popup.show();
//    }


    /*void replaceFirstFragment(Fragment mFragment, int id, String tag) {
        FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(id, mFragment);
        //DebugLog.e("TAG::" + tag);
        //mTransaction.addToBackStack(tag);
        mTransaction.commit();
    }*/

    public void replaceFragment(Fragment mFragment, int id, String tag, boolean addToStack) {
        FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(id, mFragment);
        hideKeyboard();
        //DebugLog.e("TAG::" + tag);
        if (addToStack) {
            mTransaction.addToBackStack(tag);
        }
        mTransaction.commit();
    }

    @OnClick(R.id.imgToolBarCancel)
    void onCancelClick() {
        onBackPressed();
    }

    /*@OnClick(R.id.imgToolBarBack)
    void onBackIconClick() {
        onBackPressed();
    }*/

    @OnClick(text_next)
    void onNextTextClick() {
        onSignupNextClickPerform();
    }

    public void addFragment(BaseFragment fragment, boolean isReplace) {
        hideKeyboard();
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.popBackStackImmediate(fragment.getClass().getName(),
                FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void openSplashPage() {
        replaceFragment(new SplashFragment(), R.id.container, SplashFragment.class.getName(), false);
    }

    public void openSigninPage() {
        replaceFragment(new SigninFragment(), R.id.container, SigninFragment.class.getName(), true);
    }

    public void openSignupfacebookPage() {
        replaceFragment(new SignupfacebookFragment(), R.id.container, SignupfacebookFragment.class.getName(), true);
    }

    public void openSignupPage() {
        replaceFragment(new SignupFragment(), R.id.container, SignupFragment.class.getName(), true);
    }
    public void openSignupPage(FacebookWrapper facebookWrapper) {
        replaceFragment(new SignupFragment(facebookWrapper), R.id.container, SignupFragment.class.getName(), true);
    }
    public void openForgotPage() {
        replaceFragment(new ForgotFragment(), R.id.container, ForgotFragment.class.getName(), true);
    }

    public void openResetPage() {
        replaceFragment(new ResetFragment(), R.id.container, ResetFragment.class.getName(), true);
    }

    public void openDonePage() {
        replaceFragment(new DoneFragment(), R.id.container, DoneFragment.class.getName(), true);
    }

    public void openHomePage() {
        replaceFragment(new HomeFragment(), R.id.container, HomeFragment.class.getName(), true);
    }

    public void openNoticePage() {
        replaceFragment(new NoticeFragment(), R.id.container, NoticeFragment.class.getName(), true);
    }

    public void openSearchPage() {
        replaceFragment(new SearchFragment(), R.id.container, SearchFragment.class.getName(), true);
    }

    public void openMylistPage() {
        replaceFragment(new MylistFragment(), R.id.container, MylistFragment.class.getName(), true);
    }

    public void openProfilePage() {
        replaceFragment(new ProfileFragment(), R.id.container, ProfileFragment.class.getName(), true);
    }

    public void openDetailPage(String couponId,String shopId,String userId,String Id) {
        replaceFragment(new DetailsFragment(couponId,shopId,userId,Id), R.id.container, DetailsFragment.class.getName(), true);
    }

    public void openMapPage() {
        replaceFragment(new MapFragment(), R.id.container, MapFragment.class.getName(), true);
    }

    public void openClientSplashPage() {
        replaceFragment(new ClientSplashFragment(), R.id.container, ClientSplashFragment.class.getName(), false);
    }

    public void openClientSigninPage() {
        replaceFragment(new ClientSigninFragment(), R.id.container, ClientSigninFragment.class.getName(), true);
    }

    public void openClientSignupPage() {
        replaceFragment(new ClientSignupFragment(), R.id.container, ClientSignupFragment.class.getName(), true);
    }

    public void openClientDonePage() {
        replaceFragment(new ClientDoneFragment(), R.id.container, ClientDoneFragment.class.getName(), true);
    }

    public void openClientCouponPage() {
        replaceFragment(new ClientCouponFragment(), R.id.container, ClientCouponFragment.class.getName(), true);
    }

    public void openClientShopPage() {
        replaceFragment(new ClientShopFragment(), R.id.container, ClientShopFragment.class.getName(), true);
    }

    public void openClientReviewPage() {
        replaceFragment(new ClientReviewFragment(), R.id.container, ClientReviewFragment.class.getName(), true);
    }

    public void openClientAnalyticsPage() {
        replaceFragment(new ClientAnalyticsFragment(), R.id.container, ClientAnalyticsFragment.class.getName(), true);
    }

    public void openClientSettingPage() {
        replaceFragment(new ClientSettingFragment(), R.id.container, ClientSettingFragment.class.getName(), true);
    }

    public void openClientCouponDetailsPage() {
        replaceFragment(new ClientCouponDetailsFragment(), R.id.container, ClientCouponDetailsFragment.class.getName(), true);
    }

    public void openClientSignupstep2Fragment() {
        replaceFragment(new ClientSignupstep2Fragment(), R.id.container, ClientSignupstep2Fragment.class.getName(), true);
    }

    public void openClientChangeCategoryPage(){
        replaceFragment(new ChangeCategoryFragment(),R.id.container,ChangeCategoryFragment.class.getName(),true);
    }

    public void openClientAddCouponFragment(){
        replaceFragment(new ClientAddCouponFragment(),R.id.container,ClientAddCouponFragment.class.getName(),true);
    }
    public void openClientEditProfileFragment(){
        replaceFragment(new ClientEditprofileFragment(),R.id.container,ClientEditprofileFragment.class.getName(),true);
    }

    public void onSignupNextClickPerform() {
        if (getSignupNextListner() != null) {
            // Toast.makeText(this, "::" + currentSignupPageNumber, Toast.LENGTH_SHORT).show();
            if (currentSignupPageNumber == 1) {
                Log.e(" ::: PAGE 2 CLICK", " ::: ");
                getSignupNextListner().onSignupNextClick(true);
            } else {
                openDonePage();
            }

        } else {
            Log.e(":::NULL CALL BACK:::", "FROM BASE ACTIVITY");
        }
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        receiver = new NetworkReceiver();
        filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        locationReceiver = new LocationReceiver();
        registerReceiver(receiver, filter);
        registerReceiver(locationReceiver, new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION));
    }

    @Override
    protected void onStop() {
        unregisterReceiver(receiver);
        unregisterReceiver(locationReceiver);
        super.onStop();
    }

    @Override
    public void onConnected() {
        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisconnected() {
        Toast.makeText(this, "DisConnected", Toast.LENGTH_SHORT).show();
    }
}
