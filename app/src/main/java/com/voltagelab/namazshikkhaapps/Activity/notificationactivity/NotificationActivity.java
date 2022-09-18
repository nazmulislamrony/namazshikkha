package com.voltagelab.namazshikkhaapps.Activity.notificationactivity;

import static com.voltagelab.namazshikkhaapps.Activity.pushnotification.FirebaseMessageReceiver.FCM_PARAM;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.voltagelab.namazshikkhaapps.Activity.pushnotification.NotificationModel;
import com.voltagelab.namazshikkhaapps.R;

public class NotificationActivity extends AppCompatActivity {
	NotificationModel notificationModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        notificationModel = new NotificationModel();
        TextView titlefromfirebase = findViewById(R.id.txt_title_from_firebase);
        TextView bodyfromfirebase = findViewById(R.id.txt_body_from_firebase);

		 notificationModel = getIntent().getExtras().getParcelable(FCM_PARAM);

		if (notificationModel != null) {

            titlefromfirebase.setText(notificationModel.getTitle());
            bodyfromfirebase.setText(notificationModel.getMessage());
			Log.d("nmmmmmm",": " +", f"+notificationModel.getTitle());

            if (notificationModel.getTitle().contains("সাদকায়ে")) {
                Toast.makeText(this, "sadkaiye", Toast.LENGTH_SHORT).show();
            } else {

            }


        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,NotificationActivity.class));
        super.onBackPressed();
    }
}