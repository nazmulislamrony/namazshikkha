package com.example.namazshikkha.ContentReader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.namazshikkha.Model.TwelveStore;
import com.example.namazshikkha.R;

public class TwelveContentReader extends AppCompatActivity {


    ImageView twelveImg;
    TwelveStore twelveStore;
    LinearLayout lineLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twelve_content_reader);

        twelveImg=findViewById(R.id.twelveImage);
        lineLayout=findViewById(R.id.imgeLinear);

        twelveStore=getIntent().getParcelableExtra("TWELVE");

        getSupportActionBar().setTitle(twelveStore.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d("datashow","checkval: "+twelveStore.getName());


        if (twelveStore.getId()==1){
            twelveImg.setImageResource(R.drawable.comfort);
            twelveImg.setImageResource(R.drawable.comfort);
            twelveImg.setImageResource(R.drawable.finance);
        }else {
            twelveImg.setImageResource(R.drawable.education);
        }
    }
}
