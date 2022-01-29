package com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype;



import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.voltagelab.namazshikkhaapps.Activity.AlQuran.SurahDataSource;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype.database.DatabaseAccess;
import com.voltagelab.namazshikkhaapps.R;

import java.util.ArrayList;

public class AlQuranActivity extends AppCompatActivity {

    RecyclerView rvmain;
    TextView tooltext1, tooltext2;
    private ArrayList<String> ayatlist = new ArrayList<>();
    private ArrayList<String> ayatidslist = new ArrayList<>();
    private ArrayList<AyatDet> ayatdetlist = new ArrayList<>();
    public static String surahName;
    public LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_al_quran);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        rvmain =  findViewById(R.id.rvmain);
//        tooltext1 = findViewById(R.id.tooltext1);
//        tooltext2 = findViewById(R.id.tooltext2);
        layoutManager = new LinearLayoutManager(this);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0F9D58"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        databaseGetData();

    }

    private void databaseGetData() {
        Bundle bundle = this.getIntent().getExtras();
        surahName = bundle.getString(SurahDataSource.SURAH_NAME_TRANSLATE);
        String surahNameArabic = bundle.getString(SurahDataSource.SURAH_NAME_ARABIC);
        long surahid = bundle.getLong(SurahDataSource.SURAH_ID_TAG);
        getSupportActionBar().setTitle(surahNameArabic);

        Log.d("AlQuranActivity", "ID: " + surahid + " Surah Name: " + surahName);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        ayatlist = databaseAccess.getQuotes((int) surahid);
        ayatidslist = databaseAccess.getQuotesId((int) surahid);
        databaseAccess.close();
        Typeface faceArab = Typeface.createFromAsset(this.getAssets(), "fonts/noorehuda.ttf");
//        tooltext1.setTypeface(faceArab);
//        tooltext1.setText(surahNameArabic);
//        tooltext2.setText(surahName);

        ayatlist.add(0, "بِسۡمِ اللّٰهِ الرَّحۡمٰنِ الرَّحِیۡمِ");
        ayatidslist.add(0, "0-0");

        for (int i = 0; i < ayatlist.size(); i++) {
            ayatdetlist.add(new AyatDet(ayatlist.get(i), (int) surahid, 0, "", ""));
        }

       MainAdapter adapter = new MainAdapter(this, ayatdetlist);
        rvmain.setAdapter(adapter);
        rvmain.setLayoutManager(layoutManager);

    }
}