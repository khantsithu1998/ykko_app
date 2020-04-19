package com.ykko.app.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodMenu implements Parcelable {
    public String foodStickName;
    public String foodStickType;
    public String price;

    public FoodMenu() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public FoodMenu(String foodStickName, String foodStickType, String price) {
        this.foodStickName = foodStickName;
        this.foodStickType = foodStickType;
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
