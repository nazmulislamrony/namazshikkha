package com.voltagelab.namazshikkhaapps.Activity.AlQuran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.voltagelab.namazshikkhaapps.Activity.AlQuran.adapter.SurahAdapter;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype.AlQuranActivity;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype.database.DatabaseAccess;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.intrface.OnItemClickListener;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.model.ModelSura;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.model.SurahModel;
import com.voltagelab.namazshikkhaapps.Activity.downloadhelper.DownloadService;
import com.voltagelab.namazshikkhaapps.Activity.downloadhelper.callback.DownloadListener;
import com.voltagelab.namazshikkhaapps.Activity.downloadhelper.callback.DownloadManager;
import com.voltagelab.namazshikkhaapps.Activity.downloadhelper.domain.DownloadInfo;
import com.voltagelab.namazshikkhaapps.Activity.downloadhelper.exception.DownloadException;
import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;



public class SurahActivity extends AppCompatActivity {

    static String lang;
    private ArrayList<SurahModel> surahModelArrayList;
    private RecyclerView mRecyclerView;
    private SurahAdapter surahAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<ModelSura> suraList;
    ImageView play_btn, stop_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_surah);
        Helper helper = new Helper(this);
        helper.backButtonPressed(this);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        lang = sp.getString(Config.LANG, Config.defaultLang);
        mRecyclerView = findViewById(R.id.recycler_surah_view);
        play_btn = findViewById(R.id.play_btn);
        stop_btn = findViewById(R.id.stop_btn);


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





        playMediaPlayer();
    }

    DownloadInfo downloadInfo;

    private void playMediaPlayer() {

        String DEFAULT_URL = "https://3b8637d9f6c334dab555e2afbdc16687.dlied1.cdntips.net/imtt.dd.qq.com/16891/apk/49F7A4E3B47E5828D02B2A10C580DB65.apk";


       DownloadManager downloadManager = DownloadService.getDownloadManager(getApplicationContext());
        downloadInfo = downloadManager.getDownloadById(DEFAULT_URL);

        if (downloadInfo != null) {
            setDownloadListener();
        }

        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> list = new ArrayList<>();
                list.add("https://www.everyayah.com/data/AbdulSamad_64kbps_QuranExplorer.Com/001005.mp3");


            }
        });
    }


    private void setDownloadListener() {
        downloadInfo.setDownloadListener(new DownloadListener() {

            @Override
            public void onStart() {
            }

            @Override
            public void onWaited() {

            }

            @Override
            public void onPaused() {

            }

            @Override
            public void onDownloading(long progress, long size) {

            }

            @Override
            public void onRemoved() {

                downloadInfo = null;
            }

            @Override
            public void onDownloadSuccess() {

            }

            @Override
            public void onDownloadFailed(DownloadException e) {

            }
        });
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