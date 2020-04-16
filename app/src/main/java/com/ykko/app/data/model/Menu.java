package com.ykko.app.data.model;

public class Menu {
    public String foodStickName;
    public String foodStickType;
    public String price;

    public Menu() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Menu(String foodStickName,String foodStickType, String price) {
        this.foodStickName = foodStickName;
        this.foodStickType = foodStickType;
        this.price = price;
    }
}
