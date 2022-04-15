package com.voltagelab.namazshikkhaapps.Activity.namazshikkha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.ContentReader.SecondContentReader;
import com.voltagelab.namazshikkhaapps.DatabaseAsset.DbAsset;
import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model.SecondStore;
import com.voltagelab.namazshikkhaapps.R;
import com.voltagelab.namazshikkhaapps.Activity.namazshikkha.RecyclerView.SecondRecyclerView;

import java.util.List;

public class GosolActivity extends AppCompatActivity {

    RecyclerView recyclerViewer;
    SecondRecyclerView secondRecyclerAdapter;
    List<SecondStore> secondStores;
    DbAsset databaseAsset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_button);

        Helper helper = new Helper(this);
        helper.backButtonPressed(this);


        recyclerViewer=findViewById(R.id.recyclerView);
        recyclerViewer.setLayoutManager(new LinearLayoutManager(this));

        databaseAsset=new DbAsset(this);
        secondStores=databaseAsset.getGosolInfo();

        secondRecyclerAdapter=new SecondRecyclerView(getBaseContext(),secondStores);
        recyclerViewer.setAdapter(secondRecyclerAdapter);

        //  ---------- Recycler on item click listener --------------

        secondRecyclerAdapter.setOnItemClickListener(new SecondRecyclerView.SecondClickListener() {
            @Override
            public void onItemClickListener(int position, View v) {

                if (position!=-1){
                    Intent intent=new Intent(GosolActivity.this, SecondContentReader.class);
                    intent.putExtra("secondbtn",secondStores.get(position));
                    startActivity(intent);
                }

            }
        });


    }
}
