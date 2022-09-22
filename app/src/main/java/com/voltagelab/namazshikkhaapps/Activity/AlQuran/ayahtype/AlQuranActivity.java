package com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.downloader.Error;
//import com.downloader.OnCancelListener;
//import com.downloader.OnDownloadListener;
//import com.downloader.OnPauseListener;
//import com.downloader.OnProgressListener;
//import com.downloader.OnStartOrResumeListener;
//import com.downloader.PRDownloader;
//import com.downloader.PRDownloaderConfig;
//import com.downloader.Progress;

import com.voltagelab.namazshikkhaapps.Activity.AlQuran.SurahDataSource;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype.database.DatabaseAccess;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.downloader.DownloadHelper;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.downloader.DownloadService;
import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.R;
import com.voltagelab.namazshikkhaapps.helper.MediaHelper;
import com.voltagelab.namazshikkhaapps.helper.OnPlayList;


import java.util.ArrayList;

public class AlQuranActivity extends AppCompatActivity {

    RecyclerView rvmain;
    public static String surahName;
    public LinearLayoutManager layoutManager;
    TextView tooltext;
    ImageView play_btn, stop_btn;
    ArrayList<String> list;
    MediaHelper mediaHelper;
    ArrayList<AyatDetails> ayatDetails;
    String surahid;
    ArrayList<String> downloadItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_al_quran);
        Helper helper = new Helper(this);
        helper.backButtonPressed(this);
        tooltext = findViewById(R.id.tooltext2);
        play_btn = findViewById(R.id.play_btn);
        stop_btn = findViewById(R.id.stop_btn);
        rvmain = findViewById(R.id.rvmain);
        layoutManager = new LinearLayoutManager(this);
        databaseGetData();
        if (helper.checkPermission(this)) {
            initDownload();
        } else {
            helper.requestPermission(this);
        }
    }



    private void databaseGetData() {
        Bundle bundle = this.getIntent().getExtras();
        surahName = bundle.getString(SurahDataSource.SURAH_NAME_TRANSLATE);
        String surahNameArabic = bundle.getString(SurahDataSource.SURAH_NAME_ARABIC);
        surahid = bundle.getString(SurahDataSource.SURAH_ID_TAG);
        tooltext.setText(surahName);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        ayatDetails = new ArrayList<>();
        ayatDetails = databaseAccess.getAyahDetails(Integer.parseInt(surahid));
        VerseAdapter adapter = new VerseAdapter(this, ayatDetails, Integer.parseInt(surahid));
        rvmain.setAdapter(adapter);
        rvmain.setLayoutManager(layoutManager);
        folderCreate();
    }

    private void folderCreate() {
        mediaHelper = new MediaHelper(this);
        int verseFrom = 0;
        int totalAyat = 0;
        if (surahid.equals("9") || surahid.equals("1")){
            verseFrom = 1;
            totalAyat = 1 + ayatDetails.size();
        } else {
            verseFrom = 0;
            totalAyat = ayatDetails.size();
        }
        mediaHelper.createPlayList(Integer.parseInt(surahid),verseFrom, totalAyat);
    }

    private void initDownload() {

    OnPlayList onPlayList = new OnPlayList() {
            @Override
            public void onPlayList(ArrayList<String> playList) {
                Log.d("onfinishdownload","playcall");

            }
        };
        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaHelper.downloadOrPlay(onPlayList);
            }
        });


    }

    private void createDownloadList(){
//        downloadItemList = mediaHelper.getDownloadList();
    }



    @Override
    protected void onResume() {
        super.onResume();
        mediaHelper.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaHelper.onPause();
    }




}