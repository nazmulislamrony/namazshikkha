package com.voltagelab.namazshikkhaapps.ContentReader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.voltagelab.namazshikkhaapps.Model.FourthStore;
import com.voltagelab.namazshikkhaapps.R;

public class FourthContentReader extends AppCompatActivity {

    TextView fourthContent;
    FourthStore fourthStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_content_reader);

        fourthContent=findViewById(R.id.fourth_bodycontent);

        fourthStore=getIntent().getParcelableExtra("FOURTHBTN");


        getSupportActionBar().setTitle(fourthStore.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        try {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){

                fourthContent.setText(Html.fromHtml(fourthStore.getContent(), Html.FROM_HTML_MODE_COMPACT));

            }else {
                fourthContent.setText(Html.fromHtml(fourthStore.getContent()));
            }
        }catch (Exception e){e.printStackTrace();}
    }
}
