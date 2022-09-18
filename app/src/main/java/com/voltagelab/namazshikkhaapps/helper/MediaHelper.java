package com.voltagelab.namazshikkhaapps.helper;

import android.content.Context;
import android.util.Log;

import com.voltagelab.namazshikkhaapps.Activity.AlQuran.Config;

import java.io.File;
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


}
