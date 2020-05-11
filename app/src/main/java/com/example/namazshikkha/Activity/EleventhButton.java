package com.example.namazshikkha.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.namazshikkha.ContentReader.EleventhContentReader;
import com.example.namazshikkha.DatabaseAsset.DbAsset;
import com.example.namazshikkha.Model.EleventhStore;
import com.example.namazshikkha.R;
import com.example.namazshikkha.RecyclerView.EleventRecyclerView;

import java.util.List;

public class EleventhButton extends AppCompatActivity {

    Toolbar eleventhToolbar;
    RecyclerView recyclerView;
    EleventRecyclerView eleventRecyclerViewAdapter;
    List<EleventhStore> eleventhStores;
    DbAsset db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleventh_button);
        eleventhToolbar=findViewById(R.id.eleventToolbar);
        recyclerView=findViewById(R.id.eleventhRecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setSupportActionBar(eleventhToolbar);
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
