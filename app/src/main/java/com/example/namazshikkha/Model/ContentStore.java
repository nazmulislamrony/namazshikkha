package com.example.namazshikkha.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ContentStore implements Parcelable {
    public int id;
    public String dataName, dataContent;

    public ContentStore(int id, String dataName, String dataContent) {
        this.id = id;
        this.dataName = dataName;
        this.dataContent = dataContent;
    }

    public ContentStore() {
    }

    protected ContentStore(Parcel in) {
        id = in.readInt();
        dataName = in.readString();
        dataContent = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(dataName);
        dest.writeString(dataContent);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ContentStore> CREATOR = new Creator<ContentStore>() {
        @Override
        public ContentStore createFromParcel(Parcel in) {
            return new ContentStore(in);
        }

        @Override
        public ContentStore[] newArray(int size) {
            return new ContentStore[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataContent() {
        return dataContent;
    }

    public void setDataContent(String dataContent) {
        this.dataContent = dataContent;
    }
}
