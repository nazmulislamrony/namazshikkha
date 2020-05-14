package com.voltagelab.namazshikkhaapps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.voltagelab.namazshikkhaapps.ContentReader.ContentReader;
import com.voltagelab.namazshikkhaapps.DatabaseAsset.DbAsset;
import com.voltagelab.namazshikkhaapps.Model.ContentStore;
import com.voltagelab.namazshikkhaapps.R;
import com.voltagelab.namazshikkhaapps.RecyclerView.RecyclerViewFirst;

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
