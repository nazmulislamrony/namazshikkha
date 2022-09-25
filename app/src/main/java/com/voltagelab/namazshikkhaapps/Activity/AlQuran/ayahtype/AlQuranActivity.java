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
import com.voltagelab.namazshikkhaapps.helper.NewMediaHelper;
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
    ImageView btn_play_player, btn_stop_player;
    ArrayList<String> list;
    NewMediaHelper mediaHelper;
    ArrayList<AyatDetails> ayatDetails;
    String surahid;
    boolean isVerseClick = false;
    OnPlayList onPlayListListener;
    List<String> playListStrings;
    public static int plyclck = 0;
    boolean tempVerseClick;
    public static boolean isbuttonState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_al_quran);
        Helper helper = new Helper(this);
        helper.backButtonPressed(this);
        tooltext = findViewById(R.id.tooltext2);
        btn_play_player = findViewById(R.id.play_btn);
        btn_stop_player = findViewById(R.id.stop_btn);
        rvmain = findViewById(R.id.rvmain);
        layoutManager = new LinearLayoutManager(this);
        playListStrings = new ArrayList<>();

        if (helper.checkPermission(this)) {
            permissionForPlay();
        } else {
            helper.requestPermission(this);
        }
        mediaHelper = new NewMediaHelper(this, onPlayListListener);
        databaseGetData();
    }

    private void permissionForPlay () {
        onVersePlayUpdateListener = new OnVersePlayUpdateListener() {
            @Override
            public void onUpdateVersePlay(int surahId, int position) {
                isVerseClick = true;
                playerRun(surahId, position);
            }
        };
//        onVerseLoopListener = new OnVerseLoopListener() {
//            @Override
//            public void onVerseLoop(int surahId, int position) {
//                adapter_position = position;
//                surah_number = surahId;
//                isLooping = true;
//                playerRun(surahId, position);
//            }
//        };

        btn_play_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLooping = false;
                isVerseClick = false;
                Log.d("get_su","tt: "+surahid);
                playerRun(Integer.parseInt(surahid), 1);
            }
        });

        btn_stop_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isplaying = true;
                isButtonStop();
                serviceStop();
            }
        });
    }

    private void isButtonStop() {
        Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();
        isLooping = false;
        isbuttonState = false;
//        layout_advance_player.setVisibility(View.VISIBLE);
        enableButtonState();
        playListStrings.clear();
        adapter.isPlayingMedia(false, false);
    }


    public static boolean isLooping = false;
    private void playerRun(int surahIds, int verseFrom) {
        if (isVerseClick) {
            isLooping = false;
            onPlayListListener = new OnPlayList() {
                @Override
                public void onPlayList(ArrayList<String> playList) {
                    tempVerseClick = true;
                    adapter.isPlayingMedia(true, false);
                    playListStrings = playList;
                    servicePlayActive();
                }
            };
            mediaHelper = new NewMediaHelper(this, onPlayListListener);
            mediaHelper.createVersePlayList(surahIds, verseFrom, surahName);
            isVerseClick = false;
        } else if (isLooping) {
//            onPlayListListener = new OnPlayListListener() {
//                @Override
//                public void playList(List<String> playList) {
//                    tempVerseClick = true;
//                    adapter.isPlayingMedia(false, true);
//                    playListStrings = playList;
//                    servicePlayActive();
//                }
//            };
//            mediaHelper = new NewMediaHelper(this, onPlayListListener);
//            mediaHelper.createVersePlayList(surahIds, verseFrom);
        } else {
            onPlayListListener = new OnPlayList() {
                @Override
                public void onPlayList(ArrayList<String> playList) {
                    if (playListStrings.isEmpty()) {
                        playListStrings = playList;
                        servicePlayActive();
                        plyclck = 1;
                        adapter.isPlayingMedia(true, false);
                    }
                }
            };

            if (playListStrings.isEmpty()) {
                mediaHelper = new NewMediaHelper(this, onPlayListListener);
                mediaHelper.createPlayList(surahIds, surahName);
            } else {
                servicePlayActive();
                plyclck = 1;
                adapter.isPlayingMedia(true, false);
            }
        }
    }

    private void enableButtonState() {
//        img_range_repeat.setClickable(true);
//        btn_verse_repeat.setClickable(true);
//        img_delay.setClickable(true);
//        btn_quiz_mode.setClickable(true);
        btn_play_player.setClickable(true);
        adapter.isPlayingMedia(true, false);
        tempVerseClick = false;
    }

    private void disableButtonState() {
//        img_range_repeat.setClickable(false);
//        btn_verse_repeat.setClickable(false);
//        img_delay.setClickable(false);
    }



    private void serviceStart() {
        ArrayList<String> playlist = new ArrayList<>(playListStrings);
        Intent serviceIntent = new Intent(this, ServiceMediaplayer.class);
        serviceIntent.putExtra("inputExtra", "Play From Surah");
        serviceIntent.putStringArrayListExtra("playlist", playlist);
//        serviceIntent.putExtra("verse_repeat", verse_repeat_count);
//        serviceIntent.putExtra("range_repeat", range_repeat_count);
//        serviceIntent.putExtra("playback_speed", speed);
        serviceIntent.putExtra("islooping", isLooping);
//        serviceIntent.putExtra("delay", delay_count);
        serviceIntent.setAction(Helper.MUSIC_SERVICE_ACTION_START);
        ContextCompat.startForegroundService(this, serviceIntent);
        bindService(new Intent(this,
                ServiceMediaplayer.class), connection, this.BIND_AUTO_CREATE);
    }

    private void serviceStop() {
        Intent serviceIntent = new Intent(this, ServiceMediaplayer.class);
        stopService(serviceIntent);
        boolean isService = isMyServiceRunning(ServiceMediaplayer.class);
        if (mService != null && isService) {
            // Detach the service connection.
            unbindService(connection);
            LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        }
        btn_play_player.setBackgroundResource(R.drawable.ic_baseline_play_circle_filled_24);
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    ServiceMediaplayer mService;
    boolean mBound = false;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            ServiceMediaplayer.LocalBinder binder = (ServiceMediaplayer.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    boolean isplaying;
    boolean isBroadcastRunning;
    static int alldur;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int onStartMediaDuration = intent.getIntExtra("mediaduration", -1);
            int currentplayingindex = intent.getIntExtra("currentplayingindex", -1);
            int mediarepeatcount = intent.getIntExtra("mediarepeat", -1);
            int playlistrepeatcount = intent.getIntExtra("playlistrepeatcount", -1);
            int totalduration = intent.getIntExtra("totalduration", -1);
            int delays = intent.getIntExtra("delay", -1);
            int onStop = intent.getIntExtra("onstop", -1); // oncompletion
            int progress = intent.getIntExtra("progresstime", -1);
            int totaltime = intent.getIntExtra("time", -1);
            isplaying = intent.getBooleanExtra("playstate", false);
            boolean isloopfromservice = intent.getBooleanExtra("isloop", false);
            isBroadcastRunning = intent.getBooleanExtra("broadcastrunning", false);
            boolean isPerIndexCompletion = intent.getBooleanExtra("perindexcompletion", false);
            if (isPerIndexCompletion) {
                btn_play_player.setClickable(false);
            } else {
                btn_play_player.setClickable(true);
            }
            alldur += totalduration;
//            String alltime = mediaHelper.getTime(totaltime);
//            playingstate.setText("CP: " + currentplayingindex + "\nVR: " + mediarepeatcount + "\nRR: " + playlistrepeatcount + "\nTime: " + alltime);
//            String timedur = mediaHelper.getTime(totalduration);
//            txt_end_time.setText(timedur);
            if (isplaying) {
                enableButtonState();
                btn_play_player.setBackgroundResource(R.drawable.ic_baseline_play_circle_filled_24);
                LocalBroadcastManager.getInstance(AlQuranActivity.this).unregisterReceiver(broadcastReceiver);
            } else {
                disableButtonState();
                isbuttonState = true;
                btn_play_player.setBackgroundResource(R.drawable.ic_baseline_pause_circle_filled_24);
            }
            if (surahid.equals("1") && tempVerseClick || surahid.equals("9") && tempVerseClick) {
                currentplayingindex = (surahid.equals("1") || surahid.equals("9")) ? currentplayingindex - 1 : currentplayingindex;
            }
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
        enableButtonState();
        isbuttonState = false;
        btn_play_player.setBackgroundResource(R.drawable.ic_baseline_play_circle_filled_24);
        adapter.isPlayingMedia(false, false);
        playListStrings.clear();
        serviceStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
    }


}