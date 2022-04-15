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
        RadioButton radiolight, radiodark, radiodefault;
        radiolight = findViewById(R.id.radiolight);
        radiodark = findViewById(R.id.radiodark);
        radiodefault = findViewById(R.id.radiodefault);

        radioThemeGroup = findViewById(R.id.radioGroupThemeSet);

        themeSet();


        String name =  (helper.getSessionString(Helper.THEMESETS,Helper.DEFAULTTHEMESETS));
        if (name.equals("light")) {
            Log.d("check_in_dat","if");
            radiolight.setChecked(true);
        } else if (name.equals("dark")){
            Log.d("check_in_dat","else if");
            radiodark.setChecked(true);
        } else {
            Log.d("check_in_dat","else ");
            radiodefault.setChecked(true);
        }

    }



    private void themeSet() {
        radioThemeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int selectedId = radioThemeGroup.getCheckedRadioButtonId();
                radioSelectedButton = findViewById(selectedId);
                helper.setSessionString(Helper.THEMESETS,radioSelectedButton.getTag().toString());
                helper.themeChange();
            }
        });
    }
}