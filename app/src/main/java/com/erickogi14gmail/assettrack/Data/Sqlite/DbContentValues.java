package com.erickogi14gmail.assettrack.Data.Sqlite;

import android.database.Cursor;

import com.erickogi14gmail.assettrack.Data.Models.Assets;
import com.erickogi14gmail.assettrack.Data.Models.Issues;

import java.util.ArrayList;

/**
 * Created by Eric on 1/17/2018.
 */

public class DbContentValues {


    public interface MyInterface {
        void onComplete(boolean result);


    }

    public interface MyInterfaceTitles {

        void onComplete(int titleid);
    }


    public ArrayList<Assets> getAssets(Cursor cursor){
        ArrayList<Assets> assets=new ArrayList<>();

        if (!cursor.isLast()) {

            while (cursor.moveToNext()) {

                Assets assets1=new Assets();
                assets1.setCondition(cursor.getString(cursor.getColumnIndex(DbConstants.condition)));
                assets1.setDate(cursor.getString(cursor.getColumnIndex(DbConstants.installatiodate)));
                assets1.setImage(cursor.getString(cursor.getColumnIndex(DbConstants.image)));

                assets1.setKEY_ID(cursor.getInt(cursor.getColumnIndex(DbConstants.KEY_ID)));
                assets1.setLast_maintenance(cursor.getString(cursor.getColumnIndex(DbConstants.last_maintenance)));
                assets1.setLast_maintenance_by(cursor.getString(cursor.getColumnIndex(DbConstants.last_maintenance_by)));
                assets1.setSerial(cursor.getString(cursor.getColumnIndex(DbConstants.serial)));
                assets1.setSite(cursor.getString(cursor.getColumnIndex(DbConstants.site)));
                assets1.setTag(cursor.getString(cursor.getColumnIndex(DbConstants.tag)));
                assets1.setType(cursor.getString(cursor.getColumnIndex(DbConstants.type)));

                assets1.setModel(cursor.getString(cursor.getColumnIndex(DbConstants.model)));
                assets1.setInstalledby(cursor.getString(cursor.getColumnIndex(DbConstants.installedby)));
                assets1.setContract(cursor.getString(cursor.getColumnIndex(DbConstants.contract)));

                assets.add(assets1);

            }
        }

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return assets;
    }
    public ArrayList<Issues> getIssues(Cursor cursor){
        ArrayList<Issues> issuess=new ArrayList<>();
        if (!cursor.isLast()) {

            while (cursor.moveToNext()) {

                Issues issues=new Issues();
                issues.setAsset_id(cursor.getString(cursor.getColumnIndex(DbConstants.KEY_ID)));
                issues.setComment(cursor.getString(cursor.getColumnIndex(DbConstants.comment)));

                issues.setDate(cursor.getString(cursor.getColumnIndex(DbConstants.date)));
                issues.setFix(cursor.getString(cursor.getColumnIndex(DbConstants.fix)));
                issues.setIssue(cursor.getString(cursor.getColumnIndex(DbConstants.issue)));
                issues.setMessage(cursor.getString(cursor.getColumnIndex(DbConstants.message)));
                issues.setPerson(cursor.getString(cursor.getColumnIndex(DbConstants.person)));


                issues.setParts_needed(cursor.getString(cursor.getColumnIndex(DbConstants.parts_needed)));
                issues.setParts_used(cursor.getString(cursor.getColumnIndex(DbConstants.parts_used)));
                issues.setNext_service(cursor.getString(cursor.getColumnIndex(DbConstants.next_service)));

                issuess.add(issues);

            }
        }

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return issuess;
    }






}
