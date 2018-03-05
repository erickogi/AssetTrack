package com.erickogi14gmail.assettrack.NetworkUtills;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.erickogi14gmail.assettrack.NetworkUtills.volley.MyApplication;
import com.erickogi14gmail.assettrack.Utills.UtilListeners.RequestListener;

import java.util.HashMap;
import java.util.Map;

//import com.android.volley.request.SimpleMultiPartRequest;
//import com.android.volley.request.StringRequest;

//import com.android.volley.toolbox.StringRequest;

/**
 * Created by Eric on 12/15/2017.
 */

public class DumbVolleyRequest {
    static String responseObj = null;

    public static String getPostData(String url, HashMap<String, String> params, RequestListener listener) {

        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, response -> {


            // responseObj = response;
            Log.d("dvrR", response);
            listener.onSuccess(response);


        }, error -> {
            Log.e("dvrVE", "Error: " + error.getMessage());
            listener.onError(error);

        }) {

            @Override
            protected Map<String, String> getParams() {

                Log.e("posting params", "Posting params: " + params.toString());

                return params;
            }

        };


        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq);


        return responseObj;

    }

    public static String getGetData(String url, RequestListener requestListener) {

        //responseObj = response;
// Log.d("dvrR", response);
        // Log.e("dvrVE", "Error: " + error.getMessage());
        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, requestListener::onSuccess, requestListener::onError) {


        };


        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq);


        return responseObj;

    }


//    public static void imageUpload(final String imagePath, String url, RequestListener listener) {
//
//
//        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, url,
//                response -> {
//                    Log.d("Response", response);
//
//                    Log.d("dvrR", response);
//                    listener.onSuccess(response);
//
//                },
//                error -> {
//                    Log.e("dvrVE", "Error: " + error.getMessage());
//                    listener.onError(error.toString());
//                });
//
//        smr.addFile("image", imagePath);
//        // smr.addStringParam()
//        MyApplication.getInstance().addToRequestQueue(smr);
//
//    }

}
