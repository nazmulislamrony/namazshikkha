package com.voltagelab.namazshikkhaapps.Activity.pushnotification;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class NotificationModel implements Parcelable {
    String title;
    String message;
    Uri image_url;
    String icon;


    public NotificationModel(String title, String message, Uri image_url, String icon) {
        this.title = title;
        this.message = message;
        this.image_url = image_url;
        this.icon = icon;
    }

    public NotificationModel() {
    }

    protected NotificationModel(Parcel in) {
        title = in.readString();
        message = in.readString();
        image_url = in.readParcelable(Uri.class.getClassLoader());
        icon = in.readString();
    }

    public static final Creator<NotificationModel> CREATOR = new Creator<NotificationModel>() {
        @Override
        public NotificationModel createFromParcel(Parcel in) {
            return new NotificationModel(in);
        }

        @Override
        public NotificationModel[] newArray(int size) {
            return new NotificationModel[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(message);
        parcel.writeParcelable(image_url, i);
        parcel.writeString(icon);
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public Uri getImage_url() {
        return image_url;
    }

    public String getIcon() {
        return icon;
    }
}
