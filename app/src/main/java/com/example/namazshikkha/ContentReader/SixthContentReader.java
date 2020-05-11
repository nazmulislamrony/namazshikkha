package com.example.namazshikkha.ContentReader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.namazshikkha.Model.FifthStore;
import com.example.namazshikkha.Model.SixthStore;
import com.example.namazshikkha.R;

public class SixthContentReader extends AppCompatActivity {

    TextView sixthContent;
    SixthStore sixthStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth_content_reader);


        sixthContent=findViewById(R.id.sixth_bodycontent);

        sixthStore=getIntent().getParcelableExtra("SIXTHBTN");



        getSupportActionBar().setTitle(sixthStore.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        try {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){

                sixthContent.setText(Html.fromHtml(sixthStore.getContent(), Html.FROM_HTML_MODE_COMPACT));

            }else {
                sixthContent.setText(Html.fromHtml(sixthStore.getContent()));
            }
        }catch (Exception e){e.printStackTrace();}
    }
}
