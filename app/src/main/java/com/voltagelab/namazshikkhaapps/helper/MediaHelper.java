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
    int surahId;

    public MediaHelper(Context context) {
        this.context = context;
    }

    public void createPlayList(int surahId, int verseFrom, int totalVerse) {
        this.surahId = surahId;
        createPlayList = new ArrayList<>();
        playListStrings = new ArrayList<>();
        downloadList = new ArrayList<>();
        playListStrings = getFileString(surahId, verseFrom, totalVerse);

    }

    public void startDownloadService() {
        Intent intent = new Intent(context, DownloadService.class);
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
            playListStrings.add(context.getExternalFilesDir(null).getParent() + location + "/" + convFileName(suraid, i) + ".mp3");
        }
        return playListStrings;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public boolean isConnected() throws InterruptedException, IOException {
        String command = "ping -c 1 google.com";
        return Runtime.getRuntime().exec(command).waitFor() == 0;
    }


    Button exit, stop, cancels;
    TextView txtdownloadpercent, preparingdownloading, currentTotalVerse;
    CircularProgressIndicator downloadingseekbar;
    AlertDialog dialog;
    TextView txtNoInternetConnection;


    OnPlayList onPlayList;

    public void downloadOrPlay(OnPlayList onPlayList) {
        for (int i = 0; i < playListStrings.size(); i++) {
            fileExistsCheck(playListStrings.get(i));
        }
        this.onPlayList = onPlayList;
        Log.d("check_downloadlist", "list: " + downloadList.size());
        if (downloadList.size() > 0) {
            playListForDownload();
        } else {
            onPlayList.onPlayList(playListStrings);
        }
    }

    Button ok, cancel;
    TextView remainingdownload;
    AlertDialog alertDialog;

    public void playListForDownload() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.download_yes_no_dialog, null);
        dialogBuilder.setView(dialogView);

        ok = dialogView.findViewById(R.id.btn_ok_download);
        cancel = dialogView.findViewById(R.id.btn_cancel_download);
        remainingdownload = dialogView.findViewById(R.id.txt_remaining_download);
        alertDialog = dialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(false);


        // String ayat_download_str =  Resources.getSystem().getString(R.string.ayat_download_string);// " Ayah will be download";
        //remainingdownload.setText(downloadPlaylist.size() + ayat_download_string );

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
                Toast.makeText(context, "cancel", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
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

    private void onProgressUpdate(int count, int totalDownload) {
        String txt = (count > 0) ? "Download Progress" : "Preparing";
//        preparingdownloading.setText(txt);
        int percentageOfDownload = (count / totalDownload) * 100;
        downloadingseekbar.setProgress(count);
        txtdownloadpercent.setText(percentageOfDownload + "%");
        currentTotalVerse.setText(count + " Out of " + totalDownload);
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
            exit.setVisibility(View.VISIBLE);
            stop.setVisibility(View.GONE);
        });
    }

    private void stopWorkManager() {
        dialog.dismiss();
        stopService();
        downloadList.clear();

    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                int currentDownload = bundle.getInt(DownloadHelper.CURRENTDOWNLOAD) + 1;
                Toast.makeText(context, currentDownload + "", Toast.LENGTH_SHORT).show();
                if (currentDownload == downloadList.size()) {
                    onPlayList.onPlayList(playListStrings);
                    downloadList.clear();
                } else if (currentDownload == 0) {
                    Log.d("onfinishdownload", "no internet");
                    txtNoInternetConnection.setVisibility(View.VISIBLE);
                    currentTotalVerse.setVisibility(View.GONE);
                } else {
                    onProgressUpdate(currentDownload, downloadList.size());
                    Log.d("onfinishdownload", "cd:  " + currentDownload);
//                    txtNoInternetConnection.setVisibility(View.VISIBLE);
//                    currentTotalVerse.setVisibility(View.GONE);
//                    Toast.makeText(context, "Check your internet connection ", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    private void registerReceiver
            () {
        context.registerReceiver(receiver, new IntentFilter(
                DownloadService.NOTIFICATION));
    }

    public void onPause() {
//        context.unregisterReceiver(receiver);
        stopService();
    }

    public void stopService() {
        context.unregisterReceiver(receiver);
        context.stopService(new Intent(context, DownloadService.class));

    }


    private void onFinishWork() throws IOException, InterruptedException {

        Log.d("check_condition", ":" + isNetworkAvailable() + ", " + isConnected());

//        if (isNetworkAvailable() && isConnected()) {
//            dialog.dismiss();
//        } else {
//            txtNoInternetConnection.setVisibility(View.VISIBLE);
//            currentTotalVerse.setVisibility(View.GONE);
//            Toast.makeText(context, "Check your internet connection ", Toast.LENGTH_SHORT).show();
//        }
    }

    public static String convFileName(int i, int i2) {
        StringBuilder stringBuilder;
        String stringBuilder2;
        StringBuilder stringBuilder3;
        String str = "00";
        if (i < 10) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(i);
            stringBuilder2 = stringBuilder.toString();
        } else {
            stringBuilder2 = "";
        }
        String str2 = "0";
        if (i >= 10 && i < 100) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append(i);
            stringBuilder2 = stringBuilder.toString();
        }
        if (i >= 100) {
            stringBuilder2 = Integer.toString(i);
        }
        if (i2 < 10) {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(stringBuilder2);
            stringBuilder3.append(str);
            stringBuilder3.append(i2);
            stringBuilder2 = stringBuilder3.toString();
        }
        if (i2 >= 10 && i2 < 100) {
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(stringBuilder2);
            stringBuilder3.append(str2);
            stringBuilder3.append(i2);
            stringBuilder2 = stringBuilder3.toString();
        }
        if (i2 < 100) {
            return stringBuilder2;
        }
        stringBuilder3 = new StringBuilder();
        stringBuilder3.append(stringBuilder2);
        stringBuilder3.append(Integer.toString(i2));
        return stringBuilder3.toString();
    }


}
