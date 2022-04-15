package com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ThirdStore implements Parcelable {

    public int id;
    public String name, content;

    public ThirdStore(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public ThirdStore() {
    }

    protected ThirdStore(Parcel in) {
        id = in.readInt();
        name = in.readString();
        content = in.readString();
    }

    public static final Creator<ThirdStore> CREATOR = new Creator<ThirdStore>() {
        @Override
        public ThirdStore createFromParcel(Parcel in) {
            return new ThirdStore(in);
        }

        @Override
        public ThirdStore[] newArray(int size) {
            return new ThirdStore[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(content);
    }
}
