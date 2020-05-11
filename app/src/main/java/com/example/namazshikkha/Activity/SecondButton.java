package com.example.namazshikkha.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.namazshikkha.ContentReader.SecondContentReader;
import com.example.namazshikkha.DatabaseAsset.DbAsset;
import com.example.namazshikkha.Model.SecondStore;
import com.example.namazshikkha.R;
import com.example.namazshikkha.RecyclerView.SecondRecyclerView;

import java.util.List;

public class SecondButton extends AppCompatActivity {

    RecyclerView recyclerViewer;
    SecondRecyclerView secondRecyclerAdapter;
    List<SecondStore> secondStores;
    DbAsset databaseAsset;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_button);
        toolbar=findViewById(R.id.namToolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("গোসল");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewer=findViewById(R.id.recyclerView);
        recyclerViewer.setLayoutManager(new LinearLayoutManager(this));

        databaseAsset=new DbAsset(this);
        secondStores=databaseAsset.secondStor();

        secondRecyclerAdapter=new SecondRecyclerView(getBaseContext(),secondStores);
        recyclerViewer.setAdapter(secondRecyclerAdapter);

        //  ---------- Recycler on item click listener --------------

        secondRecyclerAdapter.setOnItemClickListener(new SecondRecyclerView.SecondClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {

                if (position!=-1){
                    Intent intent=new Intent(SecondButton.this, SecondContentReader.class);
                    intent.putExtra("secondbtn",secondStores.get(position));
                    startActivity(intent);
                }

            }
        });


    }
}
