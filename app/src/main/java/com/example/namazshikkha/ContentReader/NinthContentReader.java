package com.example.namazshikkha.ContentReader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.namazshikkha.Model.NinthStore;
import com.example.namazshikkha.R;

public class NinthContentReader extends AppCompatActivity {

    TextView ninthContent;
    NinthStore ninthStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninth_content_reader);

        ninthContent=findViewById(R.id.ninth_bodycontent);

        ninthStore=getIntent().getParcelableExtra("NINTHBTN");



        getSupportActionBar().setTitle(ninthStore.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        try {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){

                ninthContent.setText(Html.fromHtml(ninthStore.getContent(), Html.FROM_HTML_MODE_COMPACT));

            }else {
                ninthContent.setText(Html.fromHtml(ninthStore.getContent()));
            }
        }catch (Exception e){e.printStackTrace();}
    }
}
