package com.voltagelab.namazshikkhaapps.Activity.namazshikkha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.ContentReader.FourthContentReader;
import com.voltagelab.namazshikkhaapps.DatabaseAsset.DbAsset;
import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.FourthStore;
import com.voltagelab.namazshikkhaapps.R;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.RecyclerView.FourthRecyclerView;

import java.util.List;

public class NamazerOwaktoActivity extends AppCompatActivity {

    RecyclerView recyclerViewer;
    FourthRecyclerView fourthRecyclerViewAdapter;
    List<FourthStore> fourthStores;
    DbAsset databaseAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namazer_owakto_rakat);

        Helper helper = new Helper(this);
        helper.backButtonPressed(this);

//        getSupportActionBar().setTitle("নামাজের ওয়াক্ত ও রাকাআত");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewer=findViewById(R.id.fourthrecyclerview);
        recyclerViewer.setLayoutManager(new LinearLayoutManager(this));

        databaseAsset=new DbAsset(this);
        fourthStores=databaseAsset.getNamazerOwakto();

        fourthRecyclerViewAdapter=new FourthRecyclerView(getBaseContext(),fourthStores);
        recyclerViewer.setAdapter(fourthRecyclerViewAdapter);

        //  ---------- Recycler on item click listener --------------

        fourthRecyclerViewAdapter.setOnItemClickListener(new FourthRecyclerView.FourthClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                if (position!=-1){
                    Intent intent=new Intent(NamazerOwaktoActivity.this, FourthContentReader.class);
                    intent.putExtra("FOURTHBTN",fourthStores.get(position));
                    startActivity(intent);
                }
            }
        });
    }
}
