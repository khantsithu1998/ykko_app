package com.ykko.app.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Order implements Parcelable {
    public String branch;
    public String date;
    public String description;
    public String food1;
    public String food2;
    public String name;
    public int numberOfPersons;
    public String phNo;
    public String township;

    public Order() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Order(String branch,String date, String description,String food1,String food2,String name,int numberOfPersons,String phNo,String township) {
        this.branch = branch;
        this.date = date;
        this.description = description;
        this.food1 = food1;
        this.food2 = food2;
        this.name = name;
        this.numberOfPersons = numberOfPersons;
        this.phNo = phNo;
        this.township = township;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
