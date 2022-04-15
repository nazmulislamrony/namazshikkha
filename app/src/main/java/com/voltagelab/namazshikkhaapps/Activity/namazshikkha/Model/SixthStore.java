package com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class SixthStore implements Parcelable {

    public int id;
    public String name, content;

    public SixthStore(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public SixthStore() {
    }

    protected SixthStore(Parcel in) {
        id = in.readInt();
        name = in.readString();
        content = in.readString();
    }

    public static final Creator<SixthStore> CREATOR = new Creator<SixthStore>() {
        @Override
        public SixthStore createFromParcel(Parcel in) {
            return new SixthStore(in);
        }

        @Override
        public SixthStore[] newArray(int size) {
            return new SixthStore[size];
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
