package com.erickogi14gmail.assettrack.NetworkUtills.volley;

//import com.android.volley.VolleyError;

//import com.android.volley.error.VolleyError;

import com.android.volley.VolleyError;

/**
 * Created by kimani kogi on 5/27/2017.
 */

public interface IResult {
    void notifySuccess(String requestType, String response);

    void notifyError(String requestType, VolleyError error);
}
