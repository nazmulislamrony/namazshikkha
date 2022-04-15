package com.voltagelab.namazshikkhaapps.Activity.namazshikkha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.ContentReader.ThirdContentReader;
import com.voltagelab.namazshikkhaapps.DatabaseAsset.DbAsset;
import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.ThirdStore;
import com.voltagelab.namazshikkhaapps.R;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.RecyclerView.ThirdRecyclerView;

import java.util.List;

public class TayamommumActivity extends AppCompatActivity {

    RecyclerView recyclerViewer;
    ThirdRecyclerView thirdRecyclerViewAdapter;
    List<ThirdStore> thirdStores;
    DbAsset databaseAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_button);
        Helper helper = new Helper(this);
        helper.backButtonPressed(this);


//        getSupportActionBar().setTitle("তায়াম্মুম");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewer=findViewById(R.id.thirdRecyclerView);
        recyclerViewer.setLayoutManager(new LinearLayoutManager(this));

        databaseAsset=new DbAsset(this);
        thirdStores=databaseAsset.getTayammum();

        thirdRecyclerViewAdapter=new ThirdRecyclerView(getBaseContext(),thirdStores);
        recyclerViewer.setAdapter(thirdRecyclerViewAdapter);

        //  ---------- Recycler on item click listener --------------

        thirdRecyclerViewAdapter.setOnItemClickListener(new ThirdRecyclerView.ThirdClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {

                if (position!=-1){
                    Intent intent=new Intent(TayamommumActivity.this, ThirdContentReader.class);
                    intent.putExtra("thirdbtn",thirdStores.get(position));

                    startActivity(intent);
                }

            }
        });
    }
}
