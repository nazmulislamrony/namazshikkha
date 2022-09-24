package com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype;


import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
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
import com.voltagelab.namazshikkhaapps.helper.OnVersePlayUpdateListener;
import com.voltagelab.namazshikkhaapps.helper.ServiceMediaplayer;


import java.util.ArrayList;
import java.util.List;

public class AlQuranActivity extends AppCompatActivity {

    RecyclerView rvmain;
    public static String surahName;
    public static LinearLayoutManager layoutManager;
    TextView tooltext;
    ImageView play_btn, stop_btn;
    ArrayList<String> list;
    MediaHelper mediaHelper;
    ArrayList<AyatDetails> ayatDetails;
    String surahid;
    boolean isVerseClick = false;

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
        onVersePlayUpdateListener = new OnVersePlayUpdateListener() {
            @Override
            public void onUpdateVersePlay(int surahId, int position) {
                isVerseClick = true;
                if (surahId==1){
                    position = position+1;
                }
                playerRun(surahId, position);
            }
        };
        databaseGetData();
        if (helper.checkPermission(this)) {
            initDownload();
        } else {
            helper.requestPermission(this);
        }
        stopButton();
    }

    private void stopButton(){
        stop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serviceStop();
            }
        });
    }

    private void serviceStop() {
        tempVerseClick = false;
        Intent serviceIntent = new Intent(this, ServiceMediaplayer.class);
        stopService(serviceIntent);
        boolean isService = isMyServiceRunning(ServiceMediaplayer.class);
        if (mService != null && isService) {
            // Detach the service connection.
            unbindService(connection);
            LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        }
        play_btn.setBackgroundResource(R.drawable.ic_baseline_play_circle_filled_24);
    }

    ServiceMediaplayer mService;
    boolean mBound = false;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            ServiceMediaplayer.LocalBinder binder = (ServiceMediaplayer.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    boolean isplaying;
    boolean isBroadcastRunning;
    static int alldur;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalduration = intent.getIntExtra("totalduration", -1);
            int onStop = intent.getIntExtra("onstop", -1); // oncompletion
            isplaying = intent.getBooleanExtra("playstate", false);
            int currentplayingindex = intent.getIntExtra("currentplayingindex", -1);
            isBroadcastRunning = intent.getBooleanExtra("broadcastrunning", false);
            boolean isPerIndexCompletion = intent.getBooleanExtra("perindexcompletion", false);
            if (isPerIndexCompletion) {
                play_btn.setClickable(false);
            } else {
                play_btn.setClickable(true);
            }
            alldur += totalduration;
            if (isplaying) {
                play_btn.setBackgroundResource(R.drawable.ic_baseline_play_circle_filled_24);
                LocalBroadcastManager.getInstance(AlQuranActivity.this).unregisterReceiver(broadcastReceiver);
            } else {
                play_btn.setBackgroundResource(R.drawable.ic_baseline_pause_circle_filled_24);
            }
            if (surahid.equals("9") && tempVerseClick) {
                currentplayingindex =  currentplayingindex - 1;
            } else if (surahid.equals("1") && tempVerseClick){
                currentplayingindex = currentplayingindex - 1;
            }
            Log.d("currrrrrrr","ind: "+currentplayingindex);
            adapter.currentPlayingIndex(currentplayingindex);
            if (onStop == 1) onCompletionFinish();
        }
    };

    private void servicePlayActive() {
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(broadcastReceiver,
                        new IntentFilter(ServiceMediaplayer.MY_SERVICE_MESSAGE));
        serviceStart();
    }

    private void onCompletionFinish() {
        play_btn.setBackgroundResource(R.drawable.ic_baseline_play_circle_filled_24);
        adapter.isPlayingMedia(false, false);
        serviceStop();
    }




    VerseAdapter adapter;
    OnVersePlayUpdateListener onVersePlayUpdateListener;
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
        adapter = new VerseAdapter(this, ayatDetails, Integer.parseInt(surahid), onVersePlayUpdateListener);
        rvmain.setAdapter(adapter);
        rvmain.setLayoutManager(layoutManager);
        folderCreate();
    }

    private void folderCreate() {
        mediaHelper = new MediaHelper(this);
        mediaHelper.createPlayList();
    }

    boolean tempVerseClick;
    private void playerRun(int surahIds, int verseFrom) {

        OnPlayList onPlayList = new OnPlayList() {
            @Override
            public void onPlayList(ArrayList<String> playList) {
                tempVerseClick = true;
                playLists = playList;
                adapter.isPlayingMedia(true, false);
                servicePlayActive();
            }
        };
        mediaHelper = new MediaHelper(this);
        mediaHelper.onVerseClick(surahName, surahIds,verseFrom, onPlayList);
    }




    ArrayList<String> playLists;
    private void initDownload() {
    OnPlayList onPlayList = new OnPlayList() {
            @Override
            public void onPlayList(ArrayList<String> playList) {
                playLists = playList;
                servicePlayActive();
            }
        };
        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int verseFrom = 0;
                int totalAyat = 0;
                if (surahid.equals("9") || surahid.equals("1")){
                    verseFrom = 1;
                    totalAyat = 1 + ayatDetails.size();
                } else {
                    verseFrom = 0;
                    totalAyat = ayatDetails.size();
                }
                mediaHelper.downloadOrPlay(Integer.parseInt(surahid), onPlayList, surahName, verseFrom, totalAyat);
            }
        });
    }

    private void serviceStart() {
        Intent serviceIntent = new Intent(AlQuranActivity.this, ServiceMediaplayer.class);
        serviceIntent.setAction(Helper.MUSIC_SERVICE_ACTION_START);
        Log.d("playlist_check","list: "+playLists.get(0));
        serviceIntent.putStringArrayListExtra("playlist", playLists);
        ContextCompat.startForegroundService(AlQuranActivity.this, serviceIntent);
        bindService(new Intent(this,
                ServiceMediaplayer.class), connection, this.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaHelper.onPause();
    }
}