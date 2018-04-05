package com.erickogi14gmail.assettrack.Data;

import com.erickogi14gmail.assettrack.Data.Models.V1.UserModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Eric on 1/26/2018.
 */

public class UserModelParse {

    public static UserModel getUser(String response) {
        UserModel userModel = new UserModel();

        try {
            JSONObject jsonObject = new JSONObject(response);

            JSONObject jsonObject1 = jsonObject.getJSONObject("profile");
            userModel.setUserName(jsonObject1.getString("name"));
            userModel.setFirstName(userModel.getUserName());
            userModel.setLastName(userModel.getUserName());

            userModel.setEmail(jsonObject1.getString("email"));
            userModel.setPhoneNumber(jsonObject1.getString("mobile"));
            userModel.setUserId(jsonObject1.getInt("id"));
            userModel.setPhoto(jsonObject1.getString("image"));


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return userModel;

    }
}
