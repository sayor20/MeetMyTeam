package com.example.ss282p.meetmyteam.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Employee implements Parcelable {

    @SerializedName("id")
    private int id;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("firstName")
    private String fName;
    @SerializedName("lastName")
    private String lName;
    @SerializedName("title")
    private String title;
    @SerializedName("bio")
    private String bio;

    public Employee(String fName, String avatar){
        this.fName = fName;
        this.avatar = avatar;
        this.id = 0;
        this.lName = "";
        this.title = "";
        this.bio = "";
    }

    public Employee(int id, String fName, String lName, String avatar, String title, String bio){
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.avatar = avatar;
        this.title = title;
        this.bio = bio;
    }

    protected Employee(Parcel in) {
        id = in.readInt();
        avatar = in.readString();
        fName = in.readString();
        lName = in.readString();
        title = in.readString();
        bio = in.readString();
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(avatar);
        parcel.writeString(fName);
        parcel.writeString(lName);
        parcel.writeString(title);
        parcel.writeString(bio);
    }
}
