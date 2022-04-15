package com.voltagelab.namazshikkhaapps.DatabaseAsset;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.voltagelab.namazshikkhaapps.Activity.NintyNineNames.NintyNimesModel;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.ContentStore;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.EighthStore;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.EleventhStore;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.FifthStore;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.FourthStore;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.NinthStore;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.SecondStore;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.SeventhStore;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.SixthStore;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.TenthStore;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.ThirdStore;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.TwelveStore;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DbAsset extends SQLiteAssetHelper {

    private static String DATABASE_NAME="namaz-upgrade_4-5.db";
    private static int VERSION_NUMBER=4;
    private static Cursor cursor;


    public DbAsset(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
    }

    public  static String ID = "id";
    public  static String AR_NAME = "ar_name";
    public  static String EN_NAME = "en_name";
    public  static String BN_NAME = "bn_name";
    public  static String BN_MEANING = "bn_meaning";
    public  static String EN_MEANING = "en_meaning";
    public  static String FAZILAT_BN = "fazilat_bn";
    public  static String FAZILAT_EN = "fazilat_en";
    public  static String REFERENCES = "reference";



    //------------- Function to get all data from database---------------------


    public List<ContentStore> getOju(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        SQLiteQueryBuilder queryBuilder=new SQLiteQueryBuilder();

        String [] sqSelect={"id","name","content"};
        String tableName="oju";
        queryBuilder.setTables(tableName);
        Cursor cursor=queryBuilder.query(sqLiteDatabase,sqSelect,null,null,null,null,null);

        List<ContentStore> result=new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                ContentStore contentStore=new ContentStore();
                contentStore.setId(cursor.getInt(cursor.getColumnIndex("id")));
                contentStore.setDataName(cursor.getString(cursor.getColumnIndex("name")));
                contentStore.setDataContent(cursor.getString(cursor.getColumnIndex("content")));
                result.add(contentStore);
            }while (cursor.moveToNext());
        }
        return result;
    }

    // ----------------------- For second Table Data collect -------------------------------------------

    public List<SecondStore> getGosolInfo(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        SQLiteQueryBuilder queryBuilder=new SQLiteQueryBuilder();

        String [] sqSelect={"id", "name","content"};
        String tableName="gosol";
        queryBuilder.setTables(tableName);
        Cursor cursor=queryBuilder.query(sqLiteDatabase,sqSelect,null,null,null,null,null);

        List<SecondStore> result=new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                SecondStore secondStore=new SecondStore();
                secondStore.setId(cursor.getInt(cursor.getColumnIndex("id")));
                secondStore.setName(cursor.getString(cursor.getColumnIndex("name")));
                secondStore.setContent(cursor.getString(cursor.getColumnIndex("content")));
                result.add(secondStore);
            }while (cursor.moveToNext());
        }
        return result;
    }

    //====================== For Third table data collection ==============================================

    public List<ThirdStore> getTayammum(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        SQLiteQueryBuilder sqLiteQueryBuilder=new SQLiteQueryBuilder();

        String [] sqSelect={"id","name","content"};
        String tableName="tayammum";

        sqLiteQueryBuilder.setTables(tableName);
        Cursor cursor=sqLiteQueryBuilder.query(sqLiteDatabase,sqSelect,null,null,null,null,null);

        List<ThirdStore> result=new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                ThirdStore thirdStore=new ThirdStore();
                thirdStore.setId(cursor.getInt(cursor.getColumnIndex("id")));
                thirdStore.setName(cursor.getString(cursor.getColumnIndex("name")));
                thirdStore.setContent(cursor.getString(cursor.getColumnIndex("content")));
                result.add(thirdStore);
            }while (cursor.moveToNext());
        }
        return  result;
    }


    //====================== For Fourth table data collection ==============================================

    public List<FourthStore> getNamazerOwakto(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        SQLiteQueryBuilder sqLiteQueryBuilder=new SQLiteQueryBuilder();

        String [] sqSelect={"id","name","content"};
        String tableName="namazer_owakto";

        sqLiteQueryBuilder.setTables(tableName);
        Cursor cursor=sqLiteQueryBuilder.query(sqLiteDatabase,sqSelect,null,null,null,null,null);

        List<FourthStore> result=new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                FourthStore fourthStore=new FourthStore();
                fourthStore.setId(cursor.getInt(cursor.getColumnIndex("id")));
                fourthStore.setName(cursor.getString(cursor.getColumnIndex("name")));
                fourthStore.setContent(cursor.getString(cursor.getColumnIndex("content")));
                result.add(fourthStore);
            }while (cursor.moveToNext());
        }
        return  result;
    }



    //====================== For Fifth table data collection ==============================================

    public List<FifthStore> getNamazerMasal(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        SQLiteQueryBuilder sqLiteQueryBuilder=new SQLiteQueryBuilder();

        String [] sqSelect={"id","name","content"};
        String tableName="namazer_masala";

        sqLiteQueryBuilder.setTables(tableName);
        Cursor cursor=sqLiteQueryBuilder.query(sqLiteDatabase,sqSelect,null,null,null,null,null);

        List<FifthStore> result=new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                FifthStore fifthStore=new FifthStore();
                fifthStore.setId(cursor.getInt(cursor.getColumnIndex("id")));
                fifthStore.setName(cursor.getString(cursor.getColumnIndex("name")));
                fifthStore.setContent(cursor.getString(cursor.getColumnIndex("content")));
                result.add(fifthStore);
            }while (cursor.moveToNext());
        }
        return  result;
    }


    //====================== For Sixth table data collection ==============================================

    public List<SixthStore> getNamazAdayerNiom(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        SQLiteQueryBuilder sqLiteQueryBuilder=new SQLiteQueryBuilder();

        String [] sqSelect={"id","name","content"};
        String tableName="namaz_adayer_niom";

        sqLiteQueryBuilder.setTables(tableName);
        Cursor cursor=sqLiteQueryBuilder.query(sqLiteDatabase,sqSelect,null,null,null,null,null);

        List<SixthStore> result=new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                SixthStore sixthStore=new SixthStore();
                sixthStore.setId(cursor.getInt(cursor.getColumnIndex("id")));
                sixthStore.setName(cursor.getString(cursor.getColumnIndex("name")));
                sixthStore.setContent(cursor.getString(cursor.getColumnIndex("content")));
                result.add(sixthStore);
            }while (cursor.moveToNext());
        }
        return  result;
    }

    //====================== For Seventh table data collection ==============================================

    public List<SeventhStore> getEidNamaz(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        SQLiteQueryBuilder sqLiteQueryBuilder=new SQLiteQueryBuilder();

        String [] sqSelect={"id","name","content"};
        String tableName="eid_namaz";

        sqLiteQueryBuilder.setTables(tableName);
        Cursor cursor=sqLiteQueryBuilder.query(sqLiteDatabase,sqSelect,null,null,null,null,null);

        List<SeventhStore> result=new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                SeventhStore seventhStore=new SeventhStore();
                seventhStore.setId(cursor.getInt(cursor.getColumnIndex("id")));
                seventhStore.setName(cursor.getString(cursor.getColumnIndex("name")));
                seventhStore.setContent(cursor.getString(cursor.getColumnIndex("content")));
                result.add(seventhStore);
            }while (cursor.moveToNext());
        }
        return  result;
    }

    //====================== For Eight table data collection ==============================================

    public List<EighthStore> getTahajjodtarabi(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        SQLiteQueryBuilder sqLiteQueryBuilder=new SQLiteQueryBuilder();

        String [] sqSelect={"id","name","content"};
        String tableName="tahajjod";

        sqLiteQueryBuilder.setTables(tableName);
        Cursor cursor=sqLiteQueryBuilder.query(sqLiteDatabase,sqSelect,null,null,null,null,null);

        List<EighthStore> result=new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                EighthStore eighthStore=new EighthStore();
                eighthStore.setId(cursor.getInt(cursor.getColumnIndex("id")));
                eighthStore.setName(cursor.getString(cursor.getColumnIndex("name")));
                eighthStore.setContent(cursor.getString(cursor.getColumnIndex("content")));
                result.add(eighthStore);
            }while (cursor.moveToNext());
        }
        return  result;
    }


    //====================== For Ninth table data collection ==============================================

    public List<NinthStore> getJanajarInfo(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        SQLiteQueryBuilder sqLiteQueryBuilder=new SQLiteQueryBuilder();

        String [] sqSelect={"id","name","content"};
        String tableName="janajar_namaz";

        sqLiteQueryBuilder.setTables(tableName);
        Cursor cursor=sqLiteQueryBuilder.query(sqLiteDatabase,sqSelect,null,null,null,null,null);

        List<NinthStore> result=new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                NinthStore ninthStore=new NinthStore();
                ninthStore.setId(cursor.getInt(cursor.getColumnIndex("id")));
                ninthStore.setName(cursor.getString(cursor.getColumnIndex("name")));
                ninthStore.setContent(cursor.getString(cursor.getColumnIndex("content")));
                result.add(ninthStore);
            }while (cursor.moveToNext());
        }
        return  result;
    }


    // ========================== For Tenth table data collection ==============================================

    public List<TenthStore> getSurah(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        SQLiteQueryBuilder sqLiteQueryBuilder=new SQLiteQueryBuilder();
        String [] sqselect={"id","name","content"};
        String tableName="surah_list";
        sqLiteQueryBuilder.setTables(tableName);
        Cursor cursor=sqLiteQueryBuilder.query(sqLiteDatabase,sqselect,null,null,null,null,null);

        List<TenthStore> result=new ArrayList<>();

        if (cursor.moveToFirst()){
            do {
                TenthStore tenthStore=new TenthStore();
                tenthStore.setId(cursor.getInt(cursor.getColumnIndex("id")));
                tenthStore.setName(cursor.getString(cursor.getColumnIndex("name")));
                tenthStore.setContent(cursor.getString(cursor.getColumnIndex("content")));
                result.add(tenthStore);
            }while (cursor.moveToNext());
        }
        return result;
    }

    // ============================For Eleventh table data collection ====================================================

    public List<EleventhStore> getDoaActivity(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        SQLiteQueryBuilder sqLiteQueryBuilder=new SQLiteQueryBuilder();

        String [] sqSelect={"id","name","content"};
        String tableName="doa";

        sqLiteQueryBuilder.setTables(tableName);
        Cursor cursor=sqLiteQueryBuilder.query(sqLiteDatabase,sqSelect,null,null,null,null,null);

        List <EleventhStore> result=new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                EleventhStore eleventhStore=new EleventhStore();
                eleventhStore.setId(cursor.getInt(cursor.getColumnIndex("id")));
                eleventhStore.setName(cursor.getString(cursor.getColumnIndex("name")));
                eleventhStore.setContent(cursor.getString(cursor.getColumnIndex("content")));

                result.add(eleventhStore);
            }while(cursor.moveToNext());
        }
        return result;
    }

    // =============================For Twelve table data collection ===========================================================

    public List<TwelveStore> getChitrosohonamaz(){
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String [] sqSelect={"id","name"};
        String tableName="chitrosoho_namaz";

        queryBuilder.setTables(tableName);
        Cursor cursor=queryBuilder.query(sqLiteDatabase,sqSelect,null,null,null,null,null);

        List<TwelveStore> result=new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                TwelveStore twelveStore=new TwelveStore();
                twelveStore.setId(cursor.getInt(cursor.getColumnIndex("id")));
                twelveStore.setName(cursor.getString(cursor.getColumnIndex("name")));

                result.add(twelveStore);
            }while (cursor.moveToNext());

        }
        return result;
    }

    public ArrayList<NintyNimesModel> getAllNames() {
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<NintyNimesModel> nintyNimesModelArrayList = new ArrayList<>();
        cursor =
                db.rawQuery(
                        "SELECT * FROM nintyninenames",
                        null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            NintyNimesModel nintyNimesModel = new NintyNimesModel();
            nintyNimesModel.setId(cursor.getString(cursor.getColumnIndexOrThrow(ID)));
            nintyNimesModel.setAr_name(cursor.getString(cursor.getColumnIndexOrThrow(AR_NAME)));
            nintyNimesModel.setEn_name(cursor.getString(cursor.getColumnIndexOrThrow(EN_NAME)));
            nintyNimesModel.setBn_name(cursor.getString(cursor.getColumnIndexOrThrow(BN_NAME)));
            nintyNimesModel.setBn_meaning(cursor.getString(cursor.getColumnIndexOrThrow(BN_MEANING)));
            nintyNimesModel.setEn_meaning(cursor.getString(cursor.getColumnIndexOrThrow(EN_MEANING)));
            nintyNimesModel.setFazilat_bn(cursor.getString(cursor.getColumnIndexOrThrow(FAZILAT_BN)));
            nintyNimesModel.setFazilat_en(cursor.getString(cursor.getColumnIndexOrThrow(FAZILAT_EN)));
            nintyNimesModel.setReference(cursor.getString(cursor.getColumnIndexOrThrow(REFERENCES)));
            nintyNimesModelArrayList.add(nintyNimesModel);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return nintyNimesModelArrayList;
    }




}
