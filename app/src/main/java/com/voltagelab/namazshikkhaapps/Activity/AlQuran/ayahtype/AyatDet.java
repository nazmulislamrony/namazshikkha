package com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype;

public class AyatDet {

    private String ayatdet;
    private int surahId;
    private int groupt_id;
    public boolean expanded;
    String surahName;
    String ayatNumber;
    String similarWord;



    public AyatDet(String ayatdet, int surahId, int group_id, String suranm, String atnnumb) {
        this.ayatdet = ayatdet;
        this.surahId = surahId;
        this.expanded = false;
        this.groupt_id = group_id;
        this.surahName = suranm;
        this.ayatNumber = atnnumb;
    }


    public AyatDet(String ayatdet, int surahId, int group_id, String suranm, String atnnumb, String similarWord) {
        this.ayatdet = ayatdet;
        this.surahId = surahId;
        this.expanded = false;
        this.groupt_id = group_id;
        this.surahName = suranm;
        this.ayatNumber = atnnumb;
        this.similarWord = similarWord;
    }



    public String getAyatdet() {
        return ayatdet;
    }

    public int getSurahId() {
        return surahId;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public int getGroupt_id() {
        return groupt_id;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getSurahName() {
        return surahName;
    }

    public String getAyatNumber() {
        return ayatNumber;
    }

    public String getSimilarWord() {
        return similarWord;
    }
}
