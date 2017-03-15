package com.sparecode.vipul.onlynow.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.fragments.ClientAnalyticsFragment;
import com.sparecode.vipul.onlynow.fragments.ClientCouponFragment;
import com.sparecode.vipul.onlynow.fragments.ClientReviewFragment;
import com.sparecode.vipul.onlynow.fragments.ClientSettingFragment;
import com.sparecode.vipul.onlynow.fragments.ClientShopFragment;
import com.sparecode.vipul.onlynow.fragments.ClientSigninFragment;
import com.sparecode.vipul.onlynow.fragments.HomeFragment;
import com.sparecode.vipul.onlynow.fragments.MylistFragment;
import com.sparecode.vipul.onlynow.fragments.NoticeFragment;
import com.sparecode.vipul.onlynow.fragments.ProfileFragment;
import com.sparecode.vipul.onlynow.fragments.SearchFragment;
import com.sparecode.vipul.onlynow.model.LoginData;
import com.sparecode.vipul.onlynow.permission.PiemissionsUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


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
    @Bind(R.id.imgSearchMap)
    ImageView imgSearchMap;
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
    @Bind(R.id.tabLayoutclient)
    TabLayout tabLayoutclient;
    @Bind(R.id.imgAdd)
    ImageView imgAdd;
    @Bind(R.id.imgEdit)
    ImageView imgEdit;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        PiemissionsUtils.init(this);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        onSettingClick();
        openSplashPage();

//        tabLayout.addTab(tabLayout.newTab().setText("Home").setIcon(R.drawable.home_sel_icon));
//        tabLayout.addTab(tabLayout.newTab().setText("Notice"));
//        tabLayout.addTab(tabLayout.newTab().setText("Search"));
//        tabLayout.addTab(tabLayout.newTab().setText("My List"));
//        tabLayout.addTab(tabLayout.newTab().setText("Profile"));


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.headercolor), PorterDuff.Mode.SRC_IN);

                System.out.println("---->position" + tab.getPosition());

                switch (tab.getPosition()) {
                    case 0:

                        openHomePage();
                        break;
                    case 1:
                        openNoticePage();
                        break;
                    case 2:
                        openSearchPage();
                        break;
                    case 3:
                        openMylistPage();
                        break;
                    case 4:
                        openProfilePage();
                        break;
                }
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.grey), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tabLayoutclient.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.headercolor), PorterDuff.Mode.SRC_IN);
                switch (tab.getPosition()) {
                    case 0:
                        openClientCouponPage();
                        break;
                    case 1:
                        openClientShopPage();
                        break;
                    case 2:
                        openClientReviewPage();
                        break;
                    case 3:
                        openClientAnalyticsPage();
                        break;
                    case 4:
                        openClientSettingPage();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(getResources().getColor(R.color.grey), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PiemissionsUtils.onRequestResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        Log.e("1:::->", "" + fragment.getClass().getName());
        if (fragment instanceof HomeFragment || fragment instanceof NoticeFragment || fragment instanceof SearchFragment || fragment instanceof MylistFragment || fragment instanceof ProfileFragment) {
            finish();
        } else if (fragment instanceof ClientCouponFragment || fragment instanceof ClientShopFragment || fragment instanceof ClientReviewFragment || fragment instanceof ClientAnalyticsFragment || fragment instanceof ClientSettingFragment) {
            finish();
        }  else if (fragment instanceof ClientSigninFragment)
        {
            finish();
        }
        else {
            super.onBackPressed();
        }
    }

    public LoginData getUserData() {
        String data = sharedpreferences.getString("user", null);
        if (data != null) {
            Log.e(this.getClass().getName(), "::: DATA IS ::: " + data);
            return new Gson().fromJson(data, LoginData.class);
        }
        return null;
    }

    public void setUserData(LoginData data) {

        editor.putString("user", new Gson().toJson(data));
        editor.commit();
    }
}
