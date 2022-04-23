package com.example.foodtrucktracker;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Truck")
public class Truck extends ParseObject {
    public static final String KEY_TRUCK_NAME = "name";
    public static final String KEY_OPEN_HOURS= "open_hours";
    public static final String KEY_TRUCK_DESCRIPTION = "description";
    public static final String KEY_PHONE_NUMBER = "phone_number";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";

    public String getTruckName() {return getString(KEY_TRUCK_NAME);}

    public void setTruckName(String truckName) {
        put(KEY_TRUCK_NAME, truckName);
    }

    public String getOpenHours() {return getString(KEY_OPEN_HOURS);}

    public void setSetOpenHours(String openHours) {
        put(KEY_OPEN_HOURS, openHours);
    }

    public String getTruckDescription() {return getString(KEY_TRUCK_DESCRIPTION);}

    public void setTruckDescription(String description) {put(KEY_TRUCK_DESCRIPTION, description);}

    public String getPhoneNumber() {return getString(KEY_PHONE_NUMBER);}

    public void setPhoneNumber(String phoneNumber) {put(KEY_PHONE_NUMBER, phoneNumber);}

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile parseFile) {
        put(KEY_IMAGE, parseFile);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }
}
