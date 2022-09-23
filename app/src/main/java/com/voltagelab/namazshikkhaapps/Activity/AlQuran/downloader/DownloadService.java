package com.voltagelab.namazshikkhaapps.Activity.AlQuran.downloader;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.work.Data;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class DownloadService extends IntentService {

    public static final String NOTIFICATION = "service receiver";
    String TAG = "download_service";
    private Toast toast;

    public DownloadService() {
        super("DownloadService");
    }

    // Will be called asynchronously by OS.
    @Override
    protected void onHandleIntent(Intent intent) {
        List<String> urlPath = intent.getStringArrayListExtra(DownloadHelper.URL);
        downloadFile(urlPath);
    }

    public String firstTwo(String str) {
        return str.length() < 3 ? str : str.substring(0, 3);
    }


    boolean isWorkFinish = false;

    private void downloadFile(List<String> path) {
        int fileLength = 0;
        try {
            for (int i = 0; i <= path.size(); i++) {
                String [] splirtArr = path.get(i).split("/",9);
                String replace = splirtArr[splirtArr.length-1].replace(".mp3","");
                int surahFolder = Integer.parseInt(firstTwo(replace));
                String reciterFolder = splirtArr[splirtArr.length-2];
                String mp3 = path.get(i);
                String lastWord = mp3.substring(mp3.lastIndexOf("/") + 1);
                String urlpath = path.get(i);
                URL url = new URL(urlpath);
                Log.d(TAG, "url: " + urlpath);
                URLConnection urlConnection = url.openConnection();
                Log.d(TAG, "urlconnection: " + urlConnection);
                urlConnection.connect();
                fileLength = urlConnection.getContentLength();
//                Log.d("checknetwrok","network: "+isNetworkOnline3()+", ");
                Log.d(TAG, "filelength: " + fileLength);
                File newFolder = new File(getApplicationContext().getExternalFilesDir(null).getParent() + "/" +DownloadHelper.DOWNLOADROOTFOLDER);
                if (!newFolder.exists()) {
                    newFolder.mkdir();
                }
                Log.d(TAG, "newfolder: " + newFolder);
                File reciterFile = new File(newFolder + "/" + reciterFolder + "/");
                if (!reciterFile.exists()) {
                    reciterFile.mkdir();
                }
                Log.d(TAG, "reciterfolder: " + reciterFolder);
                File finalFolder = new File(reciterFile + "/" + surahFolder + "/");
                if (!finalFolder.exists()) {
                    finalFolder.mkdir();
                }
                Log.d(TAG, "finalfolder: " + finalFolder);
                File inputFile = new File(finalFolder, lastWord);
//                File downloadedFile = inputFile;
                Log.d("input_file_info", "file: " + inputFile);
                // download the file
                InputStream inputStream = new BufferedInputStream(url.openStream(), 8192);
                byte[] data = new byte[1024];
                int total = 0;
                int count = 0;
                // Output stream
                OutputStream outputStream;
//                File firstVerse = new File(finalFolder + "/001001.mp3");
//                if (inputFile.equals(firstVerse)) { // it will check if playlist has 001001.mp3
//                    File firstSurahFirstVerse = new File(reciterFile + "/1/");
//                    if (!firstSurahFirstVerse.exists()) { // if folder 1 not exists
//                        firstSurahFirstVerse.mkdir();
//                    }
//                    File inputFirstVerse = new File(firstSurahFirstVerse + "/001001.mp3");
//                    outputStream = new FileOutputStream(inputFirstVerse); // put 001001.mp3 in folder 1
//                } else {
                    outputStream = new FileOutputStream(inputFile);
//                }
                try {
                    while ((count = inputStream.read(data)) != -1) {
                        total += count;
                        outputStream.write(data, 0, count);
                        int progress = total * 100 / fileLength;
//                        setProgressAsync(new Data.Builder().putInt("progress", progress).build());
//                        sessionManager.setCurrentDownloading(SessionManager.WORKER_DOWNLOADING_ITEM, i);
//                        functionCalledByYourAsyncWithUpdates(progress + "");
                    }
                } catch (FileNotFoundException ex) {
                    // complain to user
                    Log.d("check_exception", "a: " + ex.getMessage());
                } catch (IOException ex) {
                    // notify user
                    Log.d("check_exception", "b: " + ex.getMessage());
                    Log.d("check_exception", "b: " + inputFile);
                    if (inputFile.exists()) {
                        inputFile.delete();
                    }
                    break;
                }
                inputStream.close();
                outputStream.close();
                publishResults(i);
                if (isWorkFinish){
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            publishResults(-1);
            Log.d("check_exception", "c: " + e.getMessage());
        }

    }

    public void functionCalledByYourAsyncWithUpdates(String progress) {
        toast.setText(progress);
        toast.show();
    }

    private void publishResults(int currentDownload) {
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(DownloadHelper.CURRENTDOWNLOAD, currentDownload);
        sendBroadcast(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
        stopForeground(true);
        isWorkFinish = true;
        Log.d("check_on_destroy","desstroy");
    }
}