package com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype;



import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
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
        folderCreate();
    }

    private void folderCreate() {
        mediaHelper = new MediaHelper(this);
        mediaHelper.audioFolderCreate(surahid);
        mediaHelper.createPlayList(Integer.parseInt(surahid),ayatDetails.size());
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




                Log.d("gettotal_ayah_surah","surah: "+ayatDetails.size()+", sid: "+surahid);



//                Toast.makeText(AlQuranActivity.this, "ttt", Toast.LENGTH_SHORT).show();
//                File f = new File(getExternalFilesDir(null).getAbsolutePath() + "/Quran memorization Test");
//                if (!f.exists()) {
//                    f.mkdir();
//                }

               downloadItemList = mediaHelper.getDownloadList();

                File downloadLocation = new File(mediaHelper.getPlayList().get(0));
                Log.d("get_al_playlist","playlist: "+downloadLocation.getName() +", path: "+downloadLocation.getPath()+", \n"+downloadLocation.getParent());


                list.add("https://www.everyayah.com/data/AbdulSamad_64kbps_QuranExplorer.Com/001005.mp3");
                list.add("https://www.everyayah.com/data/AbdulSamad_64kbps_QuranExplorer.Com/001006.mp3");

                dialogShow();

                downloadMedia(downloadItemList.get(0), new File(downloadLocation.getParent()), downloadLocation.getName());

            }

        });
    }

    int counter = 0;
    public  void downloadMedia(String url, File f, String fileName) {
        int totalfilesize = downloadItemList.size();
        Log.d("check_item","item: "+url +" , "+f.getPath()+", "+fileName);
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

                        double percentage = ((double) progress.currentBytes / (double) progress.totalBytes)*100;

                        downloadingseekbar.setProgress((int)percentage);
                        txtdownloadpercent.setText((int)percentage+"");
                        currentTotalVerse.setText(counter +"/"+totalfilesize);


                        Log.d("download_status","progress: "+ progress.totalBytes +", "+progress.currentBytes+", res: "+(int)percentage);
                    }
                })
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        Log.d("totalfilesize","total: "+totalfilesize+", counter: "+counter +", per: ");
                        if (counter< totalfilesize) {
                            File downloadLocation = new File(mediaHelper.getPlayList().get(counter));
                            downloadMedia(downloadItemList.get(counter), new File(downloadLocation.getParent()), downloadLocation.getName());
                            counter++;
                        }
                    }

                    @Override
                    public void onError(Error error) {

                    }
                });


    }

    SeekBar downloadingseekbar;
    TextView  currentTotalVerse;
    TextView txtdownloadpercent;
    private void dialogShow() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.download_dialog_info, null);
        dialogBuilder.setView(dialogView);

//        exit = dialogView.findViewById(R.id.btn_exit);
//        stop = dialogView.findViewById(R.id.btn_stop);
//        cancels = dialogView.findViewById(R.id.cancel_download_dialog);
//        txtNoInternetConnection = dialogView.findViewById(R.id.txt_no_internet_connection);
        txtdownloadpercent = dialogView.findViewById(R.id.txt_percentage_of_download);
//        preparingdownloading = dialogView.findViewById(R.id.txt_prepare_download);
        downloadingseekbar = dialogView.findViewById(R.id.seekbar_downloading);
        currentTotalVerse = dialogView.findViewById(R.id.txt_current_total_verse);
      Dialog dialog = dialogBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        downloadingseekbar.setProgress(0);
        downloadingseekbar.setMax(100);
        dialog.show();
    }
}