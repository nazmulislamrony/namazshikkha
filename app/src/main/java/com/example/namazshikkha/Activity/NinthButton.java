package com.example.namazshikkha.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.namazshikkha.ContentReader.NinthContentReader;
import com.example.namazshikkha.DatabaseAsset.DbAsset;
import com.example.namazshikkha.Model.NinthStore;
import com.example.namazshikkha.R;
import com.example.namazshikkha.RecyclerView.NinthRecyclerView;

import java.util.List;

public class NinthButton extends AppCompatActivity {

    RecyclerView recyclerViewer;
    NinthRecyclerView ninthRecyclerViewAdapter;
    List<NinthStore> ninthStores;
    DbAsset databaseAsset;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninth_button);

        toolbar=findViewById(R.id.ninth_toolbar);

        setSupportActionBar(toolbar);
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
