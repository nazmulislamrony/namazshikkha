package com.voltagelab.namazshikkhaapps.Activity.AlQuran;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.voltagelab.namazshikkhaapps.Activity.AlQuran.model.SurahModel;

import java.util.ArrayList;

/** Created by Sadmansamee on 7/19/15. */
public class SurahDataSource {

  public static final String SURAH_TABLE_NAME = "surah_name";
  public static final String SURAH_ID = "_id";
  public static final String SURAH_ID_TAG = "surah_id";
  public static final String SURAH_NO = "surah_no";
  public static final String SURAH_NAME_ARABIC = "name_arabic";
  public static final String SURAH_NAME_ENGLISH = "name_english";
  public static final String SURAH_NAME_TRANSLATE = "name_translate";
  public static final String SURAH_NAME_BANGLA = "name_bangla";
  public static final String SURAH_MEAN_ENGLISH = "surah_mean_english";
  public static final String SURAH_ARTI_NAMA = "arti_nama";
  public static final String SURAH_AYAH_NUMBER = "ayah_number";
  public static final String SURAH_TYPE = "type";
  private static Cursor cursor;
  private DatabaseHelper databaseHelper;

  public SurahDataSource(Context context) {
    databaseHelper = new DatabaseHelper(context);
  }

  public ArrayList<SurahModel> getEnglishSurahArrayList() {

    ArrayList<SurahModel> surahModelArrayList = new ArrayList<>();
    SQLiteDatabase db = databaseHelper.getReadableDatabase();
    cursor =
        db.rawQuery(
            "SELECT surah_name._id,surah_name.name_arabic,surah_name.name_english,surah_name.ayah_number FROM surah_name",
            null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      SurahModel surahModel = new SurahModel();
      surahModel.setId(cursor.getLong(cursor.getColumnIndexOrThrow(SURAH_ID)));
      surahModel.setNameArabic(cursor.getString(cursor.getColumnIndexOrThrow(SURAH_NAME_ARABIC)));
      surahModel.setNameTranslate(cursor.getString(cursor.getColumnIndexOrThrow(SURAH_NAME_ENGLISH)));
      surahModel.setAyahNumber(cursor.getLong(cursor.getColumnIndexOrThrow(SURAH_AYAH_NUMBER)));
      surahModelArrayList.add(surahModel);
      cursor.moveToNext();
    }
    cursor.close();
    db.close();
    return surahModelArrayList;
  }

  public ArrayList<SurahModel> getBanglaSurahArrayList() {

    long banglaStart = 28;

    ArrayList<SurahModel> surahModelArrayList = new ArrayList<>();
    SQLiteDatabase db = databaseHelper.getReadableDatabase();
    cursor =
        db.rawQuery(
            "SELECT surah_name._id,surah_name.name_arabic,surah_name.name_bangla,surah_name.ayah_number FROM surah_name",
            null); // where surah_name._id >= " + banglaStart

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      SurahModel surahModel = new SurahModel();
      surahModel.setId(cursor.getLong(cursor.getColumnIndexOrThrow(SURAH_ID)));
      surahModel.setNameArabic(cursor.getString(cursor.getColumnIndexOrThrow(SURAH_NAME_ARABIC)));
      surahModel.setNameTranslate(cursor.getString(cursor.getColumnIndexOrThrow(SURAH_NAME_BANGLA)));
      surahModel.setAyahNumber(cursor.getLong(cursor.getColumnIndexOrThrow(SURAH_AYAH_NUMBER)));
      surahModelArrayList.add(surahModel);
      cursor.moveToNext();
    }
    cursor.close();
    db.close();
    return surahModelArrayList;
  }

  public ArrayList<SurahModel> getIndonesianSurahArrayList() {

    ArrayList<SurahModel> surahModelArrayList = new ArrayList<>();
    SQLiteDatabase db = databaseHelper.getReadableDatabase();
    cursor =
        db.rawQuery(
            "SELECT surah_name._id,surah_name.name_arabic,surah_name.arti_nama,surah_name.ayah_number FROM surah_name",
            null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      SurahModel surahModel = new SurahModel();
      surahModel.setId(cursor.getLong(cursor.getColumnIndexOrThrow(SURAH_ID)));
      surahModel.setNameArabic(cursor.getString(cursor.getColumnIndexOrThrow(SURAH_NAME_ARABIC)));
      surahModel.setNameTranslate(cursor.getString(cursor.getColumnIndexOrThrow(SURAH_ARTI_NAMA)));
      surahModel.setAyahNumber(cursor.getLong(cursor.getColumnIndexOrThrow(SURAH_AYAH_NUMBER)));
      surahModelArrayList.add(surahModel);
      cursor.moveToNext();
    }
    cursor.close();
    db.close();
    return surahModelArrayList;
  }


}
