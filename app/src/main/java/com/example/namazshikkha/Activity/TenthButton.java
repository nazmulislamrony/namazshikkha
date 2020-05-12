package com.example.namazshikkha.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.namazshikkha.ContentReader.TenthContentReader;
import com.example.namazshikkha.DatabaseAsset.DbAsset;
import com.example.namazshikkha.Model.TenthStore;
import com.example.namazshikkha.R;
import com.example.namazshikkha.RecyclerView.TenthRecyclerView;

import java.util.List;

public class TenthButton extends AppCompatActivity {

    RecyclerView recyclerView;
    List<TenthStore> tenthStores;
    TenthRecyclerView tenthRecyclerViewAdapter;
    DbAsset databaseAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenth_button);

        recyclerView=findViewById(R.id.tenthRecyclerview);
        databaseAsset=new DbAsset(this);
        tenthStores=databaseAsset.tenthStores();

        getSupportActionBar().setTitle("সূরা");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tenthRecyclerViewAdapter=new TenthRecyclerView(getBaseContext(),tenthStores);

        recyclerView.setAdapter(tenthRecyclerViewAdapter);

        tenthRecyclerViewAdapter.setOnItemClickListener(new TenthRecyclerView.TenthClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {
                if (position!=-1){
                    Intent intent=new Intent(TenthButton.this, TenthContentReader.class);
                    intent.putExtra("TENTHBTN",tenthStores.get(position));
                    startActivity(intent);
                }
            }
        });



    }
}
