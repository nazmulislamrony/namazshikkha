package com.voltagelab.namazshikkhaapps.Activity.namazshikkha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.ContentReader.SeventhContentReader;
import com.voltagelab.namazshikkhaapps.DatabaseAsset.DbAsset;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.SeventhStore;
import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.R;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.RecyclerView.SeventhRecyclerView;

import java.util.List;

public class EidNamazActivity extends AppCompatActivity {

    RecyclerView recyclerViewer;
    SeventhRecyclerView seventhRecyclerViewAdapter;
    List<SeventhStore> seventhStores;
    DbAsset databaseAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh_button);
        Helper helper = new Helper(this);
        helper.backButtonPressed(this);

//        getSupportActionBar().setTitle("ঈদের নামাজ");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewer=findViewById(R.id.seventh_recyclerView);
        recyclerViewer.setLayoutManager(new LinearLayoutManager(this));

        databaseAsset=new DbAsset(this);
        seventhStores=databaseAsset.getEidNamaz();

        seventhRecyclerViewAdapter=new SeventhRecyclerView(getBaseContext(),seventhStores);
        recyclerViewer.setAdapter(seventhRecyclerViewAdapter);

        seventhRecyclerViewAdapter.setOnItemClickListener(new SeventhRecyclerView.SeventhClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                Intent intent=new Intent(EidNamazActivity.this, SeventhContentReader.class);
                intent.putExtra("SEVENTHBTN",seventhStores.get(position));
                startActivity(intent);
            }
        });
    }
}
