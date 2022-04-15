package com.voltagelab.namazshikkhaapps.Activity.namazshikkha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.ContentReader.ContentReader;
import com.voltagelab.namazshikkhaapps.DatabaseAsset.DbAsset;
import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.ContentStore;
import com.voltagelab.namazshikkhaapps.R;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.RecyclerView.RecyclerViewFirst;

import java.util.List;

public class OjuActivity extends AppCompatActivity {


    RecyclerView recyclerViewer;
    RecyclerViewFirst classRecyclerAdapter;
    List<ContentStore> contentStores;
    DbAsset databaseAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_button);
        Helper helper = new Helper(this);
        helper.backButtonPressed(this);




        recyclerViewer = findViewById(R.id.recyclerView);
        recyclerViewer.setLayoutManager(new LinearLayoutManager(this));

        databaseAsset = new DbAsset(this);

        contentStores = databaseAsset.getOju();

        classRecyclerAdapter = new RecyclerViewFirst(getBaseContext(), contentStores);
        recyclerViewer.setAdapter(classRecyclerAdapter);

        //----------------- RecyclerView on item click listener --------

        classRecyclerAdapter.setOnItemClickListener(new RecyclerViewFirst.ClickListener() {
            @Override
            public void OnItemClickListener(int position, View v) {
                if (position != -1) {
                    Intent intent = new Intent(OjuActivity.this, ContentReader.class);
                    intent.putExtra("ContentStore", contentStores.get(position));
                    startActivity(intent);
                }
            }
        });


    }

}
