package com.voltagelab.namazshikkhaapps.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class TwelveStore implements Parcelable {
    private int id;
    private String name;

    public TwelveStore(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TwelveStore() {
    }

    protected TwelveStore(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<TwelveStore> CREATOR = new Creator<TwelveStore>() {
        @Override
        public TwelveStore createFromParcel(Parcel in) {
            return new TwelveStore(in);
        }

        @Override
        public TwelveStore[] newArray(int size) {
            return new TwelveStore[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }
}
