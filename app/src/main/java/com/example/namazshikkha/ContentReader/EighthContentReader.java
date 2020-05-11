package com.example.namazshikkha.ContentReader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.namazshikkha.Model.EighthStore;
import com.example.namazshikkha.Model.SeventhStore;
import com.example.namazshikkha.R;

public class EighthContentReader extends AppCompatActivity {

    TextView  eightContent;
    EighthStore eighthStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eighth_content_reader);

        eightContent=findViewById(R.id.eight_bodycontent);

        eighthStore=getIntent().getParcelableExtra("EIGHTHBTN");

        getSupportActionBar().setTitle(eighthStore.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        try {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){

                eightContent.setText(Html.fromHtml(eighthStore.getContent(), Html.FROM_HTML_MODE_COMPACT));

            }else {
                eightContent.setText(Html.fromHtml(eighthStore.getContent()));
            }
        }catch (Exception e){e.printStackTrace();}
    }
}
