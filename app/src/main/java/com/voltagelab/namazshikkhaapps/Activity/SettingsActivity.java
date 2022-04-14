package com.voltagelab.namazshikkhaapps.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.R;

public class SettingsActivity extends AppCompatActivity {

    RadioGroup radioThemeGroup;
    RadioButton radioSelectedButton;
    Helper helper;
    ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        backArrow = findViewById(R.id.back_arrow);
        helper = new Helper(this);
        helper.backButtonPressed(this);

        radioThemeGroup = findViewById(R.id.radioGroupThemeSet);

        themeSet();

    }



    private void themeSet() {
        radioThemeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int selectedId = radioThemeGroup.getCheckedRadioButtonId();
                radioSelectedButton = findViewById(selectedId);
                Toast.makeText(SettingsActivity.this, radioSelectedButton.getTag().toString()+"", Toast.LENGTH_SHORT).show();
                helper.setSessionString(Helper.THEMESETS,radioSelectedButton.getTag().toString());
                helper.themeChange();
            }
        });
    }
}