package com.voltagelab.namazshikkhaapps.Activity.AlQuran.model;

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

import com.voltagelab.namazshikkhaapps.Activity.AlQuran.Config;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.SurahDataSource;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.adapter.SurahAdapter;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahword.AyahWordActivity;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.intrface.OnItemClickListener;
import com.voltagelab.namazshikkhaapps.R;

import java.util.ArrayList;

public class SurahActivity extends AppCompatActivity {

    static String lang;
    private ArrayList<Surah> surahArrayList;
    private RecyclerView mRecyclerView;
    private SurahAdapter surahAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_surah);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        lang = sp.getString(Config.LANG, Config.defaultLang);
        mRecyclerView = findViewById(R.id.recycler_surah_view);


        surahArrayList = getSurahArrayList();
        surahAdapter = new SurahAdapter(surahArrayList, this);

        mRecyclerView.setAdapter(surahAdapter);

        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        surahAdapter.SetOnItemClickListener(
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Surah surah = (Surah) surahAdapter.getItem(position);

                        long surah_id = surah.getId();
                        long ayah_number = surah.getAyahNumber();
                        String surah_name = surah.getNameTranslate();

                        Log.d("SurahFragment", "ID: " + surah_id + " Surah Name: " + surah_name);

                        Bundle dataBundle = new Bundle();
                        dataBundle.putLong(SurahDataSource.SURAH_ID_TAG, surah_id);
                        dataBundle.putLong(SurahDataSource.SURAH_AYAH_NUMBER, ayah_number);
                        dataBundle.putString(SurahDataSource.SURAH_NAME_TRANSLATE, surah_name);

                        Intent intent = new Intent(SurahActivity.this, AyahWordActivity.class);
                        intent.putExtras(dataBundle);
                        startActivity(intent);
                    }
                });
    }
    private ArrayList<Surah> getSurahArrayList() {
        ArrayList<Surah> surahArrayList = new ArrayList<Surah>();
        SurahDataSource surahDataSource = new SurahDataSource(this);
        surahArrayList = surahDataSource.getBanglaSurahArrayList();
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

        return surahArrayList;
    }
}