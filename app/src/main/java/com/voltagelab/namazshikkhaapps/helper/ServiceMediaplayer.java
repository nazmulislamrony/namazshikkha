package com.voltagelab.namazshikkhaapps.helper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.R;

import java.util.ArrayList;
import java.util.Random;


public class ServiceMediaplayer extends Service {
    public static final String MY_SERVICE_MESSAGE = "mpservicemessage";
    public static final String ON_PROGRESS = "mpservicemessage";
    public MasterPlayer player;
    ArrayList<String> playListStrings;
    String TAG = "example_service";
    int verse_repeat, range_repeat, delay;
    public static boolean isPlaying = true;
    private final IBinder binder = new LocalBinder();
    private final Random mGenerator = new Random();


    public static final String CHANNEL_ID = "new_foregroundServiceChannel";


    public class LocalBinder extends Binder {
        public ServiceMediaplayer getService() {
            // Return this instance of LocalService so clients can call public methods
            return ServiceMediaplayer.this;
        }
    }

    Intent broadcastmsgInt;
    int totaldur, mediarepeat, playlistrepeat, delays, mediadur;
    int time;
    Intent onprogressint;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate() {
        super.onCreate();
        broadcastmsgInt = new Intent(MY_SERVICE_MESSAGE);
        onprogressint = new Intent(ON_PROGRESS);
        playListStrings = new ArrayList<>();

        Log.d("currentpslayisandex", "curresnt: " + player);


        player = new MasterPlayer(new PlayerControlInterface() {
            @Override
            public void onStartMedia(int mediaDuration) {
                mediadur = mediaDuration;
            }

            @Override
            public void currentPlayingIndex(int currentPlayIndex, int currentSurahId, int currentVerseId) {

                broadcastmsgInt.putExtra("currentplayingindex", currentPlayIndex);
                broadcastmsgInt.putExtra("currentSurahId", currentSurahId);
                broadcastmsgInt.putExtra("currentVerseId", currentVerseId);
                Log.d("onprogressdat", "CP: " + currentPlayIndex + " CS: " + currentSurahId + ", CV: " + currentVerseId);
            }

            @Override
            public void onProgress(String mediaName, int progress, int totalDuration, int mediaRepeatCount, int playlistRepeatCount, int delay) {
                totaldur = totalDuration;
                mediarepeat = mediaRepeatCount;
                playlistrepeat = playlistRepeatCount;
                delays = delay;

                time += totalDuration;
                Log.d("progress_update", "progress: " + progress);

//                broadcastmsgInt.putExtra("progresstime",progress);
                broadcastmsgInt.putExtra("mediarepeat", mediarepeat);
                broadcastmsgInt.putExtra("playlistrepeatcount", playlistrepeat);
                broadcastmsgInt.putExtra("totalduration", totaldur);
                broadcastmsgInt.putExtra("delay", delays);
                broadcastmsgInt.putExtra("mediaduration", mediadur);
                broadcastmsgInt.putExtra("onstop", -1); // when playing state -1
                broadcastmsgInt.putExtra("time", time);
                broadcastmsgInt.putExtra("broadcastrunning", true);


                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcastSync(broadcastmsgInt);
            }

            @Override
            public void onStop(int firstsurah, int firstayah) {
                stopSelf();
                stopForeground(true);
                broadcastmsgInt.putExtra("onstop", 1);
//                broadcastmsgInt.putExtra("playstate", !isPlaying);
                broadcastmsgInt.putExtra("firstsurah", firstsurah);
                broadcastmsgInt.putExtra("firstayah", firstayah);
                Log.d("check_first_surah_verse", "fs: " + firstsurah + ", " + firstayah);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcastSync(broadcastmsgInt);
//                sendBroadcast(broadcastmsgInt);

                time = 0;

            }

            @Override
            public void onCompletionPerIndex(boolean isCompletion) {
                Log.d("sssabsesfgggg", "onsttoppp " + isCompletion);
                broadcastmsgInt.putExtra("perindexcompletion", isCompletion);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcastSync(broadcastmsgInt);
            }
        }, this);


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("playliststring", "out playlist: " + intent.getAction());

        switch (intent.getAction()) {

            case Helper.MUSIC_SERVICE_ACTION_PLAY: {

                boolean checkstate = player.playerStatus == PlayerState.PLAYING;
                Log.d(TAG, "onStartCommand: play called" + checkstate);
                broadcastmsgInt.putExtra("playstate", checkstate);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcastSync(broadcastmsgInt);


                if (!checkstate) {
                    player.play();

                    contentView.setImageViewResource(R.id.play_btn, R.drawable.ic_baseline_play_circle_filled_24);
                    notificationManager.notify(NotificationID, notification);

                    broadcastmsgInt.putExtra("playstate", false);
                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcastSync(broadcastmsgInt);

                    Log.d("getplafjsldjf", "1");

                } else {
                    player.pause();
                    contentView.setImageViewResource(R.id.play_btn, R.drawable.ic_baseline_play_circle_filled_24);
//                    notification.contentView = notificationView;
                    notificationManager.notify(NotificationID, notification);

                    broadcastmsgInt.putExtra("playstate", false);
                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcastSync(broadcastmsgInt);

                    Log.d("getplafjsldjf", "2");

                }
                break;
            }
            case Helper.MUSIC_SERVICE_ACTION_PAUSE: {
                Log.d(TAG, "onStartCommand: pause called" + isPlaying);
                player.pause();
                boolean checkstate = player.playerStatus == PlayerState.PLAYING;
                broadcastmsgInt.putExtra("playstate", checkstate);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcastSync(broadcastmsgInt);
                Log.d("isplayingcheck", "pus: " + isPlaying);

                break;
            }
            case Helper.MUSIC_SERVICE_ACTION_STOP: {
                Log.d(TAG, "onStartCommand: stop called");

                broadcastmsgInt.putExtra("onstop", 1);
                broadcastmsgInt.putExtra("playstate", !isPlaying);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcastSync(broadcastmsgInt);
                time = 0;
                player.stop();
                stopForeground(true);
                stopSelfResult(startId);
                break;
            }
            case Helper.MUSIC_SERVICE_ACTION_START: {
                createNotificationChannel();
                Log.d(TAG, "onStartCommand: start called");

                playListStrings = intent.getStringArrayListExtra("playlist");
                verse_repeat = intent.getIntExtra("verse_repeat", 1);
                range_repeat = intent.getIntExtra("range_repeat", 1);
                delay = intent.getIntExtra("delay", 0);
                float playback_speed = intent.getFloatExtra("playback_speed", 1f);
                boolean isLooping = intent.getBooleanExtra("islooping", false);
                boolean isVerseclick = intent.getBooleanExtra("isverseclick", false);
//        Log.d(TAG, "onStartCommand: playlist: " + verse_repeat + ", rangerepeat: " + range_repeat);
                player.setDelay(delay);
                player.setPlayList(playListStrings);
                player.setMediaRepeat(verse_repeat);
                player.setPlayListRepeat(range_repeat);
                player.setPlaybackspeed(playback_speed);

                isPlaying = player.playerStatus == PlayerState.PLAYING;

                boolean isTempState = isLooping;

                broadcastmsgInt.putExtra("playstate", isPlaying);
                broadcastmsgInt.putExtra("isloop", isLooping);
                broadcastmsgInt.putExtra("isloop", isLooping);
                broadcastmsgInt.putExtra("isverseclick", isVerseclick);

                Log.d("check_is_looping", "loop: " + isVerseclick);

                if (isLooping && !isPlaying) {
                    player.play();
                    player.setLooping(true);
                } else if (isLooping && isPlaying) {
                    player.setLooping(false);
                    broadcastmsgInt.putExtra("playstate", false);
                } else if (!isPlaying) {
                    player.play();
                } else if (isPlaying) {
                    player.pause();
                }


                Log.d("isplayingcheck", "out: " + isPlaying);

                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcastSync(broadcastmsgInt);
                showNotification();
                break;
            }
            default: {

            }
        }


        return START_NOT_STICKY;


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        player.onDestroy();
        stopSelf();
        Log.d("ondestroyservice", "onStartCommand: stop: ");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Example Service Channel",
                    NotificationManager.IMPORTANCE_LOW
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }


    private static Notification notification;
    private static NotificationManager notificationManager;
    private static final int NotificationID = 1005;
    private static NotificationCompat.Builder mBuilder;
    private static RemoteViews contentView;

    private void showNotification() {

//        createNotificationChannel();

        notificationManager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        contentView = new RemoteViews(getPackageName(), R.layout.custom_notification_small);
        contentView.setImageViewResource(R.id.image, R.mipmap.ic_launcher);

        //Intent for play button
        Intent pIntent = new Intent(this, ServiceMediaplayer.class);
        pIntent.setAction(Helper.MUSIC_SERVICE_ACTION_PLAY);
//        PendingIntent playIntent = PendingIntent.getService(this, 1, pIntent, 0);
        PendingIntent playIntent;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            playIntent = PendingIntent.getActivity(
                    this,
                    1, pIntent,
                    PendingIntent.FLAG_IMMUTABLE);
        } else {
            playIntent = PendingIntent.getActivity(
                    this,
                    1, pIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
        }


        contentView.setOnClickPendingIntent(R.id.notif_play, playIntent);
        //Intent for stop button
        Intent sIntent = new Intent(this, ServiceMediaplayer.class);
        sIntent.setAction(Helper.MUSIC_SERVICE_ACTION_STOP);
//        = PendingIntent.getService(this, 1, sIntent, 0);
        PendingIntent stopIntent ;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            stopIntent = PendingIntent.getActivity(
                    this,
                    1, pIntent,
                    PendingIntent.FLAG_IMMUTABLE);
        } else {
            stopIntent = PendingIntent.getActivity(
                    this,
                    1, pIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
        }
        contentView.setOnClickPendingIntent(R.id.notify_stop, stopIntent);


        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        mBuilder.setAutoCancel(false);
        mBuilder.setOngoing(true);
        mBuilder.setPriority(Notification.PRIORITY_HIGH);
        mBuilder.setOnlyAlertOnce(true);
        mBuilder.build().flags = Notification.FLAG_NO_CLEAR | Notification.PRIORITY_LOW;
        mBuilder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        mBuilder.setContent(contentView);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = CHANNEL_ID;
            NotificationChannel channel = new NotificationChannel(channelId, "notification_media", NotificationManager.IMPORTANCE_LOW);
//            channel.enableVibration(true);
//            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            notificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }
        startForeground(NotificationID, mBuilder.build());
        notification = mBuilder.build();
        notificationManager.notify(NotificationID, notification);
    }

}