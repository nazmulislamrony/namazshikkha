package com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype;



import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.downloader.Progress;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.SurahDataSource;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype.database.DatabaseAccess;
import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.R;
import com.voltagelab.namazshikkhaapps.helper.MediaHelper;


import java.io.File;
import java.util.ArrayList;

public class AlQuranActivity extends AppCompatActivity {

    RecyclerView rvmain;
    public static String surahName;
    public LinearLayoutManager layoutManager;
    TextView tooltext;
    ImageView play_btn, stop_btn;
   ArrayList <String> list;
   MediaHelper mediaHelper;
    ArrayList<AyatDetails> ayatDetails;
    String surahid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_al_quran);
        Helper helper = new Helper(this);
        helper.backButtonPressed(this);
        mediaHelper = new MediaHelper(this);
        mediaHelper.audioFolderCreate();

        tooltext = findViewById(R.id.tooltext2);
        play_btn = findViewById(R.id.play_btn);
        stop_btn = findViewById(R.id.stop_btn);
        rvmain =  findViewById(R.id.rvmain);
        layoutManager = new LinearLayoutManager(this);
        if (helper.checkPermission(this)) {
            playMediaPlayer();
        } else  {
            helper.requestPermission(this);
        }
        databaseGetData();

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
        ayatDetails = databaseAccess.getAyahDetails(Integer.parseInt( surahid));
        VerseAdapter adapter = new VerseAdapter(this, ayatDetails, Integer.parseInt(surahid));
        rvmain.setAdapter(adapter);
        rvmain.setLayoutManager(layoutManager);
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

                ArrayList<String> list = new ArrayList<>();

                String url = "https://www.everyayah.com/data/AbdulSamad_64kbps_QuranExplorer.Com/001005.mp3";
                String file = "/downloads/001005.mp3";


                Log.d("gettotal_ayah_surah","surah: "+ayatDetails.size()+", sid: "+surahid);
                mediaHelper.createPlayList(Integer.parseInt(surahid),ayatDetails.size());


//                Toast.makeText(AlQuranActivity.this, "ttt", Toast.LENGTH_SHORT).show();
//                File f = new File(getExternalFilesDir(null).getAbsolutePath() + "/Quran memorization Test");
//                if (!f.exists()) {
//                    f.mkdir();
//                }


                list.add("https://www.everyayah.com/data/AbdulSamad_64kbps_QuranExplorer.Com/001005.mp3");
                list.add("https://www.everyayah.com/data/AbdulSamad_64kbps_QuranExplorer.Com/001006.mp3");

//                downloadMedia(list.get(0),f, file);

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
}