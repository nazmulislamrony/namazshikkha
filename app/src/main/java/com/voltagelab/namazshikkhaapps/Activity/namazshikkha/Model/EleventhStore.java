package com.voltagelab.namazshikkhaapps.Activity.namazshikkha.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class EleventhStore implements Parcelable {
    public int id;
    public String name,content;

    public EleventhStore (int id, String name, String content){
        this.id=id;
        this.name=name;
        this.content=content;
    }

    public EleventhStore(){

    }

    protected EleventhStore(Parcel in) {
        id = in.readInt();
        name = in.readString();
        content = in.readString();
    }

    public static final Creator<EleventhStore> CREATOR = new Creator<EleventhStore>() {
        @Override
        public EleventhStore createFromParcel(Parcel in) {
            return new EleventhStore(in);
        }

        @Override
        public EleventhStore[] newArray(int size) {
            return new EleventhStore[size];
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
