package com.example.recyclerview;

import android.os.Parcel;
import android.os.Parcelable;

public class MhsModel implements Parcelable {

    int id ;
    String name ;
    String nim ;
    String phone ;

    public MhsModel(int id, String name, String nim, String phone) {
        this.id = id;
        this.name = name;
        this.nim = nim;
        this.phone = phone;
    }

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

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.nim);
        dest.writeString(this.phone);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.name = source.readString();
        this.nim = source.readString();
        this.phone = source.readString();
    }

    public MhsModel() {
    }

    protected MhsModel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.nim = in.readString();
        this.phone = in.readString();
    }

    public static final Creator<MhsModel> CREATOR = new Creator<MhsModel>() {
        @Override
        public MhsModel createFromParcel(Parcel source) {
            return new MhsModel(source);
        }

        @Override
        public MhsModel[] newArray(int size) {
            return new MhsModel[size];
        }
    };
}
