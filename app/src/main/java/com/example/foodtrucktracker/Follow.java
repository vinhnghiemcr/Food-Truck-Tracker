package com.example.foodtrucktracker;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Follow")
public class Follow extends ParseObject {
    public static final String KEY_USER= "user";
    public static final String KEY_TRUCK= "truck";



    public ParseUser getUser() { return getParseUser(KEY_USER);};

    public void setUser(ParseUser user){ put(KEY_USER, user);};

    public ParseObject getTruck() { return getParseObject(KEY_TRUCK);};

    public void setTruck(ParseObject truck){ put(KEY_TRUCK, truck);};


}
