package com.voltagelab.namazshikkhaapps.Activity.Tasbih;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.voltagelab.namazshikkhaapps.R;

public class TasbihActivity extends AppCompatActivity {

    Button btnReset;
    TextView tasbihCount;

    public int nilai = 0;
    public int reset = 0;
    public int nilai1 =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasbih);

        btnReset = findViewById(R.id.btn_reset);
        tasbihCount = findViewById(R.id.tasbih_count);

        tasbihActivity();
    }

    private void tasbihActivity() {
        final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        final RelativeLayout btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nilai++;
                nilai1++;
                if (nilai == 33 || nilai==66 || nilai== 99){
                    vibrator.vibrate(600);
//                    btn_start.setTextColor(Color.RED);
                }else {
                    vibrator.vibrate(20);
//                    btn_start.setTextColor(Color.BLUE);
                }
                if (nilai > 99){
                    nilai = 0;


                }
                String nilai_string = String.valueOf(nilai);
//                btn_start.setText(nilai_string);
                String nilai1_string = String.valueOf(nilai1);
                tasbihCount.setText(nilai1_string);
            }
        });

        Button btn_reset = (Button) findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nilai = 0; reset++;
                nilai1 = 0;
                String nilai_string = String.valueOf(nilai);
//                btn_start.setText(nilai_string);
                String nilai1_string = String.valueOf(nilai1);
                tasbihCount.setText(nilai1_string);

            }

        });
    }
}