package com.voltagelab.namazshikkhaapps.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.voltagelab.namazshikkhaapps.ContentReader.EleventhContentReader;
import com.voltagelab.namazshikkhaapps.DatabaseAsset.DbAsset;
import com.voltagelab.namazshikkhaapps.Model.EleventhStore;
import com.voltagelab.namazshikkhaapps.R;
import com.voltagelab.namazshikkhaapps.RecyclerView.EleventRecyclerView;

import java.util.List;

public class EleventhButton extends AppCompatActivity {

    RecyclerView recyclerView;
    EleventRecyclerView eleventRecyclerViewAdapter;
    List<EleventhStore> eleventhStores;
    DbAsset db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleventh_button);
        recyclerView=findViewById(R.id.eleventhRecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getSupportActionBar().setTitle("দুয়া");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db=new DbAsset(this);

        eleventhStores=db.eleventhStores();

        eleventRecyclerViewAdapter=new EleventRecyclerView(eleventhStores,getBaseContext());

        recyclerView.setAdapter(eleventRecyclerViewAdapter);

        eleventRecyclerViewAdapter.setOnItemClickListener(new EleventRecyclerView.EleventhClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                Intent intent=new Intent(EleventhButton.this, EleventhContentReader.class);
                intent.putExtra("ELEVENTH",eleventhStores.get(position));
                startActivity(intent);
            }
        });




    }
}
