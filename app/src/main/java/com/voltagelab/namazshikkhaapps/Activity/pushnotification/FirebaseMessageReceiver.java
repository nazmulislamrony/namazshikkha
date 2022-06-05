package com.voltagelab.namazshikkhaapps.Activity.pushnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.voltagelab.namazshikkhaapps.Activity.notificationactivity.NotificationActivity;
import com.voltagelab.namazshikkhaapps.R;

import java.util.Map;

public class FirebaseMessageReceiver
		extends FirebaseMessagingService {

	public static final String FCM_PARAM = "parameters";

	// Override onMessageReceived() method to extract the
	// title and
	// body from the message passed in FCM
	@Override
	public void
	onMessageReceived(RemoteMessage remoteMessage) {
		// First case when notifications are received via
		// data event
		// Here, 'title' and 'message' are the assumed names
		// of JSON
		// attributes. Since here we do not have any data
		// payload, This section is commented out. It is
		// here only for reference purposes.
		/*if(remoteMessage.getData().size()>0){
			showNotification(remoteMessage.getData().get("title"),
						remoteMessage.getData().get("message"));
		}*/

		// Second case when notification payload is
		// received.
		if (remoteMessage.getNotification() != null) {
			// Since the notification is received directly from
			// FCM, the title and the body can be fetched
			// directly as below.
			RemoteMessage.Notification notification = remoteMessage.getNotification();
			Map<String, String> data = remoteMessage.getData();
			showNotification(notification, data);
		}
	}

	// Method to get the custom Design for the display of
	// notification.
	private RemoteViews getCustomDesign(String title,
										String message) {
		RemoteViews remoteViews = new RemoteViews(
				getApplicationContext().getPackageName(),
				R.layout.notification);
		remoteViews.setTextViewText(R.id.title, title);
		remoteViews.setTextViewText(R.id.message, message);
		remoteViews.setImageViewResource(R.id.icon,
				R.drawable.ic_reading_quran);
		return remoteViews;
	}

	// Method to display the notifications
	public void showNotification(RemoteMessage.Notification notification, Map<String, String> data) {
		// Pass the intent to switch to the MainActivity
		Log.d("gggggg","tt0 "+notification.getTitle());
		Log.d("gggggg","tt111 "+data.get(notification));

		Intent intent
				= new Intent(this, NotificationActivity.class);

		intent.putExtra(FCM_PARAM, new NotificationModel(notification.getTitle(),notification.getBody(),notification.getImageUrl(), notification.getIcon()));

		// Assign channel ID
		// Here FLAG_ACTIVITY_CLEAR_TOP flag is set to clear
		// the activities present in the activity stack,
		// on the top of the Activity that is to be launched
		// Pass the intent to PendingIntent to start the
		// next Activity
		PendingIntent pendingIntent
				= PendingIntent.getActivity(
				this, 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);

		// Create a Builder object using NotificationCompat
		// class. This will allow control over all the flags
		NotificationCompat.Builder builder
				= new NotificationCompat
				.Builder(getApplicationContext(),
				getString(R.string.notification_channel_id))
				.setSmallIcon(R.drawable.ic_reading_quran)
				.setAutoCancel(true)
				.setVibrate(new long[]{1000, 1000, 1000,
						1000, 1000})
				.setOnlyAlertOnce(true)
				.setContentIntent(pendingIntent);

		// A customized design for the notification can be
		// set only for Android versions 4.1 and above. Thus
		// condition for the same is checked here.
		if (Build.VERSION.SDK_INT
				>= Build.VERSION_CODES.JELLY_BEAN) {
			builder = builder.setContent(
					getCustomDesign(notification.getTitle(), notification.getBody()));
		} // If Android Version is lower than Jelly Beans,
		// customized layout cannot be used and thus the
		// layout is set as follows
		else {
			builder = builder.setContentTitle(notification.getTitle())
					.setContentText(notification.getBody())
					.setSmallIcon(R.drawable.ic_reading_quran);
		}
		// Create an object of NotificationManager class to
		// notify the
		// user of events that happen in the background.
		NotificationManager notificationManager
				= (NotificationManager) getSystemService(
				Context.NOTIFICATION_SERVICE);
		// Check if the Android Version is greater than Oreo
		if (Build.VERSION.SDK_INT
				>= Build.VERSION_CODES.O) {
			NotificationChannel notificationChannel
					= new NotificationChannel(
					getString(R.string.notification_channel_id), "web_app",
					NotificationManager.IMPORTANCE_HIGH);
			notificationManager.createNotificationChannel(
					notificationChannel);
		}
		notificationManager.notify(0, builder.build());
	}
}
