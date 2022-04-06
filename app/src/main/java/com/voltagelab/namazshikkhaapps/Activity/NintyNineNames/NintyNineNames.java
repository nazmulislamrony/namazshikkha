package com.voltagelab.namazshikkhaapps.Activity.NintyNineNames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninty_nine_names);
        rvnintynine = findViewById(R.id.rvnintynine);

        getAllJsonData();

    }

    private void getAllJsonData() {
        ArrayList<NintyNimesModel> nintyNineList = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("results");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d("Details-->", jo_inside.getString("id"));
                String formula_value = jo_inside.getString("id");
                String url_value = jo_inside.getString("eng_name");

                String id = jo_inside.getString("id");
                String icon = jo_inside.getString("icon");
                String eng_name = jo_inside.getString("eng_name");
                String bn_name = jo_inside.getString("bn_name");
                String bn_meaning = jo_inside.getString("bn_meaning");
                String eng_meaning = jo_inside.getString("eng_meaning");
                String explanation = jo_inside.getString("explanation");
                String fazilat = jo_inside.getString("fazilat");
                //Add your values in your `ArrayList` as below:
                NintyNimesModel nintyNimesModel = new NintyNimesModel(id,icon, eng_name, bn_name,bn_meaning,eng_meaning,explanation,fazilat);
                nintyNineList.add(nintyNimesModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        nintyNineAdapters = new NintyNineAdapters(nintyNineList, this);

        rvnintynine.setAdapter(nintyNineAdapters);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvnintynine.setLayoutManager(mLayoutManager);

        Log.d("testdata","data");
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("99names.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}