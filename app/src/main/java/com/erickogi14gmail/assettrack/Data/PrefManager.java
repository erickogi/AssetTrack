package com.erickogi14gmail.assettrack.Data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Eric on 1/5/2018.
 */

public class PrefManager {
    // Shared preferences file name
    private static final String PREF_NAME = "Odijo";
    // All Shared Preferences Keys
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_ACCOUNT_NAME = "key_googleAccount";

    private static final String KEY_USER_ID = "UserId";
    private static final String KEY_USER_NAME = "UserName";
    private static final String KEY_USER_F_NAME = "FirstName";
    private static final String KEY_USER_L_NAME = "LastName";
    private static final String KEY_USER_EMAIL = "Email";
    private static final String KEY_USER_PHONENumber = "PhoneNumber";
    private static final String KEY_USER_Designation = "Designation";
    private static final String KEY_USER_USERTYPE = "UserType";
    private static final String KEY_USER_PHOTO = "Photo";

    private static final String KEY_USER_FB_ID= "fbid";
    private static final String KEY_USER_FB_TOKEN= "fbtoken";

    private static final String PICCASA_WEB_USERID="picassauserid";

    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;



    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }
    public void setIsLoggedIn(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.commit();
    }

    public UserModel getUserData() {
        UserModel userData = new UserModel();
        userData.setUserId(pref.getInt(KEY_USER_ID, 0));
        userData.setUserName(pref.getString(KEY_USER_NAME, "NULL"));
        userData.setFirstName(pref.getString(KEY_USER_F_NAME, "NULL"));
        userData.setLastName(pref.getString(KEY_USER_L_NAME, "NULL"));
        userData.setEmail(pref.getString(KEY_USER_EMAIL, "NULL"));
        userData.setPhoneNumber(pref.getString(KEY_USER_PHONENumber, "NULL"));
       // userData.setDesignation(pref.getString(KEY_USER_Designation, "NULL"));
       // userData.setUserType(pref.getInt(KEY_USER_USERTYPE, 0));
        userData.setPhoto(pref.getString(KEY_USER_PHOTO, "NULL"));


        return userData;
    }

    ///SET AND GET USER DATA
    public void setUserData(UserModel userData) {


        editor.putInt(KEY_USER_ID, userData.getUserId());
        editor.putString(KEY_USER_NAME, userData.getUserName());
        editor.putString(KEY_USER_F_NAME, userData.getFirstName());
        editor.putString(KEY_USER_L_NAME, userData.getLastName());
        editor.putString(KEY_USER_EMAIL, userData.getEmail());
        editor.putString(KEY_USER_PHONENumber, userData.getPhoneNumber());
        //editor.putString(KEY_USER_Designation, userData.getDesignation());
        // editor.putInt(KEY_USER_USERTYPE, userData.getUserType());
        editor.putString(KEY_USER_PHOTO, userData.getPhoto());


        editor.commit();

    }

    public String getGoogleAccountName() {
        return pref.getString(KEY_ACCOUNT_NAME, "null");
    }

    public void setGoogleAccountName(String accountName) {
        editor.putString(KEY_ACCOUNT_NAME, accountName);
        editor.commit();
    }
}
