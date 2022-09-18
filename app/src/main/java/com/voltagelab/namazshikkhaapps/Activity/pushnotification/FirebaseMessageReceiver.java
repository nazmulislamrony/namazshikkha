package com.voltagelab.namazshikkhaapps.Activity.pushnotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.voltagelab.namazshikkhaapps.Activity.SadkaiyeJariya;
import com.voltagelab.namazshikkhaapps.Activity.notificationactivity.NotificationActivity;
import com.voltagelab.namazshikkhaapps.R;

import java.io.IOException;
import java.net.URL;
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
//			showNotification(notification, data);






			sendsNotification(notification,data);
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


		Intent intent
				= new Intent(this, NotificationActivity.class);

		intent.putExtra(FCM_PARAM, new NotificationModel(notification.getTitle(),notification.getBody(),notification.getImageUrl(), notification.getIcon()));

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addNextIntentWithParentStack(intent);
		// Assign channel ID
		// Here FLAG_ACTIVITY_CLEAR_TOP flag is set to clear
		// the activities present in the activity stack,
		// on the top of the Activity that is to be launched
		// Pass the intent to PendingIntent to start the
		// next Activity
		PendingIntent pendingIntent =
				stackBuilder.getPendingIntent(0,
						PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

//		PendingIntent pendingIntent = null;
//		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
//			pendingIntent = PendingIntent.getActivity
//					(this, 0, intent, PendingIntent.FLAG_MUTABLE);
//		}
//		else
//		{
//			pendingIntent = PendingIntent.getActivity
//					(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//		}

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


	private void sendsNotification(RemoteMessage.Notification notification, Map<String, String> data) {
		Bundle bundle = new Bundle();
		bundle.putString(FCM_PARAM, data.get(FCM_PARAM));

		Intent intent = new Intent(this, SadkaiyeJariya.class);
		intent.putExtras(bundle);

		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, getString(R.string.notification_channel_id))
				.setContentTitle(notification.getTitle())
				.setContentText(notification.getBody())
				.setAutoCancel(true)
				.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
				//.setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.win))
				.setContentIntent(pendingIntent)
				.setContentInfo("Hello")
				.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//				.setColor(getColor(R.color.colorAccent))
				.setLights(Color.RED, 1000, 300)
				.setDefaults(Notification.DEFAULT_VIBRATE)
				.setSmallIcon(R.drawable.ic_reading_quran);

		try {
			String picture = data.get(FCM_PARAM);
			if (picture != null && !"".equals(picture)) {
				URL url = new URL(picture);
				Bitmap bigPicture = BitmapFactory.decodeStream(url.openConnection().getInputStream());
				notificationBuilder.setStyle(
						new NotificationCompat.BigPictureStyle().bigPicture(bigPicture).setSummaryText(notification.getBody())
				);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			NotificationChannel channel = new NotificationChannel(
					getString(R.string.notification_channel_id), getString(R.string.notification_channel_id), NotificationManager.IMPORTANCE_DEFAULT
			);
			channel.setShowBadge(true);
			channel.canShowBadge();
			channel.enableLights(true);
			channel.setLightColor(Color.RED);
			channel.enableVibration(true);
			channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500});

			assert notificationManager != null;
			notificationManager.createNotificationChannel(channel);
		}

		assert notificationManager != null;
		notificationManager.notify(0, notificationBuilder.build());
	}

}
