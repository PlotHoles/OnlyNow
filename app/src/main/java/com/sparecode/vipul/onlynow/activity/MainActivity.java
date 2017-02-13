package com.sparecode.vipul.onlynow.activity;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sparecode.vipul.onlynow.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        openSplashPage();

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
    }
}
