package com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype;

public class AyatDetails {
    String verse_id;
    String quran_quotes;
    String quran_translation;

    public AyatDetails(String verse_id, String quran_quotes, String quran_translation) {
        this.verse_id = verse_id;
        this.quran_quotes = quran_quotes;
        this.quran_translation = quran_translation;
    }

    public String getVerse_id() {
        return verse_id;
    }

    public void setVerse_id(String verse_id) {
        this.verse_id = verse_id;
    }

    public String getQuran_quotes() {
        return quran_quotes;
    }

    public void setQuran_quotes(String quran_quotes) {
        this.quran_quotes = quran_quotes;
    }

    public String getQuran_translation() {
        return quran_translation;
    }

    public void setQuran_translation(String quran_translation) {
        this.quran_translation = quran_translation;
    }
}
