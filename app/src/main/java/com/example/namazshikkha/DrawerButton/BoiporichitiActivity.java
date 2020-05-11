package com.example.namazshikkha.DrawerButton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.namazshikkha.R;

public class BoiporichitiActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boiporichiti);


        getSupportActionBar().setTitle("সহীহ নামাজ ও দোয়া শিক্ষা");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
