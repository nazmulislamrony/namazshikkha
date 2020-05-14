package com.voltagelab.namazshikkhaapps.ContentReader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.voltagelab.namazshikkhaapps.Model.EleventhStore;
import com.voltagelab.namazshikkhaapps.R;

public class EleventhContentReader extends AppCompatActivity {

    TextView eleventhText;
    EleventhStore eleventhStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleventh_content_reader);

        eleventhText=findViewById(R.id.eleventh_body_content);
        eleventhStore=getIntent().getParcelableExtra("ELEVENTH");

        getSupportActionBar().setTitle(eleventhStore.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        try {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){

                eleventhText.setText(Html.fromHtml(eleventhStore.getContent(), Html.FROM_HTML_MODE_COMPACT));

            }else {
                eleventhText.setText(Html.fromHtml(eleventhStore .getContent()));
            }
        }catch (Exception e){e.printStackTrace();}
    }

}
