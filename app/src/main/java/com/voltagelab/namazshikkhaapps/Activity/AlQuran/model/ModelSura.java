package com.voltagelab.namazshikkhaapps.Activity.AlQuran.model;

public class ModelSura {
    String id;
    String name;
    String name_arabic;
    String bn_meaning;
    String ayat;
    String place_bn;

//    public ModelSura(String id, String name, String name_arabic, String bn_meaning, String ayat, String place_bn) {
//        this.id = id;
//        this.name = name;
//        this.name_arabic = name_arabic;
//        this.bn_meaning = bn_meaning;
//        this.ayat = ayat;
//        this.place_bn = place_bn;
//    }

    public ModelSura(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBnSuraName() {
        return name;
    }

    public void setBnSuraName(String name) {
        this.name = name;
    }

    public String getName_arabic() {
        return name_arabic;
    }

    public void setName_arabic(String name_arabic) {
        this.name_arabic = name_arabic;
    }

    public String getBn_meaning() {
        return bn_meaning;
    }

    public void setBn_meaning(String bn_meaning) {
        this.bn_meaning = bn_meaning;
    }

    public String getAyat() {
        return ayat;
    }

    public void setAyat(String ayat) {
        this.ayat = ayat;
    }

    public String getPlace_bn() {
        return place_bn;
    }

    public void setPlace_bn(String place_bn) {
        this.place_bn = place_bn;
    }
}
