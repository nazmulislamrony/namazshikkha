package com.voltagelab.namazshikkhaapps.Activity.NintyNineNames;

public class NintyNimesModel {
    String id;
    String icon;
    String eng_name;
    String bn_name;
    String bn_meaning;
    String eng_meaning;
    String eng_explanation;
    String ban_fazilat;
    private boolean expandable;


    public NintyNimesModel(String id, String icon, String eng_name, String bn_name, String bn_meaning, String eng_meaning, String eng_explanation, String ban_fazilat) {
        this.id = id;
        this.icon = icon;
        this.eng_name = eng_name;
        this.bn_name = bn_name;
        this.bn_meaning = bn_meaning;
        this.eng_meaning = eng_meaning;
        this.eng_explanation = eng_explanation;
        this.ban_fazilat = ban_fazilat;
        this.expandable = false;
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

    public String getIcon() {
        return icon;
    }

    public String getEng_name() {
        return eng_name;
    }

    public String getBn_name() {
        return bn_name;
    }

    public String getBn_meaning() {
        return bn_meaning;
    }

    public String getEng_meaning() {
        return eng_meaning;
    }

    public String getEng_explanation() {
        return eng_explanation;
    }

    public String getBan_fazilat() {
        return ban_fazilat;
    }
}
