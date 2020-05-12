package com.example.namazshikkha.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.namazshikkha.ContentReader.SixthContentReader;
import com.example.namazshikkha.DatabaseAsset.DbAsset;
import com.example.namazshikkha.Model.SixthStore;
import com.example.namazshikkha.R;
import com.example.namazshikkha.RecyclerView.SixthRecyclerView;

import java.util.List;

public class SixthButton extends AppCompatActivity {

    RecyclerView recyclerViewer;
    SixthRecyclerView sixthRecyclerViewAdapter;
    List<SixthStore> sixthStores;
    DbAsset databaseAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth_button);


        getSupportActionBar().setTitle("নামাজ আদায়ের নিয়ম");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewer=findViewById(R.id.sixth_recyclerView);
        recyclerViewer.setLayoutManager(new LinearLayoutManager(this));

        databaseAsset=new DbAsset(this);
        sixthStores=databaseAsset.sixthStores();

        sixthRecyclerViewAdapter=new SixthRecyclerView(getBaseContext(),sixthStores);
        recyclerViewer.setAdapter(sixthRecyclerViewAdapter);

        sixthRecyclerViewAdapter.setOnItemClickListener(new SixthRecyclerView.SixthClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                Intent intent=new Intent(SixthButton.this, SixthContentReader.class);
                intent.putExtra("SIXTHBTN",sixthStores.get(position));
                startActivity(intent);
            }
        });

    }
}
