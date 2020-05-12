package com.example.namazshikkha.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.namazshikkha.ContentReader.SeventhContentReader;
import com.example.namazshikkha.DatabaseAsset.DbAsset;
import com.example.namazshikkha.Model.SeventhStore;
import com.example.namazshikkha.Model.SixthStore;
import com.example.namazshikkha.R;
import com.example.namazshikkha.RecyclerView.SeventhRecyclerView;
import com.example.namazshikkha.RecyclerView.SixthRecyclerView;

import java.util.List;

public class SeventhButton extends AppCompatActivity {

    RecyclerView recyclerViewer;
    SeventhRecyclerView seventhRecyclerViewAdapter;
    List<SeventhStore> seventhStores;
    DbAsset databaseAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh_button);


        getSupportActionBar().setTitle("ঈদের নামাজ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewer=findViewById(R.id.seventh_recyclerView);
        recyclerViewer.setLayoutManager(new LinearLayoutManager(this));

        databaseAsset=new DbAsset(this);
        seventhStores=databaseAsset.seventhStores();

        seventhRecyclerViewAdapter=new SeventhRecyclerView(getBaseContext(),seventhStores);
        recyclerViewer.setAdapter(seventhRecyclerViewAdapter);

        seventhRecyclerViewAdapter.setOnItemClickListener(new SeventhRecyclerView.SeventhClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                Intent intent=new Intent(SeventhButton.this, SeventhContentReader.class);
                intent.putExtra("SEVENTHBTN",seventhStores.get(position));
                startActivity(intent);
            }
        });
    }
}
