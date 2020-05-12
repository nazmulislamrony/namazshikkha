package com.example.namazshikkha.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.namazshikkha.ContentReader.SecondContentReader;
import com.example.namazshikkha.ContentReader.ThirdContentReader;
import com.example.namazshikkha.DatabaseAsset.DbAsset;
import com.example.namazshikkha.Model.SecondStore;
import com.example.namazshikkha.Model.ThirdStore;
import com.example.namazshikkha.R;
import com.example.namazshikkha.RecyclerView.SecondRecyclerView;
import com.example.namazshikkha.RecyclerView.ThirdRecyclerView;

import java.util.List;

public class ThirdButton extends AppCompatActivity {

    RecyclerView recyclerViewer;
    ThirdRecyclerView thirdRecyclerViewAdapter;
    List<ThirdStore> thirdStores;
    DbAsset databaseAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_button);

        getSupportActionBar().setTitle("তায়াম্মুম");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewer=findViewById(R.id.thirdRecyclerView);
        recyclerViewer.setLayoutManager(new LinearLayoutManager(this));

        databaseAsset=new DbAsset(this);
        thirdStores=databaseAsset.thirdStore();

        thirdRecyclerViewAdapter=new ThirdRecyclerView(getBaseContext(),thirdStores);
        recyclerViewer.setAdapter(thirdRecyclerViewAdapter);

        //  ---------- Recycler on item click listener --------------

        thirdRecyclerViewAdapter.setOnItemClickListener(new ThirdRecyclerView.ThirdClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {

                if (position!=-1){
                    Intent intent=new Intent(ThirdButton.this, ThirdContentReader.class);
                    intent.putExtra("thirdbtn",thirdStores.get(position));

                    startActivity(intent);
                }

            }
        });
    }
}
