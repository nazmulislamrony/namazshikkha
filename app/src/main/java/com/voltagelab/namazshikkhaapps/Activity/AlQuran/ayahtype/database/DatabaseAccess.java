package com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.voltagelab.namazshikkhaapps.Activity.AlQuran.DatabaseHelper;

import java.util.ArrayList;

public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;

    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public ArrayList<String> getQuotes(int surahId) {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select arabic_indopak from quran_verses where sura_id = '" + surahId + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }





    //==========================for getting question id of sura mode=================================================================================================
    public ArrayList<String> getQuotesId(int surahId) {
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select sura_id,verse_id from quran_verses where sura_id = '" + surahId + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0) + "-" + cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }







}
