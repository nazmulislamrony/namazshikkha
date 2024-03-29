package com.voltagelab.namazshikkhaapps.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.voltagelab.namazshikkhaapps.ContentReader.EighthContentReader;
import com.voltagelab.namazshikkhaapps.DatabaseAsset.DbAsset;
import com.voltagelab.namazshikkhaapps.Model.EighthStore;
import com.voltagelab.namazshikkhaapps.R;
import com.voltagelab.namazshikkhaapps.RecyclerView.EighthRecyclerView;

import java.util.List;

public class EighthButton extends AppCompatActivity {

    RecyclerView recyclerViewer;
    EighthRecyclerView eighthRecyclerViewAdapter;
    List<EighthStore> eighthStores;
    DbAsset databaseAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eighth_button);


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
