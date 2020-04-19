package com.ykko.app.data.model;

public class FoodMenu {
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
}
