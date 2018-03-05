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
    private static final int DATABASE_VERSION = 1;
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



        return fieldsName;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(createTable(DbConstants.TABLE_ITEMS, creatAsset()));
        db.execSQL(createTable(DbConstants.TABLE_ISSUE, creatIssue()));



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + DbConstants.TABLE_ISSUE);



        onCreate(db);
    }

}
