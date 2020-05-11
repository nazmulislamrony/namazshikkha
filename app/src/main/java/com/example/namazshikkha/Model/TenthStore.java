package com.example.namazshikkha.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class TenthStore implements Parcelable {
    public int id;
    public String name, content;

    public TenthStore(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public TenthStore() {
    }

    protected TenthStore(Parcel in) {
        id = in.readInt();
        name = in.readString();
        content = in.readString();
    }

    public static final Creator<TenthStore> CREATOR = new Creator<TenthStore>() {
        @Override
        public TenthStore createFromParcel(Parcel in) {
            return new TenthStore(in);
        }

        @Override
        public TenthStore[] newArray(int size) {
            return new TenthStore[size];
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
