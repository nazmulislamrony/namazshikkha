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


//import com.downloader.Error;
//import com.downloader.OnCancelListener;
//import com.downloader.OnDownloadListener;
//import com.downloader.OnPauseListener;
//import com.downloader.OnProgressListener;
//import com.downloader.OnStartOrResumeListener;
//import com.downloader.PRDownloader;
//import com.downloader.PRDownloaderConfig;
//import com.downloader.Progress;

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


















//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case Helper.RequestPermissionCode:
//                if (grantResults.length> 0) {
//                    boolean StoragePermission = grantResults[0] ==
//                            PackageManager.PERMISSION_GRANTED;
//                    if (StoragePermission) {
//                        View toastView = getLayoutInflater().inflate(R.layout.toast_sample, null);
//                        Toast toast = new Toast(getApplicationContext());
//                        toast.setView(toastView);
//                        toast.setDuration(Toast.LENGTH_SHORT);
//                        toast.setGravity(Gravity.CENTER, 0,0);
//                        toast.show();
//                    } else {
//                        View toastView = getLayoutInflater().inflate(R.layout.toast_sample_denied, null);
//                        Toast toast = new Toast(getApplicationContext());
//                        toast.setView(toastView);
//                        toast.setDuration(Toast.LENGTH_SHORT);
//                        toast.setGravity(Gravity.CENTER, 0,0);
//                        toast.show();
//                    }
//                }
//                break;
//        }
//    }


}