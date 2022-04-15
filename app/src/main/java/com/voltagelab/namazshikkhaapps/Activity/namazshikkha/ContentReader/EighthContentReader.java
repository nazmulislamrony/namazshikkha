package com.voltagelab.namazshikkhaapps.Activity.namazshikkha.ContentReader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.EighthStore;
import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.R;

public class EighthContentReader extends AppCompatActivity {

    TextView  eightContent;
    EighthStore eighthStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eighth_content_reader);
        Helper helper = new Helper(this);
        helper.backButtonPressed(this);
        TextView tooltext = findViewById(R.id.tooltext2);
        eightContent=findViewById(R.id.eight_bodycontent);

        eighthStore=getIntent().getParcelableExtra("EIGHTHBTN");

        tooltext.setText(eighthStore.getName());

//        getSupportActionBar().setTitle(eighthStore.getName());
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        try {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){

                eightContent.setText(Html.fromHtml(eighthStore.getContent(), Html.FROM_HTML_MODE_COMPACT));

            }else {
                eightContent.setText(Html.fromHtml(eighthStore.getContent()));
            }
        }catch (Exception e){e.printStackTrace();}
    }
}
