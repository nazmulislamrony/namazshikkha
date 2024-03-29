package com.voltagelab.namazshikkhaapps.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.voltagelab.namazshikkhaapps.ContentReader.TwelveContentReader;
import com.voltagelab.namazshikkhaapps.DatabaseAsset.DbAsset;
import com.voltagelab.namazshikkhaapps.Model.TwelveStore;
import com.voltagelab.namazshikkhaapps.R;
import com.voltagelab.namazshikkhaapps.RecyclerView.TwelveRecyclerView;

import java.util.List;

public class TwelveButton extends AppCompatActivity {

    RecyclerView recyclerView;
    TwelveRecyclerView twelveRecyclerView;
    List<TwelveStore> twelveStores;
    DbAsset database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twelve_button);

        recyclerView=findViewById(R.id.twelveRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database=new DbAsset(this);

        getSupportActionBar().setTitle("চিত্রসহ নামাজ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        twelveStores=database.twelveStores();
        twelveRecyclerView=new TwelveRecyclerView(getBaseContext(),twelveStores);
        recyclerView.setAdapter(twelveRecyclerView);

        twelveRecyclerView.setOnItemClickListener(new TwelveRecyclerView.TwelveClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                Intent intent=new Intent(TwelveButton.this,TwelveContentReader.class);
                intent.putExtra("TWELVE",twelveStores.get(position));
                startActivity(intent);
            }
        });
    }
}
