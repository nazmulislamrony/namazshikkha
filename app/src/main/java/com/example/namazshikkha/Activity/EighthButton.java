package com.example.namazshikkha.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.namazshikkha.ContentReader.EighthContentReader;
import com.example.namazshikkha.DatabaseAsset.DbAsset;
import com.example.namazshikkha.Model.EighthStore;
import com.example.namazshikkha.Model.SeventhStore;
import com.example.namazshikkha.R;
import com.example.namazshikkha.RecyclerView.EighthRecyclerView;
import com.example.namazshikkha.RecyclerView.SeventhRecyclerView;

import java.util.List;

public class EighthButton extends AppCompatActivity {

    RecyclerView recyclerViewer;
    EighthRecyclerView eighthRecyclerViewAdapter;
    List<EighthStore> eighthStores;
    DbAsset databaseAsset;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eighth_button);

        toolbar=findViewById(R.id.eighth_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("তাহাজ্জুদ ও তারাবি");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewer=findViewById(R.id.eighth_recyclerView);
        recyclerViewer.setLayoutManager(new LinearLayoutManager(this));

        databaseAsset=new DbAsset(this);
        eighthStores=databaseAsset.eighthStores();

        eighthRecyclerViewAdapter=new EighthRecyclerView(getBaseContext(),eighthStores);
        recyclerViewer.setAdapter(eighthRecyclerViewAdapter);

        eighthRecyclerViewAdapter.setOnItemClickListener(new EighthRecyclerView.EighthClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                Intent intent=new Intent(EighthButton.this, EighthContentReader.class);
                intent.putExtra("EIGHTHBTN",eighthStores.get(position));
                startActivity(intent);
            }
        });
    }
}
