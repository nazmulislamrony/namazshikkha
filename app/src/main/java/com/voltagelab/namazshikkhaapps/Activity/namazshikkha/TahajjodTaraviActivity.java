package com.voltagelab.namazshikkhaapps.Activity.namazshikkha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.ContentReader.EighthContentReader;
import com.voltagelab.namazshikkhaapps.DatabaseAsset.DbAsset;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.EighthStore;
import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.R;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.RecyclerView.EighthRecyclerView;

import java.util.List;

public class TahajjodTaraviActivity extends AppCompatActivity {

    RecyclerView recyclerViewer;
    EighthRecyclerView eighthRecyclerViewAdapter;
    List<EighthStore> eighthStores;
    DbAsset databaseAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eighth_button);
        Helper helper = new Helper(this);
        helper.backButtonPressed(this);

//        getSupportActionBar().setTitle("তাহাজ্জুদ ও তারাবি");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewer=findViewById(R.id.eighth_recyclerView);
        recyclerViewer.setLayoutManager(new LinearLayoutManager(this));

        databaseAsset=new DbAsset(this);
        eighthStores=databaseAsset.getTahajjodtarabi();

        eighthRecyclerViewAdapter=new EighthRecyclerView(getBaseContext(),eighthStores);
        recyclerViewer.setAdapter(eighthRecyclerViewAdapter);

        eighthRecyclerViewAdapter.setOnItemClickListener(new EighthRecyclerView.EighthClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                Intent intent=new Intent(TahajjodTaraviActivity.this, EighthContentReader.class);
                intent.putExtra("EIGHTHBTN",eighthStores.get(position));
                startActivity(intent);
            }
        });
    }
}
