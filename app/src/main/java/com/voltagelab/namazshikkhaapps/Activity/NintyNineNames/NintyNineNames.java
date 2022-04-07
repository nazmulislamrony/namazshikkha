package com.voltagelab.namazshikkhaapps.Activity.NintyNineNames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.voltagelab.namazshikkhaapps.DatabaseAsset.DbAsset;
import com.voltagelab.namazshikkhaapps.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class NintyNineNames extends AppCompatActivity {

    NintyNineAdapters nintyNineAdapters;
    RecyclerView rvnintynine;

    ArrayList<NintyNimesModel> nintyninelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninty_nine_names);
        rvnintynine = findViewById(R.id.rvnintynine);
        nintyninelist = new ArrayList<>();
        DbAsset databaseAsset=new DbAsset(this);
        nintyninelist = databaseAsset.getAllNames();

        nintyNineAdapters = new NintyNineAdapters(nintyninelist, this);

        rvnintynine.setAdapter(nintyNineAdapters);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvnintynine.setLayoutManager(mLayoutManager);
    }
}