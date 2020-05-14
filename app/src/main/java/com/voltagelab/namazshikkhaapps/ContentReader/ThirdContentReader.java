package com.voltagelab.namazshikkhaapps.ContentReader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.voltagelab.namazshikkhaapps.Model.ThirdStore;
import com.voltagelab.namazshikkhaapps.R;

public class ThirdContentReader extends AppCompatActivity {

    TextView thirdContent;
    ThirdStore thirdStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_content_reader);


        thirdContent=findViewById(R.id.third_body_content);

        thirdStore=getIntent().getParcelableExtra("thirdbtn");


        getSupportActionBar().setTitle(thirdStore.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        try {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){

                thirdContent.setText(Html.fromHtml(thirdStore.getContent(), Html.FROM_HTML_MODE_COMPACT));

            }else {
                thirdContent.setText(Html.fromHtml(thirdStore.getContent()));
            }
        }catch (Exception e){e.printStackTrace();}
    }
}
