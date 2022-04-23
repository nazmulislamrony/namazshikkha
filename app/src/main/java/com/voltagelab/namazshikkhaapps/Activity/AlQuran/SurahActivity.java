package com.voltagelab.namazshikkhaapps.Activity.AlQuran;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.downloader.Progress;

import com.voltagelab.namazshikkhaapps.Activity.AlQuran.adapter.SurahAdapter;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype.AlQuranActivity;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype.database.DatabaseAccess;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.intrface.OnItemClickListener;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.model.ModelSura;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.model.SurahModel;

import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.R;


import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SurahActivity extends AppCompatActivity {
    ArrayList<String> list;

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

        if (checkPermission()) {
            playMediaPlayer();

        } else  {
            requestPermission();
        }


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




        list = new ArrayList<>();
        list.add("https://www.everyayah.com/data/AbdulSamad_64kbps_QuranExplorer.Com/001005.mp3");
        list.add("https://www.everyayah.com/data/AbdulSamad_64kbps_QuranExplorer.Com/001006.mp3");



    }


    private void playMediaPlayer() {
        PRDownloader.initialize(getApplicationContext());

        // Enabling database for resume support even after the application is killed:
        PRDownloaderConfig config1 = PRDownloaderConfig.newBuilder()
                .setDatabaseEnabled(true)
                .build();
        PRDownloader.initialize(getApplicationContext(), config1);

// Setting timeout globally for the download network requests:
        PRDownloaderConfig config = PRDownloaderConfig.newBuilder()
                .setReadTimeout(30_000)
                .setConnectTimeout(30_000)
                .build();
        PRDownloader.initialize(getApplicationContext(), config);

        String DEFAULT_URL = "https://www.everyayah.com/data/AbdulSamad_64kbps_QuranExplorer.Com/001005.mp3";

        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String url = "https://www.everyayah.com/data/AbdulSamad_64kbps_QuranExplorer.Com/001005.mp3";
                String file = "/downloads/001005.mp3";




                Toast.makeText(SurahActivity.this, "ttt", Toast.LENGTH_SHORT).show();
                File f = new File(getExternalFilesDir(null).getAbsolutePath() + "/Quran memorization Test");
                if (!f.exists()) {
                    f.mkdir();
                }

            downloadMedia(list.get(0),f, file);

            }

        });
    }


    public  void downloadMedia(String url, File f, String fileName) {
        int downloadId = PRDownloader.download(url, f.getPath(), fileName)
                .build()
                .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                    @Override
                    public void onStartOrResume() {
                        Log.d("download_status","startorresume");
                    }
                })
                .setOnPauseListener(new OnPauseListener() {
                    @Override
                    public void onPause() {

                    }
                })
                .setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel() {

                    }
                })
                .setOnProgressListener(new OnProgressListener() {
                    @Override
                    public void onProgress(Progress progress) {

                        Log.d("download_status","progress: "+progress);
                    }
                })
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        Log.d("download_status","completed");
                        String file = "/downloads/001006.mp3";

                        downloadMedia(list.get(1),f, file);
                    }

                    @Override
                    public void onError(Error error) {

                    }
                });


    }












    public static final int RequestPermissionCode = 1;


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length> 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    if (StoragePermission) {
                        View toastView = getLayoutInflater().inflate(R.layout.toast_sample, null);
                        Toast toast = new Toast(getApplicationContext());
                        toast.setView(toastView);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0,0);
                        toast.show();
                    } else {
                        View toastView = getLayoutInflater().inflate(R.layout.toast_sample_denied, null);
                        Toast toast = new Toast(getApplicationContext());
                        toast.setView(toastView);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0,0);
                        toast.show();
                    }
                }
                break;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);

        return result == PackageManager.PERMISSION_GRANTED ;
    }

}