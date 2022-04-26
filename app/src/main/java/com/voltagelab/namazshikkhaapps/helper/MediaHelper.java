package com.voltagelab.namazshikkhaapps.helper;

import android.content.Context;
import android.util.Log;

import com.voltagelab.namazshikkhaapps.Activity.AlQuran.Config;

import java.io.File;
import java.util.ArrayList;

public class MediaHelper {

    String root_folder = "namaz shikkha";
    String audio_recitation = "audio recitation";
    String audio_recitation_arabic = "arabic";
    String audio_recitation_bangla = "bangla";
    ArrayList<String> playList;
    ArrayList<String> downloadPlaylist;
    String ROOT_URL = "http://www.everyayah.com/data/Alafasy_128kbps/";

    Context context;
    File arabic, bangla;

    public MediaHelper(Context context) {
        this.context = context;
        arabic = new File(context.getExternalFilesDir(null).getAbsolutePath() + "/"+root_folder+ "/"+ audio_recitation+"/"+audio_recitation_arabic);
        bangla = new File(context.getExternalFilesDir(null).getAbsolutePath() + "/"+root_folder+ "/"+ audio_recitation+"/"+audio_recitation_bangla);
    }

    public void audioFolderCreate() {
        File  rootfolder = new File(context.getExternalFilesDir(null).getAbsolutePath() + "/"+root_folder);
        if (!rootfolder.exists()) {
            rootfolder.mkdir();
        }
        File audiorecitation = new File(context.getExternalFilesDir(null).getAbsolutePath() + "/"+root_folder+ "/"+ audio_recitation);
        if (!audiorecitation.exists()) {
            audiorecitation.mkdir();
        }

        if (!arabic.exists()) {
            arabic.mkdir();
        }
        if (!bangla.exists()) {
            bangla.mkdir();
        }

        String arabiss = arabic +"/001001.mp3";
        File folll = new File(arabiss);
        if (!folll.exists()) {
            folll.mkdir();
        }

    }

    public void createPlayList(int surahId, int totalVerse) {
        downloadPlaylist = new ArrayList<>();
        playList = new ArrayList<>();
        for (int i = 0;  i < totalVerse; i++) {
            String audioitem = convFileName(surahId, i);
            String arabicfile = arabic +"/"+audioitem+".mp3";
            File filearabic = new File(arabicfile);
            if (!filearabic.exists()) {
                downloadPlaylist.add(ROOT_URL + audioitem);
            }
            playList.add(arabicfile);
            Log.d("get_totalverse","verse: "+arabicfile);
        }
    }

    public ArrayList<String> getPlayList(){
        return playList;
    }

    public ArrayList<String> getDownloadList(){
        return downloadPlaylist;
    }

    public void fileExistCheck() {


    }

//    public void playListCreate(int surahId, int verseId) {
//        String fileName = "/" + convFileName(surahId, verseId) + ".mp3";
//        playList.add(directory + surahId + fileName);
//        File path = new File(directory + surahId + fileName);
//        String URL = Global.MAIN_URL + reciter + path.getName();
//        if (path.exists()) {
//            Log.d(TAG, "pathexist: " + path);
//        } else {
//            downloadPlaylist.add(URL);
//            surahFolder.add(String.valueOf(surahId));
//            Log.d("check_play_list", "notexist: " + URL);
//        }
//    }
//
//    public void fileExistsCheck(String playList, int surahId) {
//        path = new File(playList);
//        mp3 = "/" + path.getName();
//        String finalurl = mainurl + reciter + mp3;
//        if (path.exists()) {
//            isExists = true;
//        } else {
//            downloadPlaylist.add(finalurl);
//            surahFolder.add(String.valueOf(surahId));
//        }
//    }
//
//
//
//    public boolean getReciter(String reciterName) {
//        File path = new File(context.getExternalFilesDir(null).getParent() + MAIN_FOLDER + reciterName);
//        if (path.exists()) {
//            return true;
//        } else {
//            return false;
//        }
//    }

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
