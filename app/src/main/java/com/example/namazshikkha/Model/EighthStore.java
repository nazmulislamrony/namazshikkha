package com.example.namazshikkha.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;

public class EighthStore implements Parcelable {

    public int id;
    public String name, content;

    public EighthStore(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public EighthStore() {
    }

    protected EighthStore(Parcel in) {
        id = in.readInt();
        name = in.readString();
        content = in.readString();
    }

    public static final Creator<EighthStore> CREATOR = new Creator<EighthStore>() {
        @Override
        public EighthStore createFromParcel(Parcel in) {
            return new EighthStore(in);
        }

        @Override
        public EighthStore[] newArray(int size) {
            return new EighthStore[size];
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
