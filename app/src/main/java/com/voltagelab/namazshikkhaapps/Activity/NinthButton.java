package com.voltagelab.namazshikkhaapps.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.voltagelab.namazshikkhaapps.ContentReader.NinthContentReader;
import com.voltagelab.namazshikkhaapps.DatabaseAsset.DbAsset;
import com.voltagelab.namazshikkhaapps.Model.NinthStore;
import com.voltagelab.namazshikkhaapps.R;
import com.voltagelab.namazshikkhaapps.RecyclerView.NinthRecyclerView;

import java.util.List;

public class NinthButton extends AppCompatActivity {

    RecyclerView recyclerViewer;
    NinthRecyclerView ninthRecyclerViewAdapter;
    List<NinthStore> ninthStores;
    DbAsset databaseAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninth_button);


        getSupportActionBar().setTitle("জানাজার নামাজ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewer=findViewById(R.id.ninth_recyclerView);
        recyclerViewer.setLayoutManager(new LinearLayoutManager(this));

        databaseAsset=new DbAsset(this);
        ninthStores=databaseAsset.ninthStores();

        ninthRecyclerViewAdapter=new NinthRecyclerView(getBaseContext(),ninthStores);
        recyclerViewer.setAdapter(ninthRecyclerViewAdapter);

        ninthRecyclerViewAdapter.setOnItemClickListener(new NinthRecyclerView.NinthClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                Intent intent=new Intent(NinthButton.this, NinthContentReader.class);
                intent.putExtra("NINTHBTN",ninthStores.get(position));
                startActivity(intent);
            }
        });
    }
}
