package com.example.namazshikkha.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.namazshikkha.ContentReader.FifthContentReader;
import com.example.namazshikkha.DatabaseAsset.DbAsset;
import com.example.namazshikkha.Model.FifthStore;
import com.example.namazshikkha.Model.FourthStore;
import com.example.namazshikkha.R;
import com.example.namazshikkha.RecyclerView.FifthRecyclerView;
import com.example.namazshikkha.RecyclerView.FourthRecyclerView;

import java.util.List;

public class FifthButton extends AppCompatActivity {

    RecyclerView recyclerViewer;
    FifthRecyclerView fifthRecyclerViewAdapter;
    List<FifthStore> fifthStores;
    DbAsset databaseAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_button);

        recyclerViewer=findViewById(R.id.fifth_recyclerView);

        getSupportActionBar().setTitle("নামাজের মাসাআলা");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewer.setLayoutManager(new LinearLayoutManager(this));

        databaseAsset=new DbAsset(this);
        fifthStores=databaseAsset.fifthStores();

        fifthRecyclerViewAdapter=new FifthRecyclerView(getBaseContext(),fifthStores);
        recyclerViewer.setAdapter(fifthRecyclerViewAdapter);


        fifthRecyclerViewAdapter.setOnItemClickListener(new FifthRecyclerView.FifthClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                Intent intent=new Intent(FifthButton.this, FifthContentReader.class);
                intent.putExtra("FIFTHBTN", fifthStores.get(position));
                startActivity(intent);
            }
        });
    }
}
