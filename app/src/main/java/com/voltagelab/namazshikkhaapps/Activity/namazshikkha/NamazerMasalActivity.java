package com.voltagelab.namazshikkhaapps.Activity.namazshikkha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.ContentReader.FifthContentReader;
import com.voltagelab.namazshikkhaapps.DatabaseAsset.DbAsset;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.FifthStore;
import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.R;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.RecyclerView.FifthRecyclerView;

import java.util.List;

public class NamazerMasalActivity extends AppCompatActivity {

    RecyclerView recyclerViewer;
    FifthRecyclerView fifthRecyclerViewAdapter;
    List<FifthStore> fifthStores;
    DbAsset databaseAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_button);

        recyclerViewer=findViewById(R.id.fifth_recyclerView);
        Helper helper = new Helper(this);
        helper.backButtonPressed(this);
//        getSupportActionBar().setTitle("নামাজের মাসাআলা");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewer.setLayoutManager(new LinearLayoutManager(this));

        databaseAsset=new DbAsset(this);
        fifthStores=databaseAsset.getNamazerMasal();

        fifthRecyclerViewAdapter=new FifthRecyclerView(getBaseContext(),fifthStores);
        recyclerViewer.setAdapter(fifthRecyclerViewAdapter);


        fifthRecyclerViewAdapter.setOnItemClickListener(new FifthRecyclerView.FifthClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                Intent intent=new Intent(NamazerMasalActivity.this, FifthContentReader.class);
                intent.putExtra("FIFTHBTN", fifthStores.get(position));
                startActivity(intent);
            }
        });
    }
}
