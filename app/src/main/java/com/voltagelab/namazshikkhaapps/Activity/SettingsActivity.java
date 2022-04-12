package com.voltagelab.namazshikkhaapps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.R;

public class SettingsActivity extends AppCompatActivity {

    RadioGroup radioThemeGroup;
    RadioButton radioSelectedButton;
    Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        helper = new Helper(this);

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
                helper.setSessionString(Helper.THEMESETS,radioSelectedButton.getText().toString());
            }
        });
    }
}