package com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype;



import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.voltagelab.namazshikkhaapps.Activity.AlQuran.SurahDataSource;
import com.voltagelab.namazshikkhaapps.Activity.AlQuran.ayahtype.database.DatabaseAccess;
import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AlQuranActivity extends AppCompatActivity {

    RecyclerView rvmain;
    public static String surahName;
    public LinearLayoutManager layoutManager;
    TextView tooltext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_al_quran);
        Helper helper = new Helper(this);
        helper.backButtonPressed(this);
        tooltext = findViewById(R.id.tooltext2);
        rvmain =  findViewById(R.id.rvmain);
        layoutManager = new LinearLayoutManager(this);
        databaseGetData();

    }

    private void databaseGetData() {
        Bundle bundle = this.getIntent().getExtras();
        surahName = bundle.getString(SurahDataSource.SURAH_NAME_TRANSLATE);
        String surahNameArabic = bundle.getString(SurahDataSource.SURAH_NAME_ARABIC);
        String surahid = bundle.getString(SurahDataSource.SURAH_ID_TAG);
        tooltext.setText(surahName);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        ArrayList<AyatDetails> ayatDetails = new ArrayList<>();
        ayatDetails = databaseAccess.getAyahDetails(Integer.parseInt( surahid));
        VerseAdapter adapter = new VerseAdapter(this, ayatDetails, Integer.parseInt(surahid));
        rvmain.setAdapter(adapter);
        rvmain.setLayoutManager(layoutManager);
    }
}