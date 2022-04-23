package com.example.foodtrucktracker;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;


@ParseClassName("Dish")
public class Dish extends ParseObject {
    public static final String KEY_NAME= "name";
    public static final String KEY_DESCRIPTION= "description";
    public static final String KEY_PRICE = "price";

    public String getName() { return getString(KEY_NAME);}

    public void setName(String name) {
        put(KEY_NAME, name);
    }

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public Integer getPrice() {
        return getInt(KEY_PRICE);
    }

    public void setPrice(Integer price) {
        put(KEY_PRICE, price);
    }

}
