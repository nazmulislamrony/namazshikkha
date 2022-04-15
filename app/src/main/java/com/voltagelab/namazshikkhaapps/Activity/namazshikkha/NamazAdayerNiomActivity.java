package com.voltagelab.namazshikkhaapps.Activity.namazshikkha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.ContentReader.SixthContentReader;
import com.voltagelab.namazshikkhaapps.DatabaseAsset.DbAsset;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.SixthStore;
import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.R;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.RecyclerView.SixthRecyclerView;

import java.util.List;

public class NamazAdayerNiomActivity extends AppCompatActivity {

    RecyclerView recyclerViewer;
    SixthRecyclerView sixthRecyclerViewAdapter;
    List<SixthStore> sixthStores;
    DbAsset databaseAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth_button);
        Helper helper = new Helper(this);
        helper.backButtonPressed(this);
//
//        getSupportActionBar().setTitle("নামাজ আদায়ের নিয়ম");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewer=findViewById(R.id.sixth_recyclerView);
        recyclerViewer.setLayoutManager(new LinearLayoutManager(this));

        databaseAsset=new DbAsset(this);
        sixthStores=databaseAsset.getNamazAdayerNiom();

        sixthRecyclerViewAdapter=new SixthRecyclerView(getBaseContext(),sixthStores);
        recyclerViewer.setAdapter(sixthRecyclerViewAdapter);

        sixthRecyclerViewAdapter.setOnItemClickListener(new SixthRecyclerView.SixthClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                Intent intent=new Intent(NamazAdayerNiomActivity.this, SixthContentReader.class);
                intent.putExtra("SIXTHBTN",sixthStores.get(position));
                startActivity(intent);
            }
        });

    }
}
