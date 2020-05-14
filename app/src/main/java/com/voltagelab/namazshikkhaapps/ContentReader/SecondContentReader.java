package com.voltagelab.namazshikkhaapps.ContentReader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.voltagelab.namazshikkhaapps.Model.SecondStore;
import com.voltagelab.namazshikkhaapps.R;

public class SecondContentReader extends AppCompatActivity {

    TextView secondcontent;
    SecondStore secondStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_content_reader);

        secondcontent=findViewById(R.id.second_body_content);

        secondStore=getIntent().getParcelableExtra("secondbtn");

        getSupportActionBar().setTitle(secondStore.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        try {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){

                secondcontent.setText(Html.fromHtml(secondStore.getContent(), Html.FROM_HTML_MODE_COMPACT));

            }else {
                secondcontent.setText(Html.fromHtml(secondStore.getContent()));
            }
        }catch (Exception e){e.printStackTrace();}

    }
}
