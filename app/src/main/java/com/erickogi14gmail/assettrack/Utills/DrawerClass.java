package com.erickogi14gmail.assettrack.Utills;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.Toolbar;

import com.erickogi14gmail.assettrack.R;
import com.erickogi14gmail.assettrack.Utills.UtilListeners.DrawerItemListener;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;

/**
 * Created by Eric on 12/13/2017.
 */

public class DrawerClass {
    static Drawer result;
    private Activity activity;

    private static Bitmap getBitmap(Activity activity, String img) {


        Bitmap thumnail = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_person_black_24dp);

        try {

            return GeneralUtills.loadImagesFromStorage(img, activity.getApplicationContext());

        } catch (Exception nm) {
            return thumnail;
        }


    }

    public static void getDrawer(final Activity activity, Toolbar toolbar, DrawerItemListener itemListener) {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        //Bitmap image = getBitmap(activity, img);
        PrimaryDrawerItem drawerEmptyItem = new PrimaryDrawerItem().withIdentifier(0).withName("");
        drawerEmptyItem.withEnabled(false);

        //  Typeface typeface=getTypeface(activity.getApplicationContext());


        PrimaryDrawerItem assets = new PrimaryDrawerItem().withIdentifier(1)
                .withName("Manage Assets").withTextColorRes(R.color.drawertext)
                .withIcon(R.drawable.assetmanager);


        PrimaryDrawerItem clients = new PrimaryDrawerItem().withIdentifier(2)
                .withName("Manage Clients").withTextColorRes(R.color.drawertext).withIcon(R.drawable.clients);
        PrimaryDrawerItem engineers = new PrimaryDrawerItem().withIdentifier(3)
                .withName("Manage Engineers").withTextColorRes(R.color.drawertext).withIcon(R.drawable.engineer)
                ;
//        PrimaryDrawerItem favorites = new PrimaryDrawerItem().withIdentifier(4)
//                .withName("Favorites").withIcon(R.drawable.ic_favorite_black_24dp)
//                .withBadge("4");


        PrimaryDrawerItem issues = new PrimaryDrawerItem().withIdentifier(4)
                .withName("Manage Issues").withTextColorRes(R.color.drawertext).withIcon(R.drawable.ic_error_outline_black_24dp);

        PrimaryDrawerItem settings = new PrimaryDrawerItem().withIdentifier(5)
                //.withIcon(R.drawable.ic_settings_black_24dp)
                .withName("Settings").withTextColorRes(R.color.drawertext);


        SecondaryDrawerItem logout = new SecondaryDrawerItem().withIdentifier(6)
                .withName("Log Out").withTextColorRes(R.color.drawertext);//.withIcon(R.drawable.ic_exit_to_app_black_24dp);


        SecondaryDrawerItem account = new SecondaryDrawerItem().withIdentifier(7)
                .withName("Account").withTextColorRes(R.color.drawertext);//.withIcon(R.drawable.ic_account_circle_black_24dp);


        PrimaryDrawerItem help = new PrimaryDrawerItem().withIdentifier(8)
                .withName("Help").withTextColorRes(R.color.drawertext);


        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(activity)
                .withOnProfileClickDrawerCloseDelay(2)
                .withTextColorRes(R.color.drawertext)
                .withSelectionListEnabledForSingleProfile(false)
                .withDividerBelowHeader(true)

                // .withHeaderBackground(R.drawable.headermain)
//                .addProfiles(
//                        new ProfileDrawerItem().withName(userModel.getUserName()).withEmail(userModel.getEmail())
//
//                                //.withSelectedTextColorRes(R.color.colorPrimaryDark)
//                                .withIcon(getBitmap(activity, userModel.getPhoto()))
//
//
//                )
                .withOnAccountHeaderListener((view, profile, currentProfile) -> false)
                .build();


        result = new DrawerBuilder()

                .withActivity(activity)
                //.withFooter(R.layout.footer)

                //.withGenerateMiniDrawer(true)
                .withFooterDivider(false)

                .withSelectedItem(1)

                .withToolbar(toolbar)
               // .withSliderBackgroundDrawableRes(R.drawable.headermain)

                // .withAccountHeader(headerResult)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withCloseOnClick(true)
                //.withSelectedItem(-1)
                .addDrawerItems(
                        // favorites,
                        assets,// payments, //new DividerDrawerItem(),
                        //messages,
                        clients,
                        engineers,
                        issues,
                        new DividerDrawerItem(),
                        account,


                        settings,

                        logout,
                        // invalidate,
                        new DividerDrawerItem(),
                        //logout,
                        //share
                        help
                        // about


                )
                .withFooterClickable(true)
                //.withStickyFooter(R.layout.footer)


                .withOnDrawerItemClickListener((view, position, drawerItem) -> {

                            switch ((int) drawerItem.getIdentifier()) {
                                case 1:
                                    itemListener.assetClicked();
                                    result.closeDrawer();
                                    break;

                                case 2:
                                    itemListener.clientsClicked();
                                    result.closeDrawer();
                                    break;

                                case 3:
                                    itemListener.engineersClicked();
                                    result.closeDrawer();
                                    break;
                                case 4:
                                    itemListener.issuesClicked();
                                    result.closeDrawer();
                                    break;
                                case 5:
                                    itemListener.settingsClicked();
                                    result.closeDrawer();
                                    break;
                                case 6:
                                    itemListener.logOutClicked();
                                    result.closeDrawer();
                                    break;

                                case 7:
                                    itemListener.accountClicked();
                                    result.closeDrawer();
                                    break;

                                case 8:
                                    itemListener.helpClicked();
                                    result.closeDrawer();
                                    break;


                            }
                            return true;
                        }
                )

                .build();
    }
}
