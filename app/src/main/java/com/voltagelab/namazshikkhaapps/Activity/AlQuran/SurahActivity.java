package com.voltagelab.namazshikkhaapps.Activity.AlQuran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.voltagelab.namazshikkhaapps.Activity.AlQuran.Config;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.SurahDataSource;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.adapter.SurahAdapter;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype.AlQuranActivity;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype.database.DatabaseAccess;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.intrface.OnItemClickListener;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.model.ModelSura;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.model.SurahModel;
import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.R;

import java.util.ArrayList;

public class SurahActivity extends AppCompatActivity {

    static String lang;
    private ArrayList<SurahModel> surahModelArrayList;
    private RecyclerView mRecyclerView;
    private SurahAdapter surahAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<ModelSura> suraList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_surah);
        Helper helper = new Helper(this);
        helper.backButtonPressed(this);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        lang = sp.getString(Config.LANG, Config.defaultLang);
        mRecyclerView = findViewById(R.id.recycler_surah_view);


//        surahModelArrayList = getSurahArrayList();
//        surahAdapter = new SurahAdapter(surahModelArrayList, this);
//
//        mRecyclerView.setAdapter(surahAdapter);
//
//        mRecyclerView.setHasFixedSize(true);
//        // use a linear layout manager
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

//        surahAdapter.SetOnItemClickListener(
//                new OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View v, int position) {
//                        SurahModel surahModel = (SurahModel) surahAdapter.getItem(position);
//
//                        long surah_id = surahModel.getId();
//                        long ayah_number = surahModel.getAyahNumber();
//                        String surah_name = surahModel.getNameTranslate();
//                        String surah_name_arabic = surahModel.getNameArabic();
//
//                        Log.d("SurahFragment", "ID: " + surah_id + " Surah Name: " + surah_name_arabic);
//
//                        Bundle dataBundle = new Bundle();
//                        dataBundle.putLong(SurahDataSource.SURAH_ID_TAG, surah_id);
//                        dataBundle.putLong(SurahDataSource.SURAH_AYAH_NUMBER, ayah_number);
//                        dataBundle.putString(SurahDataSource.SURAH_NAME_TRANSLATE, surah_name);
//                        dataBundle.putString(SurahDataSource.SURAH_NAME_ARABIC, surah_name_arabic);
//
//                        Intent intent = new Intent(SurahActivity.this, AlQuranActivity.class);
////                        Intent intent = new Intent(SurahActivity.this, AyahWordActivity.class);
//                        intent.putExtras(dataBundle);
//                        startActivity(intent);
//                    }
//                });





        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        suraList = databaseAccess.getAllSurahList();

        surahAdapter = new SurahAdapter(suraList, this);

        mRecyclerView.setAdapter(surahAdapter);

        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }
    private ArrayList<SurahModel> getSurahArrayList() {
        ArrayList<SurahModel> surahModelArrayList = new ArrayList<SurahModel>();
        SurahDataSource surahDataSource = new SurahDataSource(this);
        surahModelArrayList = surahDataSource.getBanglaSurahArrayList();
//        switch (lang) {
//            case Config.LANG_BN:
//                surahArrayList = surahDataSource.getBanglaSurahArrayList();
//                break;
//            case Config.LANG_INDO:
//                surahArrayList = surahDataSource.getIndonesianSurahArrayList();
//                break;
//            case Config.LANG_EN:
//                surahArrayList = surahDataSource.getEnglishSurahArrayList();
//                break;
//        }

        return surahModelArrayList;
    }
}