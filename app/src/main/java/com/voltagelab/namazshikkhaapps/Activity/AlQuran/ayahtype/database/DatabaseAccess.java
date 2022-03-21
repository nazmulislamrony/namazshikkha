package com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.voltagelab.namazshikkhaapps.Activity.AlQuran.DatabaseHelper;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype.AyatDet;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype.AyatDetails;

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


    public ArrayList<AyatDetails> getAyahDetails(int surahId) {
        ArrayList<AyatDetails> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select arabic_indopak, verse_id, bn_muhi from quran_verses where sura_id = '" + surahId + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String arabic = cursor.getString(0);
            String verseid = cursor.getString(1);
            String bn_muhi = cursor.getString(2);
            AyatDetails ayatDetails = new AyatDetails(verseid,arabic,bn_muhi);

            if (surahId!=9 && verseid.equals("1")) {
                list.add(0,new AyatDetails("","بِسۡمِ اللّٰهِ الرَّحۡمٰنِ الرَّحِیۡمِ", "শুরু করছি আল্লাহর নামে যিনি পরম করুণাময়, অতি দয়ালু।"));
            }

            list.add(ayatDetails);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }














}
