package com.example.namazshikkha.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class FourthStore implements Parcelable {
    public int id;
    public String name, content;

    public FourthStore(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public FourthStore() {
    }

    protected FourthStore(Parcel in) {
        id = in.readInt();
        name = in.readString();
        content = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FourthStore> CREATOR = new Creator<FourthStore>() {
        @Override
        public FourthStore createFromParcel(Parcel in) {
            return new FourthStore(in);
        }

        @Override
        public FourthStore[] newArray(int size) {
            return new FourthStore[size];
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
}
