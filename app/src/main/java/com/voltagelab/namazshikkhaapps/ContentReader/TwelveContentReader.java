package com.voltagelab.namazshikkhaapps.ContentReader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.voltagelab.namazshikkhaapps.Model.TwelveStore;
import com.voltagelab.namazshikkhaapps.R;

public class TwelveContentReader extends AppCompatActivity {


    ImageView first, second, third, fourth, fifth, sixth, seventh, eight, nine, tenth, eleventh, twelve, thirteen, fourtheen, fifteen, sixteen, seventeen;
    TwelveStore twelveStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twelve_content_reader);

        first=findViewById(R.id.firstTwelve);
        second=findViewById(R.id.secondTwelve);
        third=findViewById(R.id.thirdTwelve);
        fourth=findViewById(R.id.fourthTwelve);
        fifth=findViewById(R.id.fifthTwelve);
        sixth=findViewById(R.id.sixthTwelve);
        seventh=findViewById(R.id.seventhTwelve);
        eight=findViewById(R.id.eightTwelve);
        nine=findViewById(R.id.ninthTwelve);
        tenth=findViewById(R.id.tenthTwelve);
        eleventh=findViewById(R.id.elevenTwelve);
        twelve=findViewById(R.id.twelveTwelve);
        thirteen=findViewById(R.id.thirteenTwelve);
        fourtheen=findViewById(R.id.fourteenTwelve);
        fifteen=findViewById(R.id.fiftheenTwelve);
        sixteen=findViewById(R.id.sixteenTwelve);
        seventeen=findViewById(R.id.seventeenTwelve);


        twelveStore=getIntent().getParcelableExtra("TWELVE");

        getSupportActionBar().setTitle(twelveStore.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (twelveStore.getId()==1){
            first.setImageResource(R.drawable.first);

            second.setImageResource(R.drawable.second);
            third.setImageResource(R.drawable.third);
            fourth.setImageResource(R.drawable.fourth);
            fifth.setImageResource(R.drawable.fifth);

            sixth.setImageResource(R.drawable.six);
            seventh.setImageResource(R.drawable.seven);
            eight.setImageResource(R.drawable.eight);
            nine.setImageResource(R.drawable.nine);
            tenth.setImageResource(R.drawable.ten);

            eleventh.setImageResource(R.drawable.eleven);
            twelve.setImageResource(R.drawable.twelve);
            thirteen.setImageResource(R.drawable.thirteen);
            fourtheen.setImageResource(R.drawable.fourtenn);
            fifteen.setImageResource(R.drawable.fiftenn);

            sixteen.setImageResource(R.drawable.sixteen);
            seventeen.setImageResource(R.drawable.seventeen);

        }else {
            first.setImageResource(R.drawable.one);
            second.setImageResource(R.drawable.two);
            third.setImageResource(R.drawable.three);
            fourth.setImageResource(R.drawable.four);
            fifth.setImageResource(R.drawable.five);

        }
    }
}
