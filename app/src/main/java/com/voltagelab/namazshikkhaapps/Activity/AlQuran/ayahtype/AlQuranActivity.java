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
        mediaHelper.createPlayList(Integer.parseInt(surahid), ayatDetails.size());
    }

    private void initDownload() {
        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDownloadList();
                startDownloadService();
            }
        });
    }

    private void createDownloadList(){
        downloadItemList = mediaHelper.getDownloadList();
    }

    private void startDownloadService(){
        Intent intent = new Intent(this, DownloadService.class);
        intent.putStringArrayListExtra(DownloadHelper.URL, downloadItemList);
        startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(
                DownloadService.NOTIFICATION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                int currentDownload = bundle.getInt(DownloadHelper.CURRENTDOWNLOAD);
                Toast.makeText(context, currentDownload+"", Toast.LENGTH_SHORT).show();
                if (currentDownload==downloadItemList.size()) {
//                    Toast.makeText(MainActivity.this,
//                            "Download complete. Download URI: " + string,
//                            Toast.LENGTH_LONG).show();
//                    textView.setText("Download done");
                } else {
//                    Toast.makeText(MainActivity.this, "Download failed",
//                            Toast.LENGTH_LONG).show();
//                    textView.setText("Download failed");
                }
            }
        }
    };


}