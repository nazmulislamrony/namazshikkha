package com.voltagelab.namazshikkhaapps.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.work.WorkManager;

import com.voltagelab.namazshikkhaapps.Activity.AlQuran.Config;
import com.voltagelab.namazshikkhaapps.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MediaHelper {

    String root_folder = "namaz shikkha";
    String audio_recitation = "recitation";
    String audio_recitation_arabic = "arabic";
    String audio_recitation_bangla = "bangla";
    ArrayList<String> playList;
    ArrayList<String> downloadPlaylist;
    String ROOT_URL = "http://www.everyayah.com/data/Alafasy_128kbps/";

    File folderSurah;
    Context context;
//    File arabic, bangla;

    public MediaHelper(Context context) {
        this.context = context;
    }

//    public void audioFolderCreate(String surahId) {
//        File  rootfolder = new File(context.getExternalFilesDir(null).getAbsolutePath() + "/"+root_folder);
//        if (!rootfolder.exists()) {
//            rootfolder.mkdir();
//        }
//        File audiorecitation = new File(context.getExternalFilesDir(null).getAbsolutePath() + "/"+root_folder+ "/"+ audio_recitation);
//        if (!audiorecitation.exists()) {
//            audiorecitation.mkdir();
//        }
//
//       File arabic = new File(context.getExternalFilesDir(null).getAbsolutePath() + "/"+root_folder+ "/"+ audio_recitation+"/"+audio_recitation_arabic);
//        if (!arabic.exists()) {
//            arabic.mkdir();
//        }
//
//        folderSurah = new File(context.getExternalFilesDir(null).getAbsolutePath() + "/"+root_folder+ "/"+ audio_recitation+"/"+audio_recitation_arabic+"/"+surahId);
//        if (!folderSurah.exists()) {
//            folderSurah.mkdir();
//        }
//
//        File bangla = new File(context.getExternalFilesDir(null).getAbsolutePath() + "/"+root_folder+ "/"+ audio_recitation+"/"+audio_recitation_bangla);
//        if (!bangla.exists()) {
//            bangla.mkdir();
//        }
//
//        String arabiss = arabic +"/001001.mp3";
//        File folll = new File(arabiss);
//        if (!folll.exists()) {
//            folll.mkdir();
//        }
//
//    }
    String audioitem;
    public void createPlayList(int surahId, int totalVerse) {
        downloadPlaylist = new ArrayList<>();
        playList = new ArrayList<>();
        for (int i = 0;  i < totalVerse; i++) {
            if (surahId==1) {
                audioitem = convFileName(surahId, i+1);
            } else {
                audioitem = convFileName(surahId, i);
            }

            String arabicfile = folderSurah +"/"+audioitem+".mp3";
            File filearabic = new File(arabicfile);
            if (!filearabic.exists()) {
                downloadPlaylist.add(ROOT_URL + audioitem+".mp3");
            }
            playList.add(arabicfile);
            Log.d("get_totalverse","verse: "+arabicfile);
        }
    }

    public ArrayList<String> getDownloadList(){
        return downloadPlaylist;
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
    SeekBar downloadingseekbar;
    AlertDialog dialog;
    TextView txtNoInternetConnection;
    private void dialogShow() {
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
        WorkManager.getInstance(context).cancelAllWork();
    }




}
