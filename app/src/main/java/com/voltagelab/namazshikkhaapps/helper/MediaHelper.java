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
import androidx.work.WorkManager;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.downloader.DownloadHelper;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.downloader.DownloadService;
import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MediaHelper {

    ArrayList<String> downloadList;
    ArrayList<String> createPlayList;
    String reciter = "Alafasy_128kbps";
    String ROOT_URL = "https://www.everyayah.com/data/";
    ArrayList<String> playListStrings;
    Context context;

    public MediaHelper(Context context) {
        this.context = context;
    }

    public void createPlayList() {
        createPlayList = new ArrayList<>();
        playListStrings = new ArrayList<>();
        downloadList = new ArrayList<>();
    }

    public void startDownloadService() {
        Intent intent = new Intent(context, DownloadService.class);
        Log.d("check_downloadlist","list: "+downloadList.get(0));
        intent.putStringArrayListExtra(DownloadHelper.URL, downloadList);
        context.startService(intent);
//        dialogShow();
    }

    public ArrayList<String> fileExistsCheck(String playList) {
        File path = new File(playList);
        String mp3 = "/" + path.getName();
        String finalurl = ROOT_URL + reciter + mp3;
        Log.d("check_path", "path: " + path);
        if (!path.exists()) {
            downloadList.add(finalurl);
        }
        return downloadList;
    }

    private ArrayList<String> getFileString(int suraid, int startvalue, int stopvalue) {
        String location = "/" + DownloadHelper.DOWNLOADROOTFOLDER + "/" + reciter + "/" + suraid;
        for (int i = startvalue; i < stopvalue; i++) {
            playListStrings.add(context.getExternalFilesDir(null).getParent() + location + "/" + Helper.convFileName(suraid, i) + ".mp3");
        }
        return playListStrings;
    }


    Button exit, stop, cancels;
    TextView txtdownloadpercent, preparingdownloading, currentTotalVerse;
    CircularProgressIndicator downloadingseekbar;
    AlertDialog dialog;
    TextView txtNoInternetConnection;


    public void onVerseClick(String surahName, int surahIds, int verseFrom, OnPlayList onPlayList){
        this.name = surahName;
        this.onPlayList = onPlayList;
        playListStrings = new ArrayList<>();
        downloadList =  new ArrayList<>();
        playListStrings = getFileString(surahIds, verseFrom,verseFrom+1);
        try {
            for (int j = 0; j < playListStrings.size(); j++) {
                fileExistsCheck(playListStrings.get(j));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (downloadList.size() > 0) {
            playListForDownload();
        } else {
            onPlayList.onPlayList(playListStrings);
            downloadList = new ArrayList<>();
            playListStrings = new ArrayList<>();
        }
    }


    OnPlayList onPlayList;
    String name;
    int surahId;
    public void downloadOrPlay(int surahId, OnPlayList onPlayList, String name, int verseFrom, int totalVerse) {
        this.surahId = surahId;
        playListStrings = new ArrayList<>();
        downloadList =  new ArrayList<>();
        playListStrings = getFileString(surahId, verseFrom, totalVerse);
        this.name = name;
        for (int i = 0; i < playListStrings.size(); i++) {
            fileExistsCheck(playListStrings.get(i));
        }
        this.onPlayList = onPlayList;
        if (downloadList.size() > 0) {
            playListForDownload();
        } else {
            onPlayList.onPlayList(playListStrings);
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
        remainingdownload.setText(downloadList.size() + " টি আয়াত ডাউনলোড হবে");
        dialogButton(surahId + "");
        alertDialog.show();
    }

    private void dialogButton(String surahId) {
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
                downloadList.clear();
            }
        });
    }


    //////////////////////////// Dialog show when download starting////////////////////////
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

    private void stopWorkManager() {
        downloadList.clear();
        dialog.dismiss();
        stopService();
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                int currentDownload = bundle.getInt(DownloadHelper.CURRENTDOWNLOAD) + 1;
                if (currentDownload == downloadList.size()) {
                    for (int i = 0; i< playListStrings.size(); i++){
                        Log.d("check_playlist_siii","size: "+playListStrings.get(i));
                    }
                    onPlayList.onPlayList(playListStrings);
                    downloadList.clear();
                    dialog.dismiss();
                } else if (currentDownload == 0) {
                    downloadingseekbar.setVisibility(View.GONE);
                    txtNoInternetConnection.setVisibility(View.VISIBLE);
                    currentTotalVerse.setVisibility(View.GONE);
                    dialog.dismiss();
                } else {
                    onProgressUpdate(currentDownload, downloadList.size());
                }
            }
        }
    };

    private void registerReceiver () {
        context.registerReceiver(receiver, new IntentFilter(
                DownloadService.NOTIFICATION));
    }

    public void onPause() {
        stopService();
    }

    public void stopService() {
        context.stopService(new Intent(context, DownloadService.class));
    }




}
