package com.erickogi14gmail.assettrack.Data.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Eric on 1/11/2018.
 */

public class DbOperations {

    private Context context;
    private DbClass dbHandler;


    public DbOperations(Context context) {
        dbHandler = new DbClass(context);
        this.context = context;
    }


    public int getCount(String tableName) {
        String countQuery = "SELECT  * FROM " + tableName;
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = -1;
        try {
            cnt = cursor.getCount();
        } catch (Exception nm) {

        }
        cursor.close();
        return cnt;
    }

    public int getCount(String table, String column, String key) {
        String countQuery = "SELECT  * FROM '" + table + "' WHERE " + column + " = '" + key + "' ";
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = 0;
        try {
            cnt = cursor.getCount();
        } catch (Exception nm) {

        }
        cursor.close();
        return cnt;
    }

    public boolean insert(String table, ContentValues values) {

        SQLiteDatabase db = dbHandler.getWritableDatabase();


        return db.insert(table, null, values) >= 1;


    }

    public boolean delete(String table, String column, int id) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        //boolean successful = db.delete(table, column + "= '" + id + "' ", null) > 0;
        db.execSQL("DELETE  FROM " + table + " WHERE KEY_ID = " + id + " ");
        db.close();
        return true;
    }

    public boolean deleteitem(String table, String column, int id) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        //boolean successful = db.delete(table, column + "= '" + id + "' ", null) > 0;
        db.execSQL("DELETE  FROM " + table + " WHERE " + column + " = " + id + " ");
        db.close();
        return true;
    }

    public boolean isThere(String table, String column, int id) {
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        boolean isThere = false;


        String QUERY = "SELECT * FROM " + table + "  WHERE " + column + " = " + id + " ";


        Log.d("isT", QUERY);
        Cursor cursor = db.rawQuery(QUERY, null);

        if (!cursor.isLast()) {

            if (cursor.moveToNext()) {
                isThere = true;

            }
        }
        db.close();


        return isThere;
    }

    public boolean update(String table, String column, int id, ContentValues cv) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();

        boolean successful = db.update(table, cv, column + "=" + id + " ", null) > 0;
        db.close();
        return successful;
    }

    public Cursor select(String table) {
        SQLiteDatabase db = dbHandler.getReadableDatabase();


        String QUERY = "SELECT * FROM  " + table + " ";

        Cursor cursor = db.rawQuery(QUERY, null);

        if (cursor.getCount() > 0) {


            db.close();

            return cursor;

        } else {
            db.close();

            return null;
        }


    }

    public Cursor select(String table, String column, String id) {
        SQLiteDatabase db = dbHandler.getReadableDatabase();


        String QUERY = null;


        QUERY = "SELECT * FROM  '" + table + "' WHERE " + column + " = '" + id + "' ";

        Cursor cursor = db.rawQuery(QUERY, null);


        if (cursor.getCount() > 0) {


            db.close();

            return cursor;

        } else {
            db.close();

            return null;
        }
    }

    public Cursor select(String table, String column, int id) {
        SQLiteDatabase db = dbHandler.getReadableDatabase();


        String QUERY = null;


        QUERY = "SELECT * FROM  '" + table + "' WHERE " + column + " = '" + id + "'  ";

        Cursor cursor = db.rawQuery(QUERY, null);


        if (cursor.getCount() > 0) {


            db.close();

            return cursor;

        } else {
            db.close();

            return null;
        }
    }



    public boolean delete(String table) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        //boolean successful =  db.execSQL("delete from "+ table)> 0;
        db.execSQL("delete from " + table);
        db.close();
        return true;
    }



    public Integer getID(String tableSavedTitles, String column, String title) {
        SQLiteDatabase db = dbHandler.getReadableDatabase();


        int id = 9999;
        String ttle = "";

        String QUERY = "SELECT * FROM  " + tableSavedTitles + "  WHERE " + column + " = '" + title + "'";

        Cursor cursor = db.rawQuery(QUERY, null);

        if (cursor != null && cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndex(DbConstants.KEY_ID));
            //ttle = cursor.getString(cursor.getColumnIndex(DbConstants.TITLE_NAME));
            Log.d("getID", " key id  1 fetched was " + id + "  Title  " + ttle);


            cursor.close();
        } else {
            Log.d("getID", " cursor is null" + id);

        }
        if (id >= 0) {
            return id;
        } else {
            return 9999;
        }

    }


    public String getItem(String table, String column, int id) {
        SQLiteDatabase db = dbHandler.getReadableDatabase();


        String sid = null;
        String ttle = "";

        String QUERY = "SELECT * FROM  " + table + "  WHERE " + column + " = '" + id + "'";

        Cursor cursor = db.rawQuery(QUERY, null);

        if (cursor != null && cursor.moveToFirst()) {
            sid = cursor.getString(cursor.getColumnIndex(column));
            //ttle = cursor.getString(cursor.getColumnIndex(DbConstants.TITLE_NAME));
            Log.d("colm", " key id  1 fetched was " + id + "  Title  " + ttle);


            cursor.close();
        } else {
            Log.d("colm", " cursor is null" + id);

        }
        return sid;

    }

    public Cursor select(String table, String column, String id1, String id2) {

        SQLiteDatabase db = dbHandler.getReadableDatabase();


        String QUERY = null;


        QUERY = "SELECT * FROM  '" + table + "' WHERE " + column + " = '" + id1 + "' OR " + column + " = '" + id2 + "' ";

        Cursor cursor = db.rawQuery(QUERY, null);


        if (cursor.getCount() > 0) {


            db.close();

            return cursor;

        } else {
            db.close();

            return null;
        }

    }
}
