package com.example.namazshikkha.ContentReader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.example.namazshikkha.Model.ThirdStore;
import com.example.namazshikkha.R;

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
