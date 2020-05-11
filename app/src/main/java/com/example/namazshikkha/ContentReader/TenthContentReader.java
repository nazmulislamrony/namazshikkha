package com.example.namazshikkha.ContentReader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.namazshikkha.Model.TenthStore;
import com.example.namazshikkha.R;

public class TenthContentReader extends AppCompatActivity {

    TextView tenthBodyContent;

    TenthStore tenthStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenth_content_reader);

        tenthBodyContent=findViewById(R.id.tenth_body_content);

        tenthStore=getIntent().getParcelableExtra("TENTHBTN");

          getSupportActionBar().setTitle(tenthStore.getName());
          getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.N){
                tenthBodyContent.setText(Html.fromHtml(tenthStore.getContent(), Html.FROM_HTML_MODE_COMPACT));
            }else{
                tenthBodyContent.setText(Html.fromHtml(tenthStore.getContent()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
