package com.example.namazshikkha.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.namazshikkha.ContentReader.ContentReader;
import com.example.namazshikkha.DatabaseAsset.DbAsset;
import com.example.namazshikkha.Model.ContentStore;
import com.example.namazshikkha.R;
import com.example.namazshikkha.RecyclerView.RecyclerViewFirst;

import java.util.List;

public class FirstButton extends AppCompatActivity {


    RecyclerView recyclerViewer;
    RecyclerViewFirst classRecyclerAdapter;
    List<ContentStore> contentStores;
    DbAsset databaseAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_button);


        getSupportActionBar().setTitle("ওযু");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewer=findViewById(R.id.recyclerView);
        recyclerViewer.setLayoutManager(new LinearLayoutManager(this));

        databaseAsset=new DbAsset(this);

        contentStores=databaseAsset.getData();

        classRecyclerAdapter=new RecyclerViewFirst(getBaseContext(),contentStores);
        recyclerViewer.setAdapter(classRecyclerAdapter);

        //----------------- RecyclerView on item click listener --------

        classRecyclerAdapter.setOnItemClickListener(new RecyclerViewFirst.ClickListener() {
            @Override
            public void OnItemClickListener(int position, View v) {
                if (position!=-1){
                    Intent intent=new Intent(FirstButton.this, ContentReader.class);
                    intent.putExtra("ContentStore",contentStores.get(position));
                    startActivity(intent);
                }
            }
        });



    }

}
