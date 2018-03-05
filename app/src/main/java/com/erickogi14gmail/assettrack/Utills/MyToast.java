package com.erickogi14gmail.assettrack.Utills;

import android.content.Context;
import android.widget.Toast;

import com.erickogi14gmail.assettrack.R;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

/**
 * Created by Eric on 11/29/2017.
 */

public class MyToast {
    public static void toast(String msg, Context context, int icon, int Lenght) {
        StyleableToast st = null;
        if (Lenght == Constants.TOAST_LONG)
            st = new StyleableToast(context, msg, Toast.LENGTH_LONG);
        else if (Lenght == Constants.TOAST_SHORT) {
            st = new StyleableToast(context, msg, Toast.LENGTH_SHORT);
        }
        if (st != null) {
            st.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            st.setTextColor(context.getResources().getColor(R.color.white));
            st.setIcon(icon);


            st.setMaxAlpha();
            st.show();
        }


    }

}
