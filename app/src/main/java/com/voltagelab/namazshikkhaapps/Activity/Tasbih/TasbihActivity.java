package com.voltagelab.namazshikkhaapps.Activity.Tasbih;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.voltagelab.namazshikkhaapps.Helper;
import com.voltagelab.namazshikkhaapps.R;

public class TasbihActivity extends AppCompatActivity {

    Button btnReset;
    Button imgVibrator;
    TextView tasbihCount;
    boolean isVibrator;


    public int nilai = 0;
    public int reset = 0;
    public int nilai1 =0;

    Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasbih);
        Helper helper = new Helper(this);
        helper.backButtonPressed(this);

        btnReset = findViewById(R.id.btn_reset);
        tasbihCount = findViewById(R.id.tasbih_count);
        imgVibrator = findViewById(R.id.img_vibrator);

        imgVibrator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isVibrator){
                    isVibrator=true;
                    imgVibrator.setBackgroundResource(R.drawable.vibrate_white);
                }else {
                    isVibrator=false;
                    imgVibrator.setBackgroundResource(R.drawable.vibrate);
                }
            }
        });

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
                if (nilai == 50 || nilai==100 || nilai== 200){
                    vibrator.vibrate(600);
                }else {
                    vibrator.vibrate(20);
                }
                if (nilai > 99){
                    nilai = 0;
                }
                if (isVibrator) {
                    vibrator.vibrate(60);
                }
                String nilai_string = String.valueOf(nilai);
                Log.d("isvibrator_tring","" +isVibrator);
//                btn_start.setText(nilai_string);
                String nilai1_string = String.valueOf(nilai1);
                tasbihCount.setText(helper.getDigitBanglaFromEnglish(nilai1_string));

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

                tasbihCount.setText(helper.getDigitBanglaFromEnglish(nilai1_string));

            }

        });
    }
}