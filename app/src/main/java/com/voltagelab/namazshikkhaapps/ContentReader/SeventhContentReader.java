package com.voltagelab.namazshikkhaapps.ContentReader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.voltagelab.namazshikkhaapps.Model.SeventhStore;
import com.voltagelab.namazshikkhaapps.R;

public class SeventhContentReader extends AppCompatActivity {

    TextView seventhContent;
    SeventhStore seventhStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh_content_reader);


        seventhContent=findViewById(R.id.seventh_bodycontent);

        seventhStore=getIntent().getParcelableExtra("SEVENTHBTN");



        getSupportActionBar().setTitle(seventhStore.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        try {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){

                seventhContent.setText(Html.fromHtml(seventhStore.getContent(), Html.FROM_HTML_MODE_COMPACT));

            }else {
                seventhContent.setText(Html.fromHtml(seventhStore.getContent()));
            }
        }catch (Exception e){e.printStackTrace();}
    }
}
