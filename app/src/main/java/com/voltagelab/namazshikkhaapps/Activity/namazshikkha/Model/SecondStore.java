package com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class SecondStore implements Parcelable {

    public int id;
    public String name, content;

    public SecondStore(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public SecondStore() {
    }

    protected SecondStore(Parcel in) {
        id = in.readInt();
        name = in.readString();
        content = in.readString();
    }

    public static final Creator<SecondStore> CREATOR = new Creator<SecondStore>() {
        @Override
        public SecondStore createFromParcel(Parcel in) {
            return new SecondStore(in);
        }

        @Override
        public SecondStore[] newArray(int size) {
            return new SecondStore[size];
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
