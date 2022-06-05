package com.voltagelab.namazshikkhaapps.Activity.notificationactivity;

import static com.voltagelab.namazshikkhaapps.Activity.pushnotification.FirebaseMessageReceiver.FCM_PARAM;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.voltagelab.namazshikkhaapps.Activity.pushnotification.NotificationModel;
import com.voltagelab.namazshikkhaapps.R;

public class NotificationActivity extends AppCompatActivity {
	NotificationModel notificationModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        TextView txt = findViewById(R.id.textView);

		 notificationModel = getIntent().getExtras().getParcelable(FCM_PARAM);

		if (notificationModel != null) {

            txt.setText(notificationModel.getTitle());
			Log.d("nmmmmmm",": " +", f"+notificationModel.getTitle());
        }
    }
}