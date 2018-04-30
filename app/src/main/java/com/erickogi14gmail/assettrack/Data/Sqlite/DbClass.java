package com.erickogi14gmail.assettrack.Data.Sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Eric on 1/11/2018.
 */

public class DbClass extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "assetTrack.db";


    DbClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @NonNull
    private String createTable(String tableName, HashMap<String, String> fieldNames) {

        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE " + tableName + " (");

        Set set = fieldNames.entrySet();
        Iterator iterator = set.iterator();
        int i = 0;

        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();

            if ((i + 1) != fieldNames.size()) {
                builder.append(mentry.getKey() + " " + mentry.getValue() + ", ");
            } else {
                builder.append(mentry.getKey() + " " + mentry.getValue());
            }
            i++;
        }
        builder.append(");");


        return builder.toString();


    }

    private HashMap<String, String> creatV1Asset() {
        HashMap<String, String> fieldsName = new HashMap<>();

        fieldsName.put(DbConstants.KEY_ID, "INTEGER PRIMARY KEY  AUTOINCREMENT");


        fieldsName.put(DbConstants.ASSET_CODE, "varchar ");
        fieldsName.put(DbConstants.ASSET_IMAGE, "varchar ");
        fieldsName.put(DbConstants.ASSET_NAME, "varchar ");
        fieldsName.put(DbConstants.WARRANTY, "varchar ");
        fieldsName.put(DbConstants.WARRANTY_DURATION, "varchar ");
        fieldsName.put(DbConstants.MODEL, "varchar ");
        fieldsName.put(DbConstants.SERIAL, "varchar ");
        fieldsName.put(DbConstants.CONTRACT, "varchar ");
        fieldsName.put(DbConstants.ASSET_STATUS, "varchar ");
        fieldsName.put(DbConstants.ASSET_STATUS_ID, "varchar ");
        fieldsName.put(DbConstants.MANUFACTURER, "varchar ");
        fieldsName.put(DbConstants.YR_OF_MANUFACTURE, "varchar ");
        fieldsName.put(DbConstants.NEXTSERVICEDATE, "varchar ");
        fieldsName.put(DbConstants.CONTANCT_PERSON, "varchar ");
        fieldsName.put(DbConstants.CUSTOMER_ID, "varchar ");
        fieldsName.put(DbConstants.CONTACT_PERSON_POSITION, "varchar ");
        fieldsName.put(DbConstants.DEPARTMENT, "varchar ");
        fieldsName.put(DbConstants.ROOMSIZESTATUS, "varchar ");
        // fieldsName.put(DbConstants.OOM_EXPLANATION = "room_explanation";
        fieldsName.put(DbConstants.ROOM_MEETS_SPECIFICATION, "varchar ");
        fieldsName.put(DbConstants.ENGINEER_ID, "varchar ");
        fieldsName.put(DbConstants.TRAINEES, "varchar ");
        //fieldsName.put(DbConstants.TRAINEE_POSITION="trainee_position";
        fieldsName.put(DbConstants.ENGINEER_REMARKS, "varchar ");
        fieldsName.put(DbConstants.INSTALLATION_DATE, "varchar ");
        fieldsName.put(DbConstants.RECIEVERS_NAME, "varchar ");
        fieldsName.put(DbConstants.RECEIVER_DESIGNATION, "varchar ");
        fieldsName.put(DbConstants.RECEIVER_COMMENTS, "varchar ");
        fieldsName.put(DbConstants.ACCESSORIES, "varchar ");


        return fieldsName;
    }

    private HashMap<String, String> creatAsset() {
        HashMap<String, String> fieldsName = new HashMap<>();

        fieldsName.put(DbConstants.KEY_ID, "INTEGER PRIMARY KEY  AUTOINCREMENT");
         fieldsName.put(DbConstants.tag, "varchar ");
         fieldsName.put(DbConstants.type, "varchar ");
         fieldsName.put(DbConstants.image, "varchar ");
         fieldsName.put(DbConstants.site, "varchar ");
         fieldsName.put(DbConstants.serial, "varchar ");
         fieldsName.put(DbConstants.condition, "varchar ");
         fieldsName.put(DbConstants.installatiodate, "varchar ");
         fieldsName.put(DbConstants.last_maintenance, "varchar ");
         fieldsName.put(DbConstants.last_maintenance_by, "varchar ");

         fieldsName.put(DbConstants.installedby, "varchar ");
         fieldsName.put(DbConstants.model, "varchar ");
         fieldsName.put(DbConstants.contract, "varchar ");


        return fieldsName;
    }

    private HashMap<String, String> creatIssue() {
        HashMap<String, String> fieldsName = new HashMap<>();

        fieldsName.put(DbConstants.KEY_ID, "INTEGER PRIMARY KEY  AUTOINCREMENT");
        fieldsName.put(DbConstants.asset_id, "varchar ");
        fieldsName.put(DbConstants.message, "varchar ");
        fieldsName.put(DbConstants.date, "varchar ");
        fieldsName.put(DbConstants.issue, "varchar ");
        fieldsName.put(DbConstants.fix, "varchar ");
        fieldsName.put(DbConstants.comment, "varchar ");
        fieldsName.put(DbConstants.person, "varchar ");

        fieldsName.put(DbConstants.parts_needed, "varchar ");
        fieldsName.put(DbConstants.parts_used, "varchar ");


        fieldsName.put(DbConstants.next_service, "varchar ");


        fieldsName.put(DbConstants.cusomerremarks, "varchar ");
        fieldsName.put(DbConstants.engineersremarks, "varchar ");
        fieldsName.put(DbConstants.labourhours, "varchar ");
        fieldsName.put(DbConstants.travelhours, "varchar ");


        return fieldsName;
    }

    private HashMap<String, String> creatEngineer() {
        HashMap<String, String> fieldsName = new HashMap<>();


        fieldsName.put(DbConstants.KEY_ID, "INTEGER PRIMARY KEY  AUTOINCREMENT");
        fieldsName.put(DbConstants.id, "varchar ");
        fieldsName.put(DbConstants.name, "varchar ");
        fieldsName.put(DbConstants.email, "varchar ");
        fieldsName.put(DbConstants.phone, "varchar ");
        fieldsName.put(DbConstants.first_name, "varchar ");
        fieldsName.put(DbConstants.last_name, "varchar ");
        fieldsName.put(DbConstants.speciality, "varchar ");

        fieldsName.put(DbConstants.location, "varchar ");
        fieldsName.put(DbConstants.engagement, "varchar ");


        fieldsName.put(DbConstants.national_id, "varchar ");


        fieldsName.put(DbConstants.current_work_card_id, "varchar ");
        fieldsName.put(DbConstants.current_asset_id, "varchar ");
        fieldsName.put(DbConstants.current_issue, "varchar ");
        fieldsName.put(DbConstants.current_location, "varchar ");


        return fieldsName;
    }

    private HashMap<String, String> creatClient() {
        HashMap<String, String> fieldsName = new HashMap<>();


        fieldsName.put(DbConstants.KEY_ID, "INTEGER PRIMARY KEY  AUTOINCREMENT");
        fieldsName.put(DbConstants.cust_id, "varchar ");
        fieldsName.put(DbConstants.cust_name, "varchar ");
        fieldsName.put(DbConstants.cust_email, "varchar ");
        fieldsName.put(DbConstants.cust_telephone, "varchar ");
        fieldsName.put(DbConstants.cust_address, "varchar ");
        fieldsName.put(DbConstants.cust_physical_address, "varchar ");


        return fieldsName;
    }

    private HashMap<String, String> creatStatus() {
        HashMap<String, String> fieldsName = new HashMap<>();


        fieldsName.put(DbConstants.KEY_ID, "INTEGER PRIMARY KEY  AUTOINCREMENT");
        fieldsName.put(DbConstants.status, "varchar ");
        fieldsName.put(DbConstants.status_id, "varchar ");
        fieldsName.put(DbConstants.status_color, "varchar ");


        return fieldsName;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(createTable(DbConstants.TABLE_ITEMS, creatAsset()));
        db.execSQL(createTable(DbConstants.TABLE_ISSUE, creatIssue()));
        db.execSQL(createTable(DbConstants.TABLE_ITEMS_V1, creatV1Asset()));
        db.execSQL(createTable(DbConstants.TABLE_ENG_V1, creatEngineer()));
        db.execSQL(createTable(DbConstants.TABLE_CLIENT, creatClient()));
        db.execSQL(createTable(DbConstants.TABLE_STATUS, creatStatus()));



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_ISSUE);
        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_ITEMS_V1);
        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_ENG_V1);
        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_CLIENT);
        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_STATUS);



        onCreate(db);
    }

}
