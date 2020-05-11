package com.example.namazshikkha.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class SeventhStore implements Parcelable {
    public int id;
    public String name, content;

    public SeventhStore(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public SeventhStore() {
    }

    protected SeventhStore(Parcel in) {
        id = in.readInt();
        name = in.readString();
        content = in.readString();
    }

    public static final Creator<SeventhStore> CREATOR = new Creator<SeventhStore>() {
        @Override
        public SeventhStore createFromParcel(Parcel in) {
            return new SeventhStore(in);
        }

        @Override
        public SeventhStore[] newArray(int size) {
            return new SeventhStore[size];
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
