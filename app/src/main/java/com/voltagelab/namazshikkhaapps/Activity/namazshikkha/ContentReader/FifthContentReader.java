package com.voltagelab.namazshikkhaapps.Activity.namazshikkha.ContentReader;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.FifthStore;
import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.R;

public class FifthContentReader extends AppCompatActivity {

    TextView fifthContent;
    FifthStore fifthStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_content_reader);

        fifthContent=findViewById(R.id.fifth_bodycontent);

        fifthStore=getIntent().getParcelableExtra("FIFTHBTN");
        Helper helper = new Helper(this);
        helper.backButtonPressed(this);
        TextView tooltext = findViewById(R.id.tooltext2);
        tooltext.setText(fifthStore.getName());

//        getSupportActionBar().setTitle(fifthStore.getName());
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        try {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){

                fifthContent.setText(Html.fromHtml(fifthStore.getContent(), Html.FROM_HTML_MODE_COMPACT));

            }else {
                fifthContent.setText(Html.fromHtml(fifthStore.getContent()));
            }
        }catch (Exception e){e.printStackTrace();}
    }
}
