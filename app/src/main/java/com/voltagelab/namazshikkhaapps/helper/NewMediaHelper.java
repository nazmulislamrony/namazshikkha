package com.voltagelab.namazshikkhaapps.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype.database.DatabaseAccess;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.downloader.DownloadHelper;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.downloader.DownloadService;
import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class NewMediaHelper {

    public static String MAIN_FOLDER = "/audio_recitation/";
    ArrayList<String> playListStrings;
    Context context;
    ArrayList<String> downloadPlaylist;
    File path;
    String mainurl = "https://everyayah.com/data/";
    String reciter = "Alafasy_128kbps";
    OnPlayList onPlayListListener;
    Helper sessionManager;
    ArrayList<String> surahFolder;
    boolean isFromBulk;
    boolean isFromExamMode;
    int exmModeSurahId;

    String name;


    public NewMediaHelper(Context context, OnPlayList onPlayListListener) {
        this.context = context;
        downloadPlaylist = new ArrayList<>();
        this.onPlayListListener = onPlayListListener;
        playListStrings = new ArrayList<>();
        surahFolder = new ArrayList<>();
        sessionManager = new Helper(context);
    }

//    public NewMediaHelper(Context context, OnPlayList onPlayListListener, String reciter, String name) {
//        this.context = context;
//        downloadPlaylist = new ArrayList<>();
//        this.onPlayListListener = onPlayListListener;
//        playListStrings = new ArrayList<>();
//        surahFolder = new ArrayList<>();
//        sessionManager = new Helper(context);
//        this.reciter = reciter;
//        this.name = name;
//    }


//    public NewMediaHelper(Context context) {
//        this.context = context;
//        sessionManager = new Helper(context);
//        downloadPlaylist = new ArrayList<>();
//        playListStrings = new ArrayList<>();
//        surahFolder = new ArrayList<>();
////        reciter = sessionManager.getSessionString(SessionManager.RECITER_SELECTED_NAME, "Alafasy_128kbps");
//    }

    public void reciterName(String reciter) {
        this.reciter = reciter;
    }





    boolean isPlaylist = false;

    public void createPlayList(int surahIds, String name) {
        this.name = name;
        isPlaylist = true;
        downloadPlaylist.clear();

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(context);
        String ayatTo = databaseAccess.getVersesnumn(surahIds);
        int verseFrom = 0;
        if (surahIds==1 || surahIds == 9) {
            verseFrom = 1;
        }
        int verseTo = Integer.parseInt(ayatTo);
        playListStrings = getFileString(surahIds, verseFrom, verseTo);
        try {
            for (int j = 0; j <= playListStrings.size(); j++) {
                Log.d("chekc_playlist","list: "+playListStrings.get(j));
                fileExistsCheck(playListStrings.get(j), surahIds);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (downloadPlaylist.size() > 0) {
            playListForDownload();
        } else {
            onPlayListListener.onPlayList(playListStrings);
            downloadPlaylist = new ArrayList<>();
            surahFolder = new ArrayList<>();
            playListStrings = new ArrayList<>();
        }
    }



    public void createVersePlayList(int surahIds, int verseFrom, String name) {
        this.name = name;
        isPlaylist = false;
        downloadPlaylist.clear();
        surahFolder.clear();
        playListStrings = getAdapterFileString(surahIds, verseFrom);
        try {
            for (int j = 0; j < playListStrings.size(); j++) {
                Log.d("chekc_playlist","vp: "+playListStrings.get(j));
                fileExistsCheck(playListStrings.get(j), surahIds);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (downloadPlaylist.size() > 0) {
            playListForDownload();
        } else {
            onPlayListListener.onPlayList(playListStrings);
            downloadPlaylist = new ArrayList<>();
            surahFolder = new ArrayList<>();
            playListStrings = new ArrayList<>();
        }
    }


    Button ok, cancel;
    TextView remainingdownload, initialie_download_txt;
    AlertDialog alertDialog;

    public void playListForDownload() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.download_yes_no_dialog, null);
        dialogBuilder.setView(dialogView);
        ok = dialogView.findViewById(R.id.btn_ok_download);
        initialie_download_txt = dialogView.findViewById(R.id.initialize_txt);
        cancel = dialogView.findViewById(R.id.btn_cancel_download);
        remainingdownload = dialogView.findViewById(R.id.txt_remaining_download);
        alertDialog = dialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        initialie_download_txt.setText( name+" সূরার তেলাওয়াত শুনার জন্য প্রথমবার অডিও ফাইল ডাউনলোড হবে।");
        remainingdownload.setText(downloadPlaylist.size() + " টি আয়াত ডাউনলোড হবে");
        dialogButton();
        alertDialog.show();
    }

    private void dialogButton() {
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDownloadService();
                registerReceiver();
                alertDialog.dismiss();
                downloadingDialogShow();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                downloadPlaylist.clear();
            }
        });
    }

    public void startDownloadService() {
        Intent intent = new Intent(context, DownloadService.class);
        Log.d("check_downloadlist","list: "+downloadPlaylist.get(0));
        intent.putStringArrayListExtra(DownloadHelper.URL, downloadPlaylist);
        context.startService(intent);
//        dialogShow();
    }


    Button exit, stop, cancels;
    TextView txtdownloadpercent, preparingdownloading, currentTotalVerse;
    CircularProgressIndicator downloadingseekbar;
    AlertDialog dialog;
    TextView txtNoInternetConnection;
    private void downloadingDialogShow() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.download_dialog_info, null);
        dialogBuilder.setView(dialogView);
        exit = dialogView.findViewById(R.id.btn_exit);
        stop = dialogView.findViewById(R.id.btn_stop);
        cancels = dialogView.findViewById(R.id.cancel_download_dialog);
        txtNoInternetConnection = dialogView.findViewById(R.id.txt_no_internet_connection);
        txtdownloadpercent = dialogView.findViewById(R.id.txt_percentage_of_download);
        preparingdownloading = dialogView.findViewById(R.id.txt_prepare_download);
        downloadingseekbar = dialogView.findViewById(R.id.seekbar_downloading);
        currentTotalVerse = dialogView.findViewById(R.id.txt_current_total_verse);
        dialog = dialogBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        downloadingseekbar.setProgress(0);
        downloadingseekbar.setMax(100);
        dialogButtonActive();
        dialog.show();
    }

    private void stopWorkManager() {
        downloadPlaylist.clear();
        dialog.dismiss();
        stopService();
    }

    private void dialogButtonActive() {
        exit.setOnClickListener((View v) -> {
            stopWorkManager();
            dialog.dismiss();
        });
        cancels.setOnClickListener((View v) -> {
            stopWorkManager();
            dialog.dismiss();
        });
        stop.setOnClickListener((View v) -> {
            stopWorkManager();
        });
    }

    public void stopService() {
        context.stopService(new Intent(context, DownloadService.class));
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                int currentDownload = bundle.getInt(DownloadHelper.CURRENTDOWNLOAD) + 1;
                if (currentDownload == downloadPlaylist.size()) {
                    onPlayListListener.onPlayList(playListStrings);
                    dialog.dismiss();
                } else if (currentDownload == 0) {
                    downloadingseekbar.setVisibility(View.GONE);
                    txtNoInternetConnection.setVisibility(View.VISIBLE);
                    currentTotalVerse.setVisibility(View.GONE);
                    dialog.dismiss();
                } else {
                    onProgressUpdate(currentDownload, downloadPlaylist.size());
                }
            }
        }
    };

    private void registerReceiver () {
        context.registerReceiver(receiver, new IntentFilter(
                DownloadService.NOTIFICATION));
    }

    double percentageOfDownload;
    private void onProgressUpdate(int count, int totalDownload) {
        String txt = (count > 0) ? "ডাউনলোড হচ্ছে" : "প্রস্তুত হচ্ছে";
        preparingdownloading.setText(txt);
//        Log.d("check_calc","cal: "+count+", "+totalDownload);
        if (count!=1 && count!=0 && totalDownload!=0) {
            percentageOfDownload = ((double) count / (double) totalDownload) * 100;
            Log.d("check_calc","result: "+percentageOfDownload+" c: " +count+",td "+totalDownload);
        }
        int result = (int) percentageOfDownload;
        downloadingseekbar.setProgress((int)result);
        txtdownloadpercent.setText(result + "%");
        currentTotalVerse.setText( totalDownload+" এর মধ্যে " +count);
    }


    private ArrayList<String> getFileString(int suraid, int startvalue, int stopvalue) {
        String location = "/" + DownloadHelper.DOWNLOADROOTFOLDER + "/" + reciter + "/" + suraid;
        for (int i = startvalue; i <= stopvalue; i++) {
            playListStrings.add(context.getExternalFilesDir(null).getParent() + location + "/" + Helper.convFileName(suraid, i) + ".mp3");
        }
        return playListStrings;
    }


    private ArrayList<String> getAdapterFileString(int suraid, int position) {
        String location = "/recitation/" + reciter + "/" + suraid;
        if (suraid == 1) {
            position += 1;
        }
        playListStrings.add(context.getExternalFilesDir(null).getParent() + location + "/" + Helper.convFileName(suraid, position) + ".mp3");
        return playListStrings;
    }


    String mp3;
    boolean isExists = false;

    public void fileExistsCheck(String playList, int surahId) {
        path = new File(playList);
        mp3 = "/" + path.getName();
        String finalurl = mainurl + reciter + mp3;
        if (path.exists()) {
            isExists = true;
        } else {
            downloadPlaylist.add(finalurl);
            surahFolder.add(String.valueOf(surahId));
        }
    }


}

