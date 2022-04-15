package com.voltagelab.namazshikkhaapps.Activity.namazshikkha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.ContentReader.TenthContentReader;
import com.voltagelab.namazshikkhaapps.DatabaseAsset.DbAsset;
import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.TenthStore;
import com.voltagelab.namazshikkhaapps.R;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.RecyclerView.TenthRecyclerView;

import java.util.List;

public class SurahActivityNamazShikkha extends AppCompatActivity {

    RecyclerView recyclerView;
    List<TenthStore> tenthStores;
    TenthRecyclerView tenthRecyclerViewAdapter;
    DbAsset databaseAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenth_button);

        Helper helper = new Helper(this);
        helper.backButtonPressed(this);

        recyclerView=findViewById(R.id.tenthRecyclerview);
        databaseAsset=new DbAsset(this);
        tenthStores=databaseAsset.getSurah();



//        getSupportActionBar().setTitle("সূরা");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tenthRecyclerViewAdapter=new TenthRecyclerView(getBaseContext(),tenthStores);

        recyclerView.setAdapter(tenthRecyclerViewAdapter);

        tenthRecyclerViewAdapter.setOnItemClickListener(new TenthRecyclerView.TenthClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                if (position!=-1){
                    Intent intent=new Intent(SurahActivityNamazShikkha.this, TenthContentReader.class);
                    intent.putExtra("TENTHBTN",tenthStores.get(position));
                    startActivity(intent);
                }
            }
        });


//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        tenthRecyclerViewAdapter=new TenthRecyclerView(getBaseContext(),tenthStores);
//
//        recyclerView.setAdapter(tenthRecyclerViewAdapter);
//
//        tenthRecyclerViewAdapter.setOnItemClickListener(new TenthRecyclerView.TenthClickListener() {
//            @Override
//            public void onItemClickListener(int position, View v) {
//                if (position!=-1){
//                    Intent intent=new Intent(this, TenthContentReader.class);
//                    intent.putExtra("TENTHBTN",tenthStores.get(position));
//                    startActivity(intent);
//                }
//            }
//        });

    }
}
