package com.voltagelab.namazshikkhaapps.Activity.namazshikkha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.ContentReader.EleventhContentReader;
import com.voltagelab.namazshikkhaapps.DatabaseAsset.DbAsset;
import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.EleventhStore;
import com.voltagelab.namazshikkhaapps.R;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.RecyclerView.EleventRecyclerView;

import java.util.List;

public class DoaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EleventRecyclerView eleventRecyclerViewAdapter;
    List<EleventhStore> eleventhStores;
    DbAsset db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleventh_button);
        recyclerView=findViewById(R.id.eleventhRecycler);
        Helper helper = new Helper(this);
        helper.backButtonPressed(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        getSupportActionBar().setTitle("দোয়া");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db=new DbAsset(this);

        eleventhStores=db.getDoaActivity();

        eleventRecyclerViewAdapter=new EleventRecyclerView(eleventhStores,getBaseContext());

        recyclerView.setAdapter(eleventRecyclerViewAdapter);

        eleventRecyclerViewAdapter.setOnItemClickListener(new EleventRecyclerView.EleventhClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                Intent intent=new Intent(DoaActivity.this, EleventhContentReader.class);
                intent.putExtra("ELEVENTH",eleventhStores.get(position));
                startActivity(intent);
            }
        });




    }
}
