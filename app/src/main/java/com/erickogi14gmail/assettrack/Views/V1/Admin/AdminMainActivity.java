package com.erickogi14gmail.assettrack.Views.V1.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.erickogi14gmail.assettrack.Data.PrefManager;
import com.erickogi14gmail.assettrack.R;
import com.erickogi14gmail.assettrack.Views.CreateAsset;
import com.erickogi14gmail.assettrack.Views.Login.Login;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class AdminMainActivity extends AppCompatActivity {
    private ResideMenu resideMenu;

    void setResideMenu() {
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.background_bf);
        resideMenu.attachToActivity(this);


        ResideMenuItem resideMenuItem = new ResideMenuItem(this, R.drawable.ic_home_black_24dp, "Home ");
        ResideMenuItem resideMenuItem0 = new ResideMenuItem(this, R.drawable.ic_add_bla_24dp, "Add Assets ");
        ResideMenuItem resideMenuItem1 = new ResideMenuItem(this, R.drawable.ic_account_circle_black_24dp, "Profile");
        ResideMenuItem resideMenuItem2 = new ResideMenuItem(this, R.drawable.ic_history_black_24dp, "History");
        ResideMenuItem resideMenuItem3 = new ResideMenuItem(this, R.drawable.ic_exit_to_app_black_24dp, "Log Out");

        resideMenu.addMenuItem(resideMenuItem, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(resideMenuItem3, ResideMenu.DIRECTION_LEFT);

        resideMenuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MyToast.toast("Home Clicked",MainActivity.this,R.drawable.ic_home_black_24dp, Constants.TOAST_LONG);
                //dial();
                resideMenu.closeMenu();
            }
        });
        resideMenuItem0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MyToast.toast("Home Clicked",MainActivity.this,R.drawable.ic_home_black_24dp, Constants.TOAST_LONG);
                resideMenu.closeMenu();
                startActivity(new Intent(AdminMainActivity.this, CreateAsset.class));
            }
        });
        resideMenuItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.closeMenu();
                // MyToast.toast("Account Clicked",MainActivity.this,R.drawable.ic_account_circle_black_24dp, Constants.TOAST_LONG);

            }
        });
        resideMenuItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.closeMenu();
                //  MyToast.toast("History Clicked",MainActivity.this,R.drawable.ic_history_black_24dp, Constants.TOAST_LONG);

            }
        });
        resideMenuItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resideMenu.closeMenu();
                PrefManager prefManager = new PrefManager(AdminMainActivity.this);
                prefManager.setIsLoggedIn(false, 1);
                startActivity(new Intent(AdminMainActivity.this, Login.class));
                finish();

                // MyToast.toast("Log Out Clicked",MainActivity.this,R.drawable.ic_exit_to_app_black_24dp, Constants.TOAST_LONG);
            }
        });
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // setResideMenu();
        setDrawerMenu();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.hide();
    }

    private void setDrawerMenu() {
        // DrawerClass drawerClass
    }

    public void manageStatus(View view) {
        startActivity(new Intent(this, ActivityManageIssues.class));

    }

    public void manageEngineers(View view) {
        startActivity(new Intent(this, ActivityManageEngineers.class));
    }

    public void manageClients(View view) {
        startActivity(new Intent(this, ActivityManageClients.class));
    }

    public void manageAssets(View view) {
        startActivity(new Intent(this, ActivityManageAssets.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        return resideMenu.dispatchTouchEvent(ev);
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_nav) {
            resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
        }

        return super.onOptionsItemSelected(item);
    }
}
