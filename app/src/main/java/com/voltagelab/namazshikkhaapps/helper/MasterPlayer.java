package com.voltagelab.namazshikkhaapps.helper;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.util.Log;
import android.widget.SeekBar;

import androidx.annotation.RequiresApi;

import com.voltagelab.namazshikkhaapps.Helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



public class MasterPlayer {
    private static final String TAG = "MasterPlayer";
    private MediaPlayer mp;
    public PlayerState playerStatus = PlayerState.STOPPED;
    private final PlayerControlInterface controlInterface;
    private List<String> playlist;
    private int mediaRepeat, playListRepeat, delay,
            mediaRepeatCount, playListRepeatCount, playItemIndex;
    private float playbackspeed;

    private Timer playTimer;
    private final Handler mTimerHandler = new Handler();

//    private SeekBar seekBar;


    @RequiresApi(api = Build.VERSION_CODES.M)
    public MasterPlayer(PlayerControlInterface controlInterface, Context context) {
//        this(2, 2, 3000, controlInterface); // test purpose
        this(context, 1, 1, 0, controlInterface, 1); // default
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public MasterPlayer(Context context, int mediaRepeat, int playListRepeat, int delay, PlayerControlInterface controlInterface, float playbackspeeds) {
        this.mediaRepeat = mediaRepeat;
        this.playListRepeat = playListRepeat;
        this.delay = delay;
        this.playbackspeed = playbackspeeds;
        this.controlInterface = controlInterface;
        this.playlist = new ArrayList<>();
        this.mp = new MediaPlayer();
        mp.setWakeMode(context, PowerManager.PARTIAL_WAKE_LOCK);
        Helper sessionManager = new Helper(context);
        Log.d("contesfafxsst", "contex: " + context);
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        // initiate the audio playback attributes
        playbackAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        // set the playback attributes for the focus requester
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            AudioFocusRequest focusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                    .setAudioAttributes(playbackAttributes)
                    .setAcceptsDelayedFocusGain(true)
                    .setOnAudioFocusChangeListener(audioFocusChangeListener)
                    .build();


            // request the audio focus and
            // store it in the int variable
            final int audioFocusRequest = audioManager.requestAudioFocus(focusRequest);

//            if (audioFocusRequest == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//                Log.d("check_on_foc", "play");
//                mp.start();
//            }
        }

        mp.setOnPreparedListener(mp -> {
//            Log.i(TAG, "onPreparedListener");

            sessionManager.checkAPILevel();
            Log.d("sessionmanagers", "0: disable "+sessionManager.isAPIGreaterThan23());
            mp.start();
            Log.d("check_call_sl", "1: enable"); // enable late good
            Log.d("playcurrent_items", "current_item: " + playlist.get(playItemIndex));
            if (sessionManager.isAPIGreaterThan23()) {
                PlaybackParams params = mp.getPlaybackParams();
                params.setSpeed(playbackspeed);
                mp.setPlaybackParams(params);
            }
            controlInterface.onCompletionPerIndex(false); // current playing
            Log.d("isplayinssfgcheck", "1: " + mp.isPlaying());

        });


        Log.d("play_list_count", "out: " + playItemIndex + ", " + playlist.size());
        mp.setOnCompletionListener(mp -> {
            controlInterface.onCompletionPerIndex(true);
            Log.d("check_call_sl", "2: disable");
            if (playerStatus != PlayerState.PAUSED) {
                Log.i("state_play_pause", "onCompletionListener if" + playItemIndex);
                if (mediaRepeatCount + 1 < this.mediaRepeat) { // is media repeat available
                    mediaRepeatCount++;
                    play(true);
                    Log.d("play_list_count", "first: " + playItemIndex + ", " + playlist.size());
                } else if (playItemIndex < playlist.size() - 1) { // is next media available
                    mediaRepeatCount = 0;
                    playItemIndex++;
                    Log.d("play_list_count", "sec: " + playItemIndex + ", " + playlist.size());
                    Log.d("isplayinssfgcheck", "2: " + mp.isPlaying());
                    Log.d("ste_play_pause", "1: ");
                    play();
                } else if (playListRepeatCount + 1 < this.playListRepeat) { // is playList repeat available
                    playListRepeatCount++;
                    mediaRepeatCount = 0;
                    playItemIndex = 0;
                    play();
                } else { // playlist finished
                    resetConfig();
                    stop();
                }
            } else {
                Log.i("state_play_pause", "onCompletionListener else" + playItemIndex);
                if (playItemIndex < playlist.size() - 1) { // is next media available
                    mediaRepeatCount = 0;
                    playItemIndex++;
                    playerStatus = PlayerState.STOPPED;
                }
            }

            printStatus();
        });

        resetConfig();
        printStatus();
    }

    public void attachSeekBar(SeekBar sb) {
//        seekBar = sb;
//        seekBar.setClickable(false);
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean isFromUser) {
//                if (isFromUser) mp.seekTo(i * 1000);
////                Log.d(TAG, "onProgressChanged : " + i);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//            }
//        });
    }

    private void stopTimer() {
        if (playTimer != null) {
            playTimer.cancel();
            playTimer.purge();
        }
    }

    /*
     * this timer updates the seekbar progress
     * */
    private void startTimer() {
        if (playTimer != null) return;
        playTimer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                mTimerHandler.post(() -> notifyProgressUpdate());
            }
        };


        playTimer.schedule(timerTask, 1000, 1000);
    }

    private void notifyProgressUpdate() {
        if (playerStatus == PlayerState.PLAYING) {
//            String mediaName = playlist.get(playItemIndex);
            int progress = mp.getCurrentPosition() / 100;
            Log.d("getprogressss","pr"+progress);
            int totalDuration = mp.getDuration();
            controlInterface.onProgress("", progress, totalDuration, mediaRepeatCount, playListRepeatCount, delay);
//            if (seekBar != null) seekBar.setProgress(progress);
        }
    }

    public void resetConfig() {
        playListRepeatCount = 0;
        mediaRepeatCount = 0;
        playItemIndex = 0;
//        playerStatus = PlayerState.STOPPED;
    }

    public void pause() {

//        Log.d("state_play_pagbause","state outer: "+mp.isPlaying() +", "+mp);

        if (mp != null) {
            if (mp.isPlaying() && playerStatus == PlayerState.PLAYING) {
                Log.d("state_plassgy_pause", "state inner: ");
                mp.pause();
                playerStatus = PlayerState.PAUSED;
                isPauseFromBtn = true;
            }
        }
    }

//    public void pauseForIncomingOutgoingCall() {
//
//
//        if (playerStatus == PlayerState.PLAYING) {
//
//            Log.d("state_plassgy_pause", "state inner: " + mp.isPlaying() +", ps: "+playerStatus);
//            mp.pause();
//            playerStatus = PlayerState.PAUSED;
//        }
//    }

    boolean isDelay = false;
    int tempDelay;

    private void playWithDelay(boolean playCurrentItem) {
        // first item play without counting the delay, delay start from second verse
        tempDelay = delay;
        if (!isDelay) {
            tempDelay = 0;
            isDelay = true;
        } else {
            tempDelay = delay;
        }

        Log.d("get_temp_delay", "delay: " + tempDelay);

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            if (playerStatus == PlayerState.PLAYING) {
                if (playCurrentItem) mp.start();
                else playFile();
                Log.d("playcurrenst_itssems", "playwithdelay: " + playItemIndex);
            }
//            if (seekBar != null) seekBar.setMax(mp.getDuration() / 1000);
        }, tempDelay);
    }

    public void play() {
        Log.d(TAG, "inFunction play");
        play(false);
    }

    int firstsurah, firstayah;

    private void play(boolean playCurrentItem) {
        if (playerStatus == PlayerState.PAUSED) {
            playerStatus = PlayerState.PLAYING;
//            startTimer();
            mp.start();
            return;
        }

        Log.d("playcurrenst_itssems", "\nplay: " + playItemIndex);// 0 , 1 then play, it should play from 0


        if (delay > 0) playWithDelay(playCurrentItem);
        else if (playCurrentItem) mp.start();
        else playFile();


        playerStatus = PlayerState.PLAYING;
        controlInterface.onStartMedia(mp.getDuration());


//        controlInterface.currentPlayingIndex(playItemIndex);
        // for verse click get verse id;
        File recitationname = new File(new File(playlist.get(playItemIndex)).getName());
        String reciter = String.valueOf(recitationname);
        String replacemp3 = reciter.replace(".mp3", "");
        String last3 = replacemp3.substring(replacemp3.length() - 3);
        String first3 = replacemp3.substring(0, 3);
        int currentPlayIndx = Integer.parseInt(last3);
        int currentSurah = Integer.parseInt(first3);
        if (playlist.size() == 1) {
            Log.d("current_play_index", "c: " + currentPlayIndx);
//            currentPlayIndx = (currentSurah == 1) ? currentPlayIndx - 1 : currentPlayIndx;
            controlInterface.currentPlayingIndex(currentPlayIndx, currentSurah, currentPlayIndx); // for single verse / loop click
        } else {
            controlInterface.currentPlayingIndex(playItemIndex, currentSurah, currentPlayIndx); // for whole playlist
        }

        if (playItemIndex == 0) { // first ayah and verse selected
            firstsurah = currentSurah;
            firstayah = currentPlayIndx;
            Log.d("firstsurah_current", "fs " + firstsurah + ", fa: " + firstayah);
        }
        //        if (seekBar != null) {
//            controlInterface.currentPlayingIndex(playItemIndex);
//            seekBar.setMax(mp.getDuration() / 1000);
//            seekBar.setProgress(0);
//        startTimer();

        notifyProgressUpdate();

    }
//    }


    private void playFile() {
        try {
            mp.reset(); // player needs to reset before playing the next media
            mp.setDataSource(playlist.get(playItemIndex));
//            Log.d("playcurrent_items", "current_item: " + playlist.get(playItemIndex));
            mp.prepare();

//            controlInterface.onCompletionPerIndex(true);
//            if (isLooping) {
//                mp.setLooping(true);
//            }


//            setLooping(true);

        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "play: " + e.getMessage());
        }
    }

    public void stop() {
        mp.stop();
        playItemIndex = 0;
        playerStatus = PlayerState.STOPPED;
        controlInterface.onStop(firstsurah, firstayah);
        Log.d("first_last_sura", "f: " + firstsurah + ", " + firstayah);
        stopTimer();
//        if (seekBar != null) seekBar.setProgress(0);
    }

    public void seekTo(int seekPosition) {
        mp.seekTo(seekPosition);
    }

    boolean isLooping;

    public void setLooping(boolean isLooping) {
        this.isLooping = isLooping;
        if (isLooping) {
            try {
                mp.reset(); // player needs to reset before playing the next media
                Log.d("playitemindexx", "index: " + playItemIndex);
                mp.setDataSource(playlist.get(playItemIndex));
//            Log.d("playcurrent_items", "current_item: " + playlist.get(playItemIndex));
                mp.prepare();

                mp.setLooping(true);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            mp.setLooping(false);
        }

        Log.d("checkloopistruefalse", "MP: " + isLooping);
//        loopingPlay();
    }

    public void loopingPlay() {
        try {
            mp.reset(); // player needs to reset before playing the next media
            Log.d("playitemindexx", "index: " + playItemIndex);
            mp.setDataSource(playlist.get(playItemIndex));
//            Log.d("playcurrent_items", "current_item: " + playlist.get(playItemIndex));
            mp.prepare();
            if (isLooping) {
                mp.setLooping(true);
            } else {
                mp.setLooping(false);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
//                        controlInterface.onStop(firstsurah, firstayah);
                        Log.d("oncompleitonnns", "index: ");

                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "play: " + e.getMessage());
        }
    }

    /*
     * playlist means the list of path from all playable audio files
     * */
    public void setPlayList(List<String> playlist) {
        this.playlist = playlist;
        Log.d("set_play_list", "play: " + playlist);
    }

    public void setMediaRepeat(int mediaRepeat) { // verse repeat directly can access
        this.mediaRepeat = mediaRepeat;
        Log.d("mediaRepeat", "repeat: " + mediaRepeat);
        notifyProgressUpdate();
        printStatus();
    }


    public void incrementVerseRepeat() {
//        this.mediaRepeat++;
//        notifyProgressUpdate();
//        printStatus();
        setMediaRepeat(mediaRepeat + 1);
    }

    public void setPlayListRepeat(int playListRepeat) { // range repeat
        this.playListRepeat = playListRepeat;
        notifyProgressUpdate();
        printStatus();
    }

    public void setPlaybackspeed(float speed) {
        this.playbackspeed = speed;
    }

    public void incrementRangeRepeat() {
//        this.playListRepeat++;
//        Log.d("playList_repeat","repeat: "+playListRepeat);
//        notifyProgressUpdate();
//        printStatus();
        setPlayListRepeat(playListRepeat + 1);
    }

    public void setDelay(int delaySecond) {
        this.delay = delaySecond * 1000;
        notifyProgressUpdate();
        printStatus();
    }

    public void resetMediaCount() {
        this.mediaRepeat = 0;
        this.playListRepeat = 0;
        this.delay = 0;
        this.playbackspeed = 1.0f;
    }

    public void onDestroy() {
        mp.release();
        mp = null;
        stopTimer();
    }

    private void printStatus() {
        String status = "media=" + mediaRepeatCount + "/" + mediaRepeat
                + ", playList=" + playListRepeatCount + "/" + playListRepeat
                + ", delay=" + delay
                + ", playIndex=" + playItemIndex
                + ", playerStatus=" + playerStatus;
        Log.i(TAG, status);
    }


    // Audio manager instance to manage or
    // handle the audio interruptions
    AudioManager audioManager;

    // Audio attributes instance to set the playback
    // attributes for the media player instance
    // these attributes specify what type of media is
    // to be played and used to callback the audioFocusChangeListener
    AudioAttributes playbackAttributes;

    // media player is handled according to the
    // change in the focus which Android system grants for

    // pause from button will not work for incoming call

    boolean isPauseFromBtn;

    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                if (playerStatus == PlayerState.PAUSED && !isPauseFromBtn) {
                    mp.start();
                    playerStatus = PlayerState.PLAYING;
                }
                Log.d("check_on_foc", "start" + playerStatus);
//                mp.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                if (playerStatus == PlayerState.PLAYING) {
                    mp.pause();
                    playerStatus = PlayerState.PAUSED;
                    isPauseFromBtn = false;
                }
                Log.d("check_on_foc", "pause");
//                mp.pause();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                if (mp!=null && playerStatus == PlayerState.STOPPED) {
                    mp.release();
                }
            }
        }
    };
}