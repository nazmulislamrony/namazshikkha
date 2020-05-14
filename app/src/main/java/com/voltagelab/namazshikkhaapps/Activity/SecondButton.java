package com.voltagelab.namazshikkhaapps.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.voltagelab.namazshikkhaapps.ContentReader.SecondContentReader;
import com.voltagelab.namazshikkhaapps.DatabaseAsset.DbAsset;
import com.voltagelab.namazshikkhaapps.Model.SecondStore;
import com.voltagelab.namazshikkhaapps.R;
import com.voltagelab.namazshikkhaapps.RecyclerView.SecondRecyclerView;

import java.util.List;

public class SecondButton extends AppCompatActivity {

    RecyclerView recyclerViewer;
    SecondRecyclerView secondRecyclerAdapter;
    List<SecondStore> secondStores;
    DbAsset databaseAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_button);

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
