package com.voltagelab.namazshikkhaapps.Activity.namazshikkha.ContentReader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.NinthStore;
import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.R;

public class NinthContentReader extends AppCompatActivity {

    TextView ninthContent;
    NinthStore ninthStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninth_content_reader);

        ninthContent=findViewById(R.id.ninth_bodycontent);

        ninthStore=getIntent().getParcelableExtra("NINTHBTN");

        Helper helper = new Helper(this);
        helper.backButtonPressed(this);
        TextView tooltext = findViewById(R.id.tooltext2);
        tooltext.setText(ninthStore.getName());

//        getSupportActionBar().setTitle(ninthStore.getName());
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//

        try {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){

                ninthContent.setText(Html.fromHtml(ninthStore.getContent(), Html.FROM_HTML_MODE_COMPACT));

            }else {
                ninthContent.setText(Html.fromHtml(ninthStore.getContent()));
            }
        }catch (Exception e){e.printStackTrace();}
    }
}
