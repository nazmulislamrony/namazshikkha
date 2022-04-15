package com.voltagelab.namazshikkhaapps.Activity.namazshikkha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.ContentReader.TwelveContentReader;
import com.voltagelab.namazshikkhaapps.DatabaseAsset.DbAsset;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.TwelveStore;
import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.R;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.RecyclerView.TwelveRecyclerView;

import java.util.List;

public class ChitrosohoNamazActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TwelveRecyclerView twelveRecyclerView;
    List<TwelveStore> twelveStores;
    DbAsset database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twelve_button);
        Helper helper = new Helper(this);
        helper.backButtonPressed(this);
        recyclerView=findViewById(R.id.twelveRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        database=new DbAsset(this);

//        getSupportActionBar().setTitle("চিত্রসহ নামাজ");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        twelveStores=database.getChitrosohonamaz();
        twelveRecyclerView=new TwelveRecyclerView(getBaseContext(),twelveStores);
        recyclerView.setAdapter(twelveRecyclerView);

        twelveRecyclerView.setOnItemClickListener(new TwelveRecyclerView.TwelveClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                Intent intent=new Intent(ChitrosohoNamazActivity.this,TwelveContentReader.class);
                intent.putExtra("TWELVE",twelveStores.get(position));
                startActivity(intent);
            }
        });
    }
}
