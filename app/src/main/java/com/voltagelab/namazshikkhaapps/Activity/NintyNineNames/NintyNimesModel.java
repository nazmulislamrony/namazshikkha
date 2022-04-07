package com.voltagelab.namazshikkhaapps.Activity.NintyNineNames;

public class NintyNimesModel {
    String id;
    String ar_name;
    String en_name;
    String bn_name;
    String bn_meaning;
    String en_meaning;
    String fazilat_bn;
    String fazilat_en;
    String reference;

    private boolean expandable;

    public NintyNimesModel() {
    }

//    public NintyNimesModel(String id, String ar_name, String en_name, String bn_name, String bn_meaning, String en_meaning, String fazilat_bn, String fazilat_en, String reference) {
//        this.id = id;
//        this.ar_name = ar_name;
//        this.en_name = en_name;
//        this.bn_name = bn_name;
//        this.bn_meaning = bn_meaning;
//        this.en_meaning = en_meaning;
//        this.fazilat_bn = fazilat_bn;
//        this.fazilat_en = fazilat_en;
//        this.reference = reference;
//        this.expandable = false;
//    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAr_name() {
        return ar_name;
    }

    public void setAr_name(String ar_name) {
        this.ar_name = ar_name;
    }

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }

    public String getBn_name() {
        return bn_name;
    }

    public void setBn_name(String bn_name) {
        this.bn_name = bn_name;
    }

    public String getBn_meaning() {
        return bn_meaning;
    }

    public void setBn_meaning(String bn_meaning) {
        this.bn_meaning = bn_meaning;
    }

    public String getEn_meaning() {
        return en_meaning;
    }

    public void setEn_meaning(String en_meaning) {
        this.en_meaning = en_meaning;
    }

    public String getFazilat_bn() {
        return fazilat_bn;
    }

    public void setFazilat_bn(String fazilat_bn) {
        this.fazilat_bn = fazilat_bn;
    }

    public String getFazilat_en() {
        return fazilat_en;
    }

    public void setFazilat_en(String fazilat_en) {
        this.fazilat_en = fazilat_en;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getId() {
        return id;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }


}
