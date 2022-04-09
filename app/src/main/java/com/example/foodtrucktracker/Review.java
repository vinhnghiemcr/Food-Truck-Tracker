package com.example.foodtrucktracker;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Review")
public class Review extends ParseObject {
    public static final String KEY_RATING = "rating";
    public static final String KEY_COMMENT = "comment";
    public static final String KEY_TRUCK = "truck";
    public static final String KEY_USER = "user";

    public Integer getRating() {
        return getInt(KEY_RATING);
    }

    public void setRating(int rating) {
        put(KEY_RATING, rating);
    }

    public String getComment() {
        return getString(KEY_COMMENT);
    }

    public void setComment(String comment) {
        put(KEY_COMMENT, comment);
    }

    public String getTruckId() {
        return getString(KEY_TRUCK);
    }

    public void setTruckId(String truckId) {
        put(KEY_TRUCK, truckId);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }
}
