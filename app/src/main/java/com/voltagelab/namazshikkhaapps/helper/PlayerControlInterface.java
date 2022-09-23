package com.voltagelab.namazshikkhaapps.helper;

public interface PlayerControlInterface {
    void onStartMedia(int mediaDuration);

     void currentPlayingIndex (int currentPlayIndex, int currentSurahId, int currentVerseId);

    void onProgress(String mediaName, int progress, int totalDuration, int mediaRepeatCount, int playlistRepeatCount, int delay);

    void onStop(int firstsurah, int firstAyah);


    void onCompletionPerIndex (boolean isCompletion);
}
