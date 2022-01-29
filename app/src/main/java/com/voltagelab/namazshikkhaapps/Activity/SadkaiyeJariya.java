package com.voltagelab.namazshikkhaapps.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.voltagelab.namazshikkhaapps.R;

public class SadkaiyeJariya extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sadkaiye_jariya);
        getSupportActionBar().setTitle("আর্থিক অনুদান দিন");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button btnSadkaiyeJariya = findViewById(R.id.btn_sadkaiye_jaria);

        btnSadkaiyeJariya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp();
            }
        });

    }

    public void showPopUp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();

        //this is custom dialog
        //custom_popup_dialog contains textview only
        View customView = layoutInflater.inflate(R.layout.sadkaiye_jariya_dialog, null);
        // reference the textview of custom_popup_dialog
        ImageView bkashnumber =  customView.findViewById(R.id.img_bkash_number_copy);
        ImageView rocketnumber =  customView.findViewById(R.id.img_rocket_number_copy);
        ImageView nagadnumber =  customView.findViewById(R.id.img_nagad_number_copy);

        bkashnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("copied:", "01713509349");
                clipboard.setPrimaryClip(clip);
                Toast.makeText(SadkaiyeJariya.this, "01713509349 is Copied", Toast.LENGTH_SHORT).show();
            }
        });

        rocketnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("copied:", "017135093490");
                clipboard.setPrimaryClip(clip);
                Toast.makeText(SadkaiyeJariya.this, "017135093490 is Copied", Toast.LENGTH_SHORT).show();
            }
        });

        nagadnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("copied:", "01713509349");
                clipboard.setPrimaryClip(clip);
                Toast.makeText(SadkaiyeJariya.this, "01713509349 is Copied", Toast.LENGTH_SHORT).show();
            }
        });


        builder.setView(customView);
        builder.create();
        builder.show();

    }
}