package com.voltagelab.namazshikkhaapps.ContentReader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.voltagelab.namazshikkhaapps.Model.ContentStore;
import com.voltagelab.namazshikkhaapps.R;

public class ContentReader extends AppCompatActivity {

    TextView  contentshow;

    ContentStore contentStore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content__reader);

        contentshow=findViewById(R.id.first_body_content);

        contentStore=getIntent().getParcelableExtra("ContentStore");


        getSupportActionBar().setTitle(contentStore.getDataName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        try {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                contentshow.setText(Html.fromHtml(contentStore.getDataContent(), Html.FROM_HTML_MODE_COMPACT));
            }else {
                contentshow.setText(Html.fromHtml(contentStore.getDataContent()));
            }
        }catch (Exception e){e.printStackTrace();}
    }
}
