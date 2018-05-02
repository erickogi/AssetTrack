package com.erickogi14gmail.assettrack.Views.V1.Admin;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.erickogi14gmail.assettrack.GLConstants;
import com.erickogi14gmail.assettrack.Kogi.BottomNav.AHBottomNavigation;
import com.erickogi14gmail.assettrack.Kogi.BottomNav.AHBottomNavigationItem;
import com.erickogi14gmail.assettrack.R;
import com.erickogi14gmail.assettrack.Views.V1.Admin.ManageAssets.FragmentAll;

public class ActivityManageAssets extends AppCompatActivity {

    AHBottomNavigation bottomNavigation;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_assets);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        fragment = new FragmentAll();
        Bundle bundle = new Bundle();
        bundle.putInt("type", 0);
        bundle.putInt("STATUS_ID", 0);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment, "fragmentMain").commit();

        bottomNav();
        fab.hide();
    }

    private void bottomNav() {
        bottomNavigation = findViewById(R.id.bottom_navigation);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("All"//null);
                , R.drawable.ic_clear_all_black_24dp, R.color.white
        );
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Working",//null
                R.drawable.ic_verified_user_black_24dp, R.color.white
        );
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Faulty",//null
                R.drawable.ic_error_outline_black_24dp, R.color.white
        );
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Written Off",//null
                R.drawable.ic_phonelink_off_black_24dp, R.color.white
        );


// Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);

// Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#795548"));

// Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(true);

// Enable the translation of the FloatingActionButton
        //bottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);

// Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#FFFFFF"));
        bottomNavigation.setInactiveColor(Color.parseColor("#65D7A0"));
        // bottomNavigation.setAct(Color.parseColor("#65D7A0"));

// Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);

// Display color under navigation bar (API 21+)
// Don't forget these lines in your style-v21
// <item name="android:windowTranslucentNavigation">true</item>
// <item name="android:fitsSystemWindows">true</item>
        // bottomNavigation.setTranslucentNavigationEnabled(true);

// Manage titles
        // bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        // bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        // bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);

// Use colored navigation with circle reveal effect
        //bottomNavigation.setColored(true);

// Set current item programmatically
        bottomNavigation.setCurrentItem(0);
        // bottomNavigation.s

// Customize notification (title, background, typeface)
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));

// Add or remove notification for each item

//        int count=getCount(DbConstants.TABLE_SAVED_TITLES,
//                DbConstants.IMAGE_STATUS, String.valueOf(DbConstants.saved));
//
//        if(count>0) {
//            bottomNavigation.setNotification(String.valueOf(count), 1);
//        }

// OR
//        AHNotification notification = new AHNotification.Builder()
//                .setText("1")
//                .setBackgroundColor(ContextCompat.getColor(DemoActivity.this, R.color.color_notification_back))
//                .setTextColor(ContextCompat.getColor(DemoActivity.this, R.color.color_notification_text))
//                .build();
//        bottomNavigation.setNotification(notification, 1);

// Enable / disable item & set disable color
        // bottomNavigation.enableItemAtPosition(2);
        // bottomNavigation.disableItemAtPosition(2);
        // bottomNavigation.setItemDisableColor(Color.parseColor("#3A000000"));

// Set listeners
        bottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {
            // Do something cool here...
            Bundle bundle = new Bundle();
            fragment = new FragmentAll();
            switch (position) {
                case 0:

                    popOutFragments();
                    bundle.putInt("type", 0);
                    bundle.putInt("STATUS_ID", 0);
                    fragment.setArguments(bundle);
                    setFragment();
                    break;
                case 1:
                    popOutFragments();
                    bundle.putInt("type", 1);
                    bundle.putInt("STATUS_ID", GLConstants.WORKING);
                    fragment.setArguments(bundle);
                    setFragment();
                    break;
                case 2:
                    popOutFragments();
                    bundle.putInt("type", 2);
                    bundle.putInt("STATUS_ID", GLConstants.FAULTY);
                    fragment.setArguments(bundle);
                    setFragment();
                    break;

                case 3:
                    popOutFragments();
                    bundle.putInt("type", 3);
                    bundle.putInt("STATUS_ID", GLConstants.WRITTEN_OFF);
                    fragment.setArguments(bundle);
                    setFragment();
                    break;
            }
            return true;
        });
        bottomNavigation.setOnNavigationPositionListener(y -> {
            // Manage the new y position
        });

        try {
            setCount(0, 0);
        } catch (Exception nm) {
            nm.printStackTrace();
        }

    }

    public void setCount(int count, int pos) {
        try {

            if (count > 0) {

                bottomNavigation.setNotification(String.valueOf(count), pos);
            } else {
                bottomNavigation.setNotification("", pos);
            }
        } catch (Exception nm) {
            nm.printStackTrace();
        }
    }

    void setFragment() {
        // fragment = new FragmentSearch();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment, "fragmentMain").commit();
    }

    void popOutFragments() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }
    }

}
