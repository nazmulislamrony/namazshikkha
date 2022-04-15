package com.voltagelab.namazshikkhaapps.Activity.namazshikkha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.ContentReader.NinthContentReader;
import com.voltagelab.namazshikkhaapps.DatabaseAsset.DbAsset;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.NinthStore;
import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.R;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.RecyclerView.NinthRecyclerView;

import java.util.List;

public class JanajarNamazActivity extends AppCompatActivity {

    RecyclerView recyclerViewer;
    NinthRecyclerView ninthRecyclerViewAdapter;
    List<NinthStore> ninthStores;
    DbAsset databaseAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninth_button);

        Helper helper = new Helper(this);
        helper.backButtonPressed(this);
//        getSupportActionBar().setTitle("জানাজার নামাজ");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewer=findViewById(R.id.ninth_recyclerView);
        recyclerViewer.setLayoutManager(new LinearLayoutManager(this));

        databaseAsset=new DbAsset(this);
        ninthStores=databaseAsset.getJanajarInfo();

        ninthRecyclerViewAdapter=new NinthRecyclerView(getBaseContext(),ninthStores);
        recyclerViewer.setAdapter(ninthRecyclerViewAdapter);

        ninthRecyclerViewAdapter.setOnItemClickListener(new NinthRecyclerView.NinthClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                Intent intent=new Intent(JanajarNamazActivity.this, NinthContentReader.class);
                intent.putExtra("NINTHBTN",ninthStores.get(position));
                startActivity(intent);
            }
        });
    }
}
